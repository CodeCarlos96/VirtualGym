package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Plano;

public class PlanoDAO {
 
    private static PlanoDAO instancia = new PlanoDAO();

    private PlanoDAO() {
    }
 
    public static PlanoDAO getInstancia() {
        return instancia;
    }
 
    public Plano gravar(Plano plano) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (plano.getIdPlano() == null) {
                em.persist(plano);
            } else {
                em.merge(plano);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return plano;
    }

    public Plano excluir(Integer idPlano) {
        EntityManager em = new ConexaoFactory().getConexao();
        Plano plano = null;
        try {
            plano = em.find(Plano.class, idPlano);
            em.getTransaction().begin();
            em.remove(plano);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return plano;
    }

    public Plano obterPlano(Integer idPlano) {
        EntityManager em = new ConexaoFactory().getConexao();
        Plano plano = null;
        try {
            plano = em.find(Plano.class, idPlano);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return plano;
    }

    public List<Plano> obterPlanos() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Plano> planos = null;
        try {
            planos = em.createQuery("from Plano p").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return planos;
    }
}
