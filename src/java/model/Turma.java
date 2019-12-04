package model;

import dao.TurmaDAO;
import java.sql.SQLException;
import java.util.List;

public class Turma {

    private int idTurma;

    private Professor professor;
    private int idProfessor;
    private Sala sala;
    private int idSala;
    private Aula aula;
    private int idAula;

    public Turma(int idTurma, Professor professor, Sala sala, Aula aula) {
        this.idTurma = idTurma;
        this.professor = professor;
        this.sala = sala;
        this.aula = aula;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public Professor getProfessor() throws ClassNotFoundException, SQLException {
        if ((this.getIdProfessor() != 0) && (this.professor == null)) {
            this.professor = Professor.obterProfessor(this.idProfessor);
        }
        return this.professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Sala getSala() throws ClassNotFoundException, SQLException {
        if ((this.getIdSala() != 0) && (this.sala == null)) {
            this.sala = Sala.obterSala(this.idSala);
        }
        return this.sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Aula getAula() throws ClassNotFoundException, SQLException {
        if ((this.getIdAula() != 0) && (this.aula == null)) {
            this.aula = Aula.obterAula(this.idAula);
        }
        return this.aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public int getMatriculados() throws ClassNotFoundException, SQLException {
        return MatriculaAula.getMatriculados(this.idTurma);
    }

    public static Turma obterTurma(int idTurma) throws ClassNotFoundException, SQLException {
        return TurmaDAO.obterTurma(idTurma);
    }

    public static List<Turma> obterTurmas() throws ClassNotFoundException, SQLException {
        return TurmaDAO.obterTurmas();
    }

    public static List<Turma> obterTurmasDisponiveis() throws ClassNotFoundException, SQLException {
        return TurmaDAO.obterTurmasDisponiveis();
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        TurmaDAO.gravar(this);
    }

    public void editar() throws SQLException, ClassNotFoundException {
        TurmaDAO.editar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        TurmaDAO.excluir(this);
    }
}
