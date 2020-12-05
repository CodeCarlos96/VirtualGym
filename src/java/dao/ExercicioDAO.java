package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.Exercicio;

public class ExercicioDAO {

    private static ExercicioDAO instancia = new ExercicioDAO();

    private ExercicioDAO() {
    }

    public static ExercicioDAO getInstancia() {
        return instancia;
    }

    public Exercicio gravar(Exercicio exercicio) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (exercicio.getIdExercicio() == null) {
                em.persist(exercicio);
            } else {
                em.merge(exercicio);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return exercicio;
    }

    public Exercicio excluir(Integer idExercicio) {
        EntityManager em = new ConexaoFactory().getConexao();
        Exercicio exercicio = null;
        try {
            exercicio = em.find(Exercicio.class, idExercicio);
            em.getTransaction().begin();
            em.remove(exercicio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return exercicio;
    }

    public Exercicio obterExercicio(Integer idExercicio) {
        EntityManager em = new ConexaoFactory().getConexao();
        Exercicio exercicio = null;
        try {
            exercicio = em.find(Exercicio.class, idExercicio);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return exercicio;
    }

    public List<Exercicio> obterExercicios() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Exercicio> exercicios = null;
        try {
            exercicios = em.createQuery("from Exercicio t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return exercicios;
    }

    public static List<Exercicio> obterExerciciosAerobicos() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Exercicio> exercicios = null;
        try {
            exercicios = em.createQuery("SELECT e FROM Exercicio e"
                    + " WHERE e.tipoTreino = :tipo")
                    .setParameter("tipo", "Aerobico").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return exercicios;
    }

    public static List<Exercicio> obterExerciciosMusculacao() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Exercicio> exercicios = null;
        try {
            exercicios = em.createQuery("SELECT e FROM Exercicio e"
                    + " WHERE e.tipoTreino = :tipo")
                    .setParameter("tipo", "Musculação").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return exercicios;
    }
}
