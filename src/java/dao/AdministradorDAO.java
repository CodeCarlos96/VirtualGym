package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Administrador;

public class AdministradorDAO {

    private static AdministradorDAO instancia = new AdministradorDAO();

    private AdministradorDAO() {
    }

    public static AdministradorDAO getInstancia() {
        return instancia;
    }

    public Administrador gravar(Administrador administrador) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (administrador.getIdAdministrador() == null) {
                em.persist(administrador.getUsuario().getEndereco());
                em.persist(administrador.getUsuario());
                em.persist(administrador);
            } else {
                em.merge(administrador.getUsuario().getEndereco());
                em.merge(administrador.getUsuario());
                em.merge(administrador);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return administrador;
    }

    public Administrador excluir(Integer idAdministrador) {
        EntityManager em = new ConexaoFactory().getConexao();
        Administrador administrador = null;
        try {
            administrador = em.find(Administrador.class, idAdministrador);
            em.getTransaction().begin();
            em.remove(administrador);
            em.remove(administrador.getUsuario());
            em.remove(administrador.getUsuario().getEndereco());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return administrador;
    }

    public Administrador obterAdministrador(Integer idAdministrador) {
        EntityManager em = new ConexaoFactory().getConexao();
        Administrador administrador = null;
        try {
            administrador = em.find(Administrador.class, idAdministrador);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return administrador;
    }

    public List<Administrador> obterAdministradores() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Administrador> administradores = null;
        try {
            administradores = em.createQuery("from Administrador t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return administradores;
    }
}
