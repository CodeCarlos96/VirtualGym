
package model;

import dao.FichaTreinoDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FichaTreino {
    private int idFichaTreino;
    private Date dataInicio;
    private Date dataReavaliacao;
    private String dias;
    private String observacao;
    
    private Aluno aluno;
    private int idAluno;
    private Professor professor;
    private int idProfessor;

    public FichaTreino(int idFichaTreino, Date dataInicio, Date dataReavaliacao, String dias, String observacao, Aluno aluno, Professor professor) {
        this.idFichaTreino = idFichaTreino;
        this.dataInicio = dataInicio;
        this.dataReavaliacao = dataReavaliacao;
        this.dias = dias;
        this.observacao = observacao;
        this.aluno = aluno;
        this.professor = professor;
    }

    public int getIdFichaTreino() {
        return idFichaTreino;
    }

    public void setIdFichaTreino(int idFichaTreino) {
        this.idFichaTreino = idFichaTreino;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataReavaliacao() {
        return dataReavaliacao;
    }

    public void setDataReavaliacao(Date dataReavaliacao) {
        this.dataReavaliacao = dataReavaliacao;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Professor getProfessor() throws ClassNotFoundException, SQLException {
        if((this.idProfessor != 0) && (this.professor == null)){
            this.professor = Professor.obterProfessor(this.idProfessor);
        }
        return this.professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
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

    public static FichaTreino obterFichaTreino(int idFichaTreino) throws ClassNotFoundException, SQLException{
        return FichaTreinoDAO.obterFichaTreino(idFichaTreino);
    }
    
    public static List<FichaTreino> obterFichaTreinos() throws ClassNotFoundException, SQLException{
        return FichaTreinoDAO.obterFichaTreinos();
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        FichaTreinoDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        FichaTreinoDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        FichaTreinoDAO.excluir(this);
    }
}
