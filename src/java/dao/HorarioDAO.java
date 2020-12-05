package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Horario;
import model.Turma;

public class HorarioDAO {

    private static HorarioDAO instancia = new HorarioDAO();

    private HorarioDAO() {
    }

    public static HorarioDAO getInstancia() {
        return instancia;
    }

    public Horario gravar(Horario horario) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (horario.getIdHorario() == null) {
                em.persist(horario);
            } else {
                em.merge(horario);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return horario;
    }

    public Horario excluir(Integer idHorario) {
        EntityManager em = new ConexaoFactory().getConexao();
        Horario horario = null;
        try {
            horario = em.find(Horario.class, idHorario);
            em.getTransaction().begin();
            em.remove(horario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return horario;
    }

    public Horario obterHorario(Integer idHorario) {
        EntityManager em = new ConexaoFactory().getConexao();
        Horario horario = null;
        try {
            horario = em.find(Horario.class, idHorario);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return horario;
    }

    public List<Horario> obterHorarios(Integer idTurma) {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Horario> horarios = null;
        try {
            horarios = em.createQuery(
                    "SELECT h FROM Horario h WHERE h.turma = :turma")
                    .setParameter("turma", Turma.obterTurma(idTurma))
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return horarios;
    }

    public void excluirHorarioTurma(Turma turma, EntityManager em) throws Exception {
        try {
            em.createQuery(
                    "DELETE FROM Horario h WHERE h.turma = :turma")
                    .setParameter("turma", turma)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
