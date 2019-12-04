/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.MatriculaAulaDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class MatriculaAula {

    private int idMatriculaAula;
    private Date dataMatricula;
    private int diaVencimento;

    private Aluno aluno;
    private int idAluno;
    private Turma turma;
    private int idTurma;

    public MatriculaAula(int idMatriculaAula, Date dataMatricula, int diaVencimento, Aluno aluno, Turma turma) {
        this.idMatriculaAula = idMatriculaAula;
        this.dataMatricula = dataMatricula;
        this.diaVencimento = diaVencimento;
        this.aluno = aluno;
        this.turma = turma;
    }

    public int getIdMatriculaAula() {
        return idMatriculaAula;
    }

    public void setIdMatriculaAula(int idMatriculaAula) {
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

    public Aluno getAluno() throws ClassNotFoundException, SQLException {
        if ((this.idAluno != 0) && (this.aluno == null)) {
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

    public Turma getTurma() throws ClassNotFoundException, SQLException {
        if ((this.idTurma != 0) && (this.turma == null)) {
            this.turma = Turma.obterTurma(this.idTurma);
        }
        return this.turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public static int getMatriculados(int idTurma) throws ClassNotFoundException, SQLException {
        return MatriculaAulaDAO.getMatriculados(idTurma);
    }

    public static boolean matriculado(int idAluno, int idTurma) throws ClassNotFoundException, SQLException {
        return MatriculaAulaDAO.matriculado(idAluno, idTurma);
    }

    public static MatriculaAula obterMatriculaAula(int idMatriculaAula) throws ClassNotFoundException, SQLException {
        return MatriculaAulaDAO.obterMatriculaAula(idMatriculaAula);
    }

    public static List<MatriculaAula> obterMatriculasAula() throws ClassNotFoundException, SQLException {
        return MatriculaAulaDAO.obterMatriculasAula();
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        MatriculaAulaDAO.gravar(this);
    }

    public void editar() throws SQLException, ClassNotFoundException {
        MatriculaAulaDAO.editar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        MatriculaAulaDAO.excluir(this);
    }
}
