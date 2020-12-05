package model;

import dao.TurmaDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Turma")
public class Turma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTurma;

    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Sala sala;
    @ManyToOne
    private Aula aula;

    public Turma() {
    }

    public Turma(Integer idTurma, Professor professor, Sala sala, Aula aula) {
        this.idTurma = idTurma;
        this.professor = professor;
        this.sala = sala;
        this.aula = aula;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public int getMatriculados() {
        return MatriculaAula.getMatriculados(this.idTurma);
    }

    public static Turma obterTurma(Integer idTurma) {
        return TurmaDAO.getInstancia().obterTurma(idTurma);
    }

    public static List<Turma> obterTurmas() {
        return TurmaDAO.getInstancia().obterTurmas();
    }

    public Turma gravar() {
        return TurmaDAO.getInstancia().gravar(this);
    }

    public Turma excluir() {
        return TurmaDAO.getInstancia().excluir(this.idTurma);
    }
}
