
package model;

import dao.MatriculaAulaDAO;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MatriculaAula")
public class MatriculaAula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatriculaAula;
    private Date dataMatricula;
    private int diaVencimento;
    @ManyToOne
    private Aluno aluno;
    @ManyToOne
    private Turma turma;

    public MatriculaAula() {
    }

    public MatriculaAula(Integer idMatriculaAula, Date dataMatricula, int diaVencimento, Aluno aluno, Turma turma) {
        this.idMatriculaAula = idMatriculaAula;
        this.dataMatricula = dataMatricula;
        this.diaVencimento = diaVencimento;
        this.aluno = aluno;
        this.turma = turma;
    }

    public Integer getIdMatriculaAula() {
        return idMatriculaAula;
    }

    public void setIdMatriculaAula(Integer idMatriculaAula) {
        this.idMatriculaAula = idMatriculaAula;
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(int diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public static int getMatriculados(Integer idTurma) {
        return MatriculaAulaDAO.getMatriculados(idTurma);
    }

    public static boolean matriculado(Integer idAluno, Integer idTurma) {
        return MatriculaAulaDAO.matriculado(idAluno, idTurma);
    }

    public static MatriculaAula obterMatriculaAula(Integer idMatriculaAula) {
        return MatriculaAulaDAO.getInstancia().obterMatriculaAula(idMatriculaAula);
    }

    public static List<MatriculaAula> obterMatriculasAula() {
        return MatriculaAulaDAO.getInstancia().obterMatriculaAulas();
    }

    public MatriculaAula gravar() {
        return MatriculaAulaDAO.getInstancia().gravar(this);
    }

    public MatriculaAula excluir() {
        return MatriculaAulaDAO.getInstancia().excluir(this.idMatriculaAula);
    }
    
    public static void excluirMatriculaTurma(Turma turma, EntityManager em) throws Exception {
        MatriculaAulaDAO.getInstancia().excluirMatriculaTurma(turma, em);
    }
}
