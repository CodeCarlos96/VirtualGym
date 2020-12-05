package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Aerobico;
import model.Aluno;
import model.FichaTreino;
import model.Musculacao;

public class FichaTreinoDAO {

    private static FichaTreinoDAO instancia = new FichaTreinoDAO();

    private FichaTreinoDAO() {
    }

    public static FichaTreinoDAO getInstancia() {
        return instancia;
    }

    public FichaTreino gravar(FichaTreino fichaTreino) {
        EntityManager em = new ConexaoFactory().getConexao();
        try {
            em.getTransaction().begin();
            if (fichaTreino.getIdFichaTreino() == null) {
                em.persist(fichaTreino);
            } else {
                em.merge(fichaTreino);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return fichaTreino;
    }

    public FichaTreino excluir(Integer idFichaTreino) {
        EntityManager em = new ConexaoFactory().getConexao();
        FichaTreino fichaTreino = null;
        try {
            fichaTreino = em.find(FichaTreino.class, idFichaTreino);
            em.getTransaction().begin();
            Musculacao.excluirExerciciosFicha(fichaTreino, em);
            Aerobico.excluirExerciciosFicha(fichaTreino, em);
            em.remove(fichaTreino);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
        } finally {
            em.close();
        }
        return fichaTreino;
    }
    
    public void excluirFichaAluno(Aluno aluno, EntityManager em) throws Exception {
        List<FichaTreino> fichaTreinos = null;
        try {
            fichaTreinos = em.createQuery(
                    "FROM FichaTreino f WHERE f.aluno = :aluno")
                    .setParameter("aluno", aluno)
                    .getResultList();
            System.out.println(fichaTreinos);
            
            for(FichaTreino f : fichaTreinos){
                excluir(f.getIdFichaTreino());
            }
            
//            em.createQuery(
//                    "DELETE FROM FichaTreino f WHERE f.aluno = :aluno")
//                    .setParameter("aluno", aluno)
//                    .executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public FichaTreino obterFichaTreino(Integer idFichaTreino) {
        EntityManager em = new ConexaoFactory().getConexao();
        FichaTreino fichaTreino = null;
        try {
            fichaTreino = em.find(FichaTreino.class, idFichaTreino);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return fichaTreino;
    }

    public List<FichaTreino> obterFichaTreinos() {
        EntityManager em = new ConexaoFactory().getConexao();
        List<FichaTreino> fichaTreinos = null;
        try {
            fichaTreinos = em.createQuery("from FichaTreino t").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return fichaTreinos;
    }
}
