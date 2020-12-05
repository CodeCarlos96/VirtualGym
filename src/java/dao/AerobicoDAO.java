package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Aerobico;
import model.FichaTreino;

public class AerobicoDAO {

    private static AerobicoDAO instancia = new AerobicoDAO();

    private AerobicoDAO() {
    }

    public static AerobicoDAO getInstancia() {
        return instancia;
    }

    public Aerobico gravar(Aerobico aerobico) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (aerobico.getIdAerobico() == null) {
                atualizarOrdem(aerobico, em, true);
                em.persist(aerobico);
            } else {
                atualizarOrdem(aerobico, em, false);
                em.merge(aerobico);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return aerobico;
    }

    public Aerobico excluir(Integer idAerobico) {
        EntityManager em = new ConexaoFactory().getConexao();
        Aerobico aerobico = null;
        try {
            aerobico = em.find(Aerobico.class, idAerobico);
            em.getTransaction().begin();
            em.remove(aerobico);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return aerobico;
    }

    public void excluirExerciciosFicha(FichaTreino fichaTreino, EntityManager em) throws Exception {
        em.createQuery(
                "DELETE FROM Aerobico a WHERE a.fichaTreino = :fichaTreino")
                .setParameter("fichaTreino", fichaTreino)
                .executeUpdate();
    }

    public Aerobico obterAerobico(Integer idAerobico) {
        EntityManager em = new ConexaoFactory().getConexao();
        Aerobico aerobico = null;
        try {
            aerobico = em.find(Aerobico.class, idAerobico);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return aerobico;
    }

    public List<Aerobico> obterAerobicos(Integer idFichaTreino) {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Aerobico> aerobicos = null;
        try {
            aerobicos = em.createQuery("SELECT a FROM Aerobico a"
                    + " WHERE a.fichaTreino = :fichaTreino"
                    + " ORDER BY a.ordem")
                    .setParameter("fichaTreino", FichaTreino.obterFichaTreino(idFichaTreino))
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return aerobicos;
    }

    public int obterOrdem(Integer idFichaTreino) {
        EntityManager em = new ConexaoFactory().getConexao();
        long ordem = 0;
        try {
            ordem = (long) em.createQuery("SELECT COUNT(a) FROM Aerobico a"
                    + " WHERE a.fichaTreino = :fichaTreino")
                    .setParameter("fichaTreino", FichaTreino.obterFichaTreino(idFichaTreino))
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return Math.toIntExact(ordem);
    }
    
    public void atualizarOrdem(Aerobico aerobico, EntityManager em, boolean gravar) throws Exception {
        if (gravar) {
            if (aerobico.getOrdem() <= Aerobico.obterOrdem(aerobico.getFichaTreino().getIdFichaTreino())) {
                em.createQuery(
                        "UPDATE Aerobico a SET a.ordem = (a.ordem + 1) WHERE a.fichaTreino = :fichaTreino AND a.ordem >= :ordem")
                        .setParameter("fichaTreino", aerobico.getFichaTreino())
                        .setParameter("ordem", aerobico.getOrdem())
                        .executeUpdate();
            }
        } else {
            int antigaOrdem = (int) em.createQuery("SELECT a.ordem FROM Aerobico a"
                    + " WHERE a.idAerobico = :idAerobico")
                    .setParameter("idAerobico", aerobico.getIdAerobico())
                    .getSingleResult();

            if (aerobico.getOrdem() > antigaOrdem) {
                em.createQuery(
                        "UPDATE Aerobico a SET a.ordem = (a.ordem - 1)"
                        + " WHERE a.fichaTreino = :fichaTreino"
                        + " AND a.ordem BETWEEN :antigaOrdem"
                        + " AND :ordem")
                        .setParameter("fichaTreino", aerobico.getFichaTreino())
                        .setParameter("antigaOrdem", antigaOrdem)
                        .setParameter("ordem", aerobico.getOrdem())
                        .executeUpdate();
            }
            if (aerobico.getOrdem() < antigaOrdem) {
                em.createQuery(
                        "UPDATE Aerobico a SET a.ordem = (a.ordem + 1)"
                        + " WHERE a.fichaTreino = :fichaTreino"
                        + " AND a.ordem BETWEEN :ordem"
                        + " AND :antigaOrdem")
                        .setParameter("fichaTreino", aerobico.getFichaTreino())
                        .setParameter("ordem", aerobico.getOrdem())
                        .setParameter("antigaOrdem", antigaOrdem)
                        .executeUpdate();
            }
        }
    }
}
