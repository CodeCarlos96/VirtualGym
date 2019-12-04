package model;

import dao.MatriculaAcademiaDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MatriculaAcademia {

    private int idMatriculaAcademia;
    private Date dataMatricula;
    private int diaVencimento;

    private Aluno aluno;
    private int idAluno;
    private Plano plano;
    private int idPlano;

    public MatriculaAcademia(int idMatriculaAcademia, Date dataMatricula, int diaVencimento, Aluno aluno, Plano plano) {
        this.idMatriculaAcademia = idMatriculaAcademia;
        this.dataMatricula = dataMatricula;
        this.diaVencimento = diaVencimento;
        this.aluno = aluno;
        this.plano = plano;
    }

    public int getIdMatriculaAcademia() {
        return idMatriculaAcademia;
    }

    public void setIdMatriculaAcademia(int idMatriculaAcademia) {
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

    public Aluno getAluno() throws ClassNotFoundException, SQLException {
        if((this.idAluno != 0) && (this.aluno == null)){
            this.aluno = Aluno.obterAluno(this.idAluno);
        }
        return this.aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public Plano getPlano() throws ClassNotFoundException, SQLException {
        if((this.idPlano != 0) && (this.plano == null)){
            this.plano = Plano.obterPlano(this.idPlano);
        }
        return this.plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public static MatriculaAcademia obterMatriculaAcademia(int idMatriculaAcademia) throws ClassNotFoundException, SQLException {
        return MatriculaAcademiaDAO.obterMatriculaAcademia(idMatriculaAcademia);
    }

    public static List<MatriculaAcademia> obterMatriculasAcademia() throws ClassNotFoundException, SQLException {
        return MatriculaAcademiaDAO.obterMatriculasAcademia();
    }

    public void gravar() throws SQLException, ClassNotFoundException{
        MatriculaAcademiaDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        MatriculaAcademiaDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        MatriculaAcademiaDAO.excluir(this);
    }
}
