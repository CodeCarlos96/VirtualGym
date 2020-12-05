package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Sala;

public class SalaDAO {

    private static SalaDAO instancia = new SalaDAO();

    private SalaDAO() {
    }

    public static SalaDAO getInstancia() {
        return instancia;
    }

    public Sala gravar(Sala sala) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (sala.getIdSala() == null) {
                em.persist(sala); 
            } else {
                em.merge(sala);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return sala;
    }

    public Sala excluir(Integer idSala) {
        EntityManager em = new ConexaoFactory().getConexao();
        Sala sala = null;
        try {
            sala = em.find(Sala.class, idSala);
            em.getTransaction().begin();
            em.remove(sala);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return sala;
    }

    public Sala obterSala(Integer idSala) {
        EntityManager em = new ConexaoFactory().getConexao();
        Sala sala = null;
        try {
            sala = em.find(Sala.class, idSala);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return sala;
    }

    public List<Sala> obterSalas() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Sala> salas = null;
        try {
            salas = em.createQuery("from Sala s").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return salas;
    }

}
