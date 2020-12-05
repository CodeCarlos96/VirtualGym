package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Aluno;
import model.MatriculaAula;
import model.Turma;

public class MatriculaAulaDAO {

    private static MatriculaAulaDAO instancia = new MatriculaAulaDAO();

    private MatriculaAulaDAO() {
    }

    public static MatriculaAulaDAO getInstancia() {
        return instancia;
    }

    public MatriculaAula gravar(MatriculaAula matriculaAula) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (matriculaAula.getIdMatriculaAula() == null) {
                em.persist(matriculaAula);
            } else {
                em.merge(matriculaAula);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return matriculaAula;
    }

    public MatriculaAula excluir(Integer idMatriculaAula) {
        EntityManager em = new ConexaoFactory().getConexao();
        MatriculaAula matriculaAula = null;
        try {
            matriculaAula = em.find(MatriculaAula.class, idMatriculaAula);
            em.getTransaction().begin();
            em.remove(matriculaAula);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return matriculaAula;
    }
    
    public void excluirMatriculaTurma(Turma turma, EntityManager em) throws Exception {
        try {
            em.createQuery(
                    "DELETE FROM MatriculaAula m WHERE m.turma = :turma")
                    .setParameter("turma", turma)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public MatriculaAula obterMatriculaAula(Integer idMatriculaAula) {
        EntityManager em = new ConexaoFactory().getConexao();
        MatriculaAula matriculaAula = null;
        try {
            matriculaAula = em.find(MatriculaAula.class, idMatriculaAula);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return matriculaAula;
    }

    public List<MatriculaAula> obterMatriculaAulas() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<MatriculaAula> matriculaAulas = null;
        try {
            matriculaAulas = em.createQuery("from MatriculaAula t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return matriculaAulas;
    }

    public static int getMatriculados(Integer idTurma) {
        EntityManager em = new ConexaoFactory().getConexao();
        long matriculados = 0;
        try {
            matriculados = (long) em.createQuery(
                    "SELECT COUNT(m) FROM MatriculaAula m WHERE m.turma = :turma")
                    .setParameter("turma", Turma.obterTurma(idTurma))
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return Math.toIntExact(matriculados);
    }

    public static boolean matriculado(Integer idAluno, Integer idTurma) {
        EntityManager em = new ConexaoFactory().getConexao();
        long matriculado = 0;
        try {
            matriculado = (long) em.createQuery(
                    "SELECT COUNT(m) FROM MatriculaAula m WHERE m.aluno = :aluno"
                    + " and m.turma = :turma")
                    .setParameter("aluno", Aluno.obterAluno(idAluno))
                    .setParameter("turma", Turma.obterTurma(idTurma))
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return Math.toIntExact(matriculado) > 0;
    }

}
