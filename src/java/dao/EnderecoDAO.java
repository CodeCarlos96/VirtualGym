package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Endereco;

public class EnderecoDAO {

    private static EnderecoDAO instancia = new EnderecoDAO();

    private EnderecoDAO() {
    }

    public static EnderecoDAO getInstancia() {
        return instancia;
    }

    public Endereco gravar(Endereco endereco) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (endereco.getIdEndereco() == null) {
                em.persist(endereco);
            } else {
                em.merge(endereco);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return endereco;
    }

    public Endereco excluir(Integer idEndereco) {
        EntityManager em = new ConexaoFactory().getConexao();
        Endereco endereco = null;
        try {
            endereco = em.find(Endereco.class, idEndereco);
            em.getTransaction().begin();
            em.remove(endereco);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return endereco;
    }

    public Endereco obterEndereco(Integer idEndereco) {
        EntityManager em = new ConexaoFactory().getConexao();
        Endereco endereco = null;
        try {
            endereco = em.find(Endereco.class, idEndereco);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return endereco;
    }

    public List<Endereco> obterEnderecos() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Endereco> enderecos = null;
        try {
            enderecos = em.createQuery("from Endereco t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return enderecos;
    }
}
