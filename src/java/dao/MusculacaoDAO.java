package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.FichaTreino;
import model.Musculacao;

public class MusculacaoDAO {

    private static MusculacaoDAO instancia = new MusculacaoDAO();

    private MusculacaoDAO() {
    }

    public static MusculacaoDAO getInstancia() {
        return instancia;
    }

    public Musculacao gravar(Musculacao musculacao) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (musculacao.getIdMusculacao() == null) {
                atualizarOrdem(musculacao, em, true);
                em.persist(musculacao);
            } else {
                atualizarOrdem(musculacao, em, false);
                em.merge(musculacao);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return musculacao;
    }

    public Musculacao excluir(Integer idMusculacao) {
        EntityManager em = new ConexaoFactory().getConexao();
        Musculacao musculacao = null;
        try {
            musculacao = em.find(Musculacao.class, idMusculacao);
            em.getTransaction().begin();
            em.remove(musculacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return musculacao;
    }

    public void excluirExerciciosFicha(FichaTreino fichaTreino, EntityManager em) throws Exception {
        em.createQuery(
                "DELETE FROM Musculacao m WHERE m.fichaTreino = :fichaTreino")
                .setParameter("fichaTreino", fichaTreino)
                .executeUpdate();
    }

    public Musculacao obterMusculacao(Integer idMusculacao) {
        EntityManager em = new ConexaoFactory().getConexao();
        Musculacao musculacao = null;
        try {
            musculacao = em.find(Musculacao.class, idMusculacao);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return musculacao;
    }

    public List<Musculacao> obterMusculacaos(Integer idFichaTreino) {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Musculacao> musculacoes = null;
        try {
            musculacoes = em.createQuery("SELECT m FROM Musculacao m"
                    + " WHERE m.fichaTreino = :fichaTreino"
                    + " ORDER BY m.ordem")
                    .setParameter("fichaTreino", FichaTreino.obterFichaTreino(idFichaTreino))
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return musculacoes;
    }

    public int obterOrdem(Integer idFichaTreino) {
        EntityManager em = new ConexaoFactory().getConexao();
        long ordem = 0;
        try {
            ordem = (long) em.createQuery("SELECT COUNT(m) FROM Musculacao m"
                    + " WHERE m.fichaTreino = :fichaTreino")
                    .setParameter("fichaTreino", FichaTreino.obterFichaTreino(idFichaTreino))
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return Math.toIntExact(ordem);
    }

    public void atualizarOrdem(Musculacao musculacao, EntityManager em, boolean gravar) throws Exception {
        if (gravar) {
            if (musculacao.getOrdem() <= Musculacao.obterOrdem(musculacao.getFichaTreino().getIdFichaTreino())) {
                em.createQuery(
                        "UPDATE Musculacao a SET a.ordem = (a.ordem + 1) WHERE a.fichaTreino = :fichaTreino AND a.ordem >= :ordem")
                        .setParameter("fichaTreino", musculacao.getFichaTreino())
                        .setParameter("ordem", musculacao.getOrdem())
                        .executeUpdate();
            }
        } else {
            int antigaOrdem = (int) em.createQuery("SELECT a.ordem FROM Musculacao a"
                    + " WHERE a.idMusculacao = :idMusculacao")
                    .setParameter("idMusculacao", musculacao.getIdMusculacao())
                    .getSingleResult();

            if (musculacao.getOrdem() > antigaOrdem) {
                em.createQuery(
                        "UPDATE Musculacao a SET a.ordem = (a.ordem - 1)"
                        + " WHERE a.fichaTreino = :fichaTreino"
                        + " AND a.ordem BETWEEN :antigaOrdem"
                        + " AND :ordem")
                        .setParameter("fichaTreino", musculacao.getFichaTreino())
                        .setParameter("antigaOrdem", antigaOrdem)
                        .setParameter("ordem", musculacao.getOrdem())
                        .executeUpdate();
            }
            if (musculacao.getOrdem() < antigaOrdem) {
                em.createQuery(
                        "UPDATE Musculacao a SET a.ordem = (a.ordem + 1)"
                        + " WHERE a.fichaTreino = :fichaTreino"
                        + " AND a.ordem BETWEEN :ordem"
                        + " AND :antigaOrdem")
                        .setParameter("fichaTreino", musculacao.getFichaTreino())
                        .setParameter("ordem", musculacao.getOrdem())
                        .setParameter("antigaOrdem", antigaOrdem)
                        .executeUpdate();
            }
        }
    }
}
