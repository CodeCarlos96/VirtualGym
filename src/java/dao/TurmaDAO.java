package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Horario;
import model.MatriculaAula;
import model.Turma;

public class TurmaDAO {

    private static TurmaDAO instancia = new TurmaDAO();

    private TurmaDAO() {
    }

    public static TurmaDAO getInstancia() {
        return instancia;
    }

    public Turma gravar(Turma turma) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (turma.getIdTurma() == null) {
                em.persist(turma);
            } else {
                em.merge(turma);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return turma;
    }

    public Turma excluir(Integer idTurma) {
        EntityManager em = new ConexaoFactory().getConexao();
        Turma turma = null;
        try {
            turma = em.find(Turma.class, idTurma);
            em.getTransaction().begin();
            Horario.excluirHorarioTurma(turma, em);
            MatriculaAula.excluirMatriculaTurma(turma, em);
            em.remove(turma);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return turma;
    }

    public Turma obterTurma(Integer idTurma) {
        EntityManager em = new ConexaoFactory().getConexao();
        Turma turma = null;
        try {
            turma = em.find(Turma.class, idTurma);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return turma;
    }

    public List<Turma> obterTurmas() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Turma> turmas = null;
        try {
            turmas = em.createQuery("from Turma t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return turmas;
    }
}
