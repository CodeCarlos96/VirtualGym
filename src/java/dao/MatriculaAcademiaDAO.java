package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Aluno;
import model.MatriculaAcademia;

public class MatriculaAcademiaDAO {

    private static MatriculaAcademiaDAO instancia = new MatriculaAcademiaDAO();

    private MatriculaAcademiaDAO() {
    }

    public static MatriculaAcademiaDAO getInstancia() {
        return instancia;
    }

    public MatriculaAcademia gravar(MatriculaAcademia matriculaAcademia) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (matriculaAcademia.getIdMatriculaAcademia() == null) {
                em.persist(matriculaAcademia);
            } else {
                em.merge(matriculaAcademia);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return matriculaAcademia;
    }

    public MatriculaAcademia excluir(Integer idMatriculaAcademia) {
        EntityManager em = new ConexaoFactory().getConexao();
        MatriculaAcademia matriculaAcademia = null;
        try {
            matriculaAcademia = em.find(MatriculaAcademia.class, idMatriculaAcademia);
            em.getTransaction().begin();
            em.remove(matriculaAcademia);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return matriculaAcademia;
    }
    
    public void excluirMatriculaAluno(Aluno aluno, EntityManager em) throws Exception {
        try {
            em.createQuery(
                    "DELETE FROM MatriculaAcademia m WHERE m.aluno = :aluno")
                    .setParameter("aluno", aluno)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public MatriculaAcademia obterMatriculaAcademia(Integer idMatriculaAcademia) {
        EntityManager em = new ConexaoFactory().getConexao();
        MatriculaAcademia matriculaAcademia = null;
        try {
            matriculaAcademia = em.find(MatriculaAcademia.class, idMatriculaAcademia);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return matriculaAcademia;
    }

    public List<MatriculaAcademia> obterMatriculaAcademias() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<MatriculaAcademia> matriculaAcademias = null;
        try {
            matriculaAcademias = em.createQuery("from MatriculaAcademia t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return matriculaAcademias;
    }

    public boolean matriculado(Integer idAluno) {
        EntityManager em = new ConexaoFactory().getConexao();
        long matriculado = 0;
        try {
            matriculado = (long) em.createQuery(
                    "SELECT COUNT(m) FROM MatriculaAcademia m WHERE m.aluno = :aluno")
                    .setParameter("aluno", Aluno.obterAluno(idAluno))
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return Math.toIntExact(matriculado) > 0;
    }
}
