package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Aluno;
import model.Entrada;

public class EntradaDAO {
    
    private static EntradaDAO instancia = new EntradaDAO();
    
    private EntradaDAO() {
    }
    
    public static EntradaDAO getInstancia() {
        return instancia;
    }
    
    public Entrada gravar(Entrada entrada) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (entrada.getIdEntrada() == null) {
                em.persist(entrada);
            } else {
                em.merge(entrada);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return entrada;
    }
    
    public Entrada excluir(Integer idEntrada) {
        EntityManager em = new ConexaoFactory().getConexao();
        Entrada entrada = null;
        try {
            entrada = em.find(Entrada.class, idEntrada);
            em.getTransaction().begin();
            em.remove(entrada);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return entrada;
    }
    
    public Entrada obterEntrada(Integer idEntrada) {
        EntityManager em = new ConexaoFactory().getConexao();
        Entrada entrada = null;
        try {
            entrada = em.find(Entrada.class, idEntrada);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return entrada;
    }
    
    public List<Entrada> obterEntradas() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Entrada> entradas = null;
        try {
            entradas = em.createQuery("from Entrada e").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return entradas;
    }
    
    public List<Entrada> obterEntradasAluno(Integer idAluno) {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Entrada> entradas = null;
        try {
            entradas = em.createQuery("SELECT e FROM Entrada e"
                    + " WHERE e.aluno = :aluno")
                    .setParameter("aluno", Aluno.obterAluno(idAluno))
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return entradas;
    }
}
