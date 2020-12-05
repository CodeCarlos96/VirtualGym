package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.PagamentoAula;

public class PagamentoAulaDAO {

    private static PagamentoAulaDAO instancia = new PagamentoAulaDAO();

    private PagamentoAulaDAO() {
    }

    public static PagamentoAulaDAO getInstancia() {
        return instancia;
    }

    public PagamentoAula gravar(PagamentoAula pagamentoAula) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (pagamentoAula.getIdPagamentoAula() == null) {
                em.persist(pagamentoAula);
            } else {
                em.merge(pagamentoAula);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamentoAula;
    }

    public PagamentoAula excluir(Integer idPagamentoAula) {
        EntityManager em = new ConexaoFactory().getConexao();
        PagamentoAula pagamentoAula = null;
        try {
            pagamentoAula = em.find(PagamentoAula.class, idPagamentoAula);
            em.getTransaction().begin();
            em.remove(pagamentoAula);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamentoAula;
    }

    public PagamentoAula obterPagamentoAula(Integer idPagamentoAula) {
        EntityManager em = new ConexaoFactory().getConexao();
        PagamentoAula pagamentoAula = null;
        try {
            pagamentoAula = em.find(PagamentoAula.class, idPagamentoAula);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamentoAula;
    }

    public List<PagamentoAula> obterPagamentoAulas() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<PagamentoAula> pagamentoAulas = null;
        try {
            pagamentoAulas = em.createQuery("from PagamentoAula t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamentoAulas;
    }
}
