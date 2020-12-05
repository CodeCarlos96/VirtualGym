package model;

import dao.ExercicioDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Exercicio")
public class Exercicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExercicio;
    private String nome;
    private String tipoTreino;

    public Exercicio() {
    }

    public Exercicio(Integer idExercicio, String nome, String tipoTreino) {
        this.idExercicio = idExercicio;
        this.nome = nome;
        this.tipoTreino = tipoTreino;
    }

    public Integer getIdExercicio() {
        return idExercicio;
    }

    public void setIdExercicio(Integer idExercicio) {
        this.idExercicio = idExercicio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoTreino() {
        return tipoTreino;
    }

    public void setTipoTreino(String tipoTreino) {
        this.tipoTreino = tipoTreino;
    }

    public static Exercicio obterExercicio(Integer idExercicio) {
        return ExercicioDAO.getInstancia().obterExercicio(idExercicio);
    }

    public static List<Exercicio> obterExercicios() {
        return ExercicioDAO.getInstancia().obterExercicios();
    }

    public static List<Exercicio> obterExerciciosAerobicos() {
        return ExercicioDAO.obterExerciciosAerobicos();
    }

    public static List<Exercicio> obterExerciciosMusculacao() {
        return ExercicioDAO.obterExerciciosMusculacao();
    }

    public Exercicio gravar() {
        return ExercicioDAO.getInstancia().gravar(this);
    }

    public Exercicio excluir() {
        return ExercicioDAO.getInstancia().excluir(this.idExercicio);
    }
}
