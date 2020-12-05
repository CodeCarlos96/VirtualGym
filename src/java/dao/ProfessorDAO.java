package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Professor;

public class ProfessorDAO {

    private static ProfessorDAO instancia = new ProfessorDAO();

    private ProfessorDAO() {
    }

    public static ProfessorDAO getInstancia() {
        return instancia;
    }

    public Professor gravar(Professor professor) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (professor.getIdProfessor() == null) {
                em.persist(professor.getUsuario().getEndereco());
                em.persist(professor.getUsuario());
                em.persist(professor);
            } else {
                em.merge(professor.getUsuario().getEndereco());
                em.merge(professor.getUsuario());
                em.merge(professor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return professor;
    }

    public Professor excluir(Integer idProfessor) {
        EntityManager em = new ConexaoFactory().getConexao();
        Professor professor = null;
        try {
            professor = em.find(Professor.class, idProfessor);
            em.getTransaction().begin();
            em.remove(professor);
            em.remove(professor.getUsuario());
            em.remove(professor.getUsuario().getEndereco());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return professor;
    }

    public Professor obterProfessor(Integer idProfessor) {
        EntityManager em = new ConexaoFactory().getConexao();
        Professor professor = null;
        try {
            professor = em.find(Professor.class, idProfessor);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return professor;
    }

    public List<Professor> obterProfessors() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Professor> professors = null;
        try {
            professors = em.createQuery("from Professor p").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return professors;
    }

}
