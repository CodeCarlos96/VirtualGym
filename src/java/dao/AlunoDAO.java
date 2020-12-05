package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Aluno;
import model.AvaliacaoFisica;
import model.FichaTreino;
import model.MatriculaAcademia;

public class AlunoDAO {

    private static AlunoDAO instancia = new AlunoDAO();

    private AlunoDAO() {
    }

    public static AlunoDAO getInstancia() {
        return instancia;
    }

    public Aluno gravar(Aluno aluno) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (aluno.getIdAluno() == null) {
                em.persist(aluno.getUsuario().getEndereco());
                em.persist(aluno.getUsuario());
                em.persist(aluno);
            } else {
                em.merge(aluno.getUsuario().getEndereco());
                em.merge(aluno.getUsuario());
                em.merge(aluno);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return aluno;
    }

    public Aluno excluir(Integer idAluno) {
        EntityManager em = new ConexaoFactory().getConexao();
        Aluno aluno = null;
        try {
            aluno = em.find(Aluno.class, idAluno);
            em.getTransaction().begin();
            AvaliacaoFisica.excluirAvaliacaoAluno(aluno, em);
            FichaTreino.excluirFichaAluno(aluno, em);
            MatriculaAcademia.excluirMatriculaAluno(aluno, em);
            em.remove(aluno);
            em.remove(aluno.getUsuario());
            em.remove(aluno.getUsuario().getEndereco());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return aluno;
    }

    public Aluno obterAluno(Integer idAluno) {
        EntityManager em = new ConexaoFactory().getConexao();
        Aluno aluno = null;
        try {
            aluno = em.find(Aluno.class, idAluno);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return aluno;
    }

    public List<Aluno> obterAlunos() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Aluno> alunos = null;
        try {
            alunos = em.createQuery("from Aluno a").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return alunos;
    }

    public List<Aluno> obterAlunosNome(String nomeAluno) {
        EntityManager em = new ConexaoFactory().getConexao();
        List<Aluno> alunos = null;
        try {
            alunos = em.createQuery("SELECT a FROM Aluno a"
                    + " WHERE a.usuario.nome LIKE '%" + nomeAluno + "%'")
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return alunos;
    }

}
