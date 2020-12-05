package model;

import dao.MatriculaAcademiaDAO;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MatriculaAcademia")
public class MatriculaAcademia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatriculaAcademia;
    private Date dataMatricula;
    private int diaVencimento;
    @OneToOne
    private Aluno aluno;
    @ManyToOne
    private Plano plano;

    public MatriculaAcademia() {
    }

    public MatriculaAcademia(Integer idMatriculaAcademia, Date dataMatricula, int diaVencimento, Aluno aluno, Plano plano) {
        this.idMatriculaAcademia = idMatriculaAcademia;
        this.dataMatricula = dataMatricula;
        this.diaVencimento = diaVencimento;
        this.aluno = aluno;
        this.plano = plano;
    }

    public Integer getIdMatriculaAcademia() {
        return idMatriculaAcademia;
    }

    public void setIdMatriculaAcademia(Integer idMatriculaAcademia) {
        this.idMatriculaAcademia = idMatriculaAcademia;
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

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public static MatriculaAcademia obterMatriculaAcademia(Integer idMatriculaAcademia) {
        return MatriculaAcademiaDAO.getInstancia().obterMatriculaAcademia(idMatriculaAcademia);
    }

    public static List<MatriculaAcademia> obterMatriculasAcademia() {
        return MatriculaAcademiaDAO.getInstancia().obterMatriculaAcademias();
    }
    
    public static boolean matriculado(Integer idAluno) {
        return MatriculaAcademiaDAO.getInstancia().matriculado(idAluno);
    }

    public MatriculaAcademia gravar() {
        return MatriculaAcademiaDAO.getInstancia().gravar(this);
    }

    public MatriculaAcademia excluir() {
        return MatriculaAcademiaDAO.getInstancia().excluir(this.idMatriculaAcademia);
    }
    
    public static void excluirMatriculaAluno(Aluno aluno, EntityManager em) throws Exception {
        MatriculaAcademiaDAO.getInstancia().excluirMatriculaAluno(aluno, em);
    }
}
