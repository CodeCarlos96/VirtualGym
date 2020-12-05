package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Aula;

public class AulaDAO {

    private static AulaDAO instancia = new AulaDAO();

    private AulaDAO() {
    }

    public static AulaDAO getInstancia() {
        return instancia;
    }

    public Aula gravar(Aula aula) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (aula.getIdAula() == null) {
                em.persist(aula);
            } else {
                em.merge(aula);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return aula;
    }

    public Aula excluir(Integer idAula) {
        EntityManager em = new ConexaoFactory().getConexao();
        Aula aula = null;
        try {
            aula = em.find(Aula.class, idAula);
            em.getTransaction().begin();
            em.remove(aula);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return aula;
    }

    public Aula obterAula(Integer idAula) {
        EntityManager em = new ConexaoFactory().getConexao();
        Aula aula = null;
        try {
            aula = em.find(Aula.class, idAula);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return aula;
    }

    public List<Aula> obterAulas() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Aula> aulas = null;
        try {
            aulas = em.createQuery("from Aula t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return aulas;
    }
}
