package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.PagamentoPlano;

public class PagamentoPlanoDAO {

    private static PagamentoPlanoDAO instancia = new PagamentoPlanoDAO();

    private PagamentoPlanoDAO() {
    }

    public static PagamentoPlanoDAO getInstancia() {
        return instancia;
    }

    public PagamentoPlano gravar(PagamentoPlano pagamentoPlano) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (pagamentoPlano.getIdPagamentoPlano() == null) {
                em.persist(pagamentoPlano);
            } else {
                em.merge(pagamentoPlano);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamentoPlano;
    }

    public PagamentoPlano excluir(Integer idPagamentoPlano) {
        EntityManager em = new ConexaoFactory().getConexao();
        PagamentoPlano pagamentoPlano = null;
        try {
            pagamentoPlano = em.find(PagamentoPlano.class, idPagamentoPlano);
            em.getTransaction().begin();
            em.remove(pagamentoPlano);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamentoPlano;
    }

    public PagamentoPlano obterPagamentoPlano(Integer idPagamentoPlano) {
        EntityManager em = new ConexaoFactory().getConexao();
        PagamentoPlano pagamentoPlano = null;
        try {
            pagamentoPlano = em.find(PagamentoPlano.class, idPagamentoPlano);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamentoPlano;
    }

    public List<PagamentoPlano> obterPagamentoPlanos() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<PagamentoPlano> pagamentosPlano = null;
        try {
            pagamentosPlano = em.createQuery("from PagamentoPlano t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamentosPlano;
    }
}
