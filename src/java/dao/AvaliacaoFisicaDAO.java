package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Aluno;
import model.AvaliacaoFisica;

public class AvaliacaoFisicaDAO {

    private static AvaliacaoFisicaDAO instancia = new AvaliacaoFisicaDAO();

    private AvaliacaoFisicaDAO() {
    }

    public static AvaliacaoFisicaDAO getInstancia() {
        return instancia;
    }

    public AvaliacaoFisica gravar(AvaliacaoFisica avaliacaoFisica) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (avaliacaoFisica.getIdAvaliacaoFisica() == null) {
                em.persist(avaliacaoFisica);
            } else {
                em.merge(avaliacaoFisica);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return avaliacaoFisica;
    }

    public AvaliacaoFisica excluir(Integer idAvaliacaoFisica) {
        EntityManager em = new ConexaoFactory().getConexao();
        AvaliacaoFisica avaliacaoFisica = null;
        try {
            avaliacaoFisica = em.find(AvaliacaoFisica.class, idAvaliacaoFisica);
            em.getTransaction().begin();
            em.remove(avaliacaoFisica);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return avaliacaoFisica;
    }
    
    public void excluirAvaliacaoAluno(Aluno aluno, EntityManager em) throws Exception {
        try {
            em.createQuery(
                    "DELETE FROM AvaliacaoFisica a WHERE a.aluno = :aluno")
                    .setParameter("aluno", aluno)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public AvaliacaoFisica obterAvaliacaoFisica(Integer idAvaliacaoFisica) {
        EntityManager em = new ConexaoFactory().getConexao();
        AvaliacaoFisica avaliacaoFisica = null;
        try {
            avaliacaoFisica = em.find(AvaliacaoFisica.class, idAvaliacaoFisica);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return avaliacaoFisica;
    }

    public List<AvaliacaoFisica> obterAvaliacoesFisicas() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<AvaliacaoFisica> avaliacoesFisicas = null;
        try {
            avaliacoesFisicas = em.createQuery("from AvaliacaoFisica a").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return avaliacoesFisicas;
    }
}
