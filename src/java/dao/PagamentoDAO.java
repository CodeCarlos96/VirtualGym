package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Pagamento;

public class PagamentoDAO {

    private static PagamentoDAO instancia = new PagamentoDAO();

    private PagamentoDAO() {
    }

    public static PagamentoDAO getInstancia() {
        return instancia;
    }

    public Pagamento gravar(Pagamento pagamento) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (pagamento.getIdPagamento() == null) {
                em.persist(pagamento);
            } else {
                em.merge(pagamento);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamento;
    }

    public Pagamento excluir(Integer idPagamento) {
        EntityManager em = new ConexaoFactory().getConexao();
        Pagamento pagamento = null;
        try {
            pagamento = em.find(Pagamento.class, idPagamento);
            em.getTransaction().begin();
            em.remove(pagamento);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamento;
    }

    public Pagamento obterPagamento(Integer idPagamento) {
        EntityManager em = new ConexaoFactory().getConexao();
        Pagamento pagamento = null;
        try {
            pagamento = em.find(Pagamento.class, idPagamento);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamento;
    }

    public List<Pagamento> obterPagamentos() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Pagamento> pagamentos = null;
        try {
            pagamentos = em.createQuery("from Pagamento t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return pagamentos;
    }
}
