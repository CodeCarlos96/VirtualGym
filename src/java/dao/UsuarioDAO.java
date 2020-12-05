package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Usuario;

public class UsuarioDAO {

    private static UsuarioDAO instancia = new UsuarioDAO();

    private UsuarioDAO() {
    }

    public static UsuarioDAO getInstancia() {
        return instancia;
    }

    public Usuario gravar(Usuario usuario) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (usuario.getIdUsuario() == null) {
                em.persist(usuario);
            } else {
                em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return usuario;
    }

    public Usuario excluir(Integer idUsuario) {
        EntityManager em = new ConexaoFactory().getConexao();
        Usuario usuario = null;
        try {
            usuario = em.find(Usuario.class, idUsuario);
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return usuario;
    }

    public Usuario obterUsuario(Integer idUsuario) {
        EntityManager em = new ConexaoFactory().getConexao();
        Usuario usuario = null;
        try {
            usuario = em.find(Usuario.class, idUsuario);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return usuario;
    }

    public List<Usuario> obterUsuarios() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Usuario> usuarios = null;
        try {
            usuarios = em.createQuery("from Usuario u").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return usuarios;
    }
}
