package model;

import dao.ExercicioDAO;
import java.sql.SQLException;
import java.util.List;

public class Exercicio {

    private int idExercicio;
    private String nome;
    private String tipoTreino;

    public Exercicio(int idExercicio, String nome, String tipoTreino) {
        this.idExercicio = idExercicio;
        this.nome = nome;
        this.tipoTreino = tipoTreino;
    }

    public int getIdExercicio() {
        return idExercicio;
    }

    public void setIdExercicio(int idExercicio) {
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

    public static Exercicio obterExercicio(int idExercicio) throws ClassNotFoundException, SQLException {
        return ExercicioDAO.obterExercicio(idExercicio);
    }

    public static List<Exercicio> obterExercicios() throws ClassNotFoundException, SQLException {
        return ExercicioDAO.obterExercicios();
    }
    
    public static List<Exercicio> obterExerciciosAerobicos() throws ClassNotFoundException, SQLException {
        return ExercicioDAO.obterExerciciosAerobicos();
    }
    
    public static List<Exercicio> obterExerciciosMusculacao() throws ClassNotFoundException, SQLException {
        return ExercicioDAO.obterExerciciosMusculacao();
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        ExercicioDAO.gravar(this);
    }

    public void editar() throws SQLException, ClassNotFoundException {
        ExercicioDAO.editar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        ExercicioDAO.excluir(this);
    }
}
