package model;

import dao.AerobicoDAO;
import java.sql.SQLException;
import java.util.List;

public class Aerobico {

    private int idAerobico;
    private int ordem;
    private int tempo;
    private int distancia;

    private FichaTreino fichaTreino;
    private int idFichaTreino;
    private Exercicio exercicio;
    private int idExercicio;

    public Aerobico(int ordem, int tempo, int distancia, FichaTreino fichaTreino, Exercicio exercicio) {
        this.ordem = ordem;
        this.tempo = tempo;
        this.distancia = distancia;
        this.fichaTreino = fichaTreino;
        this.exercicio = exercicio;
    }

    public int getIdAerobico() {
        return idAerobico;
    }

    public void setIdAerobico(int idAerobico) {
        this.idAerobico = idAerobico;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public FichaTreino getFichaTreino() throws ClassNotFoundException, SQLException {
        if ((this.idFichaTreino != 0) && (this.fichaTreino == null)) {
            this.fichaTreino = FichaTreino.obterFichaTreino(this.idFichaTreino);
        }
        return this.fichaTreino;
    }

    public void setFichaTreino(FichaTreino fichaTreino) {
        this.fichaTreino = fichaTreino;
    }

    public int getIdFichaTreino() {
        return idFichaTreino;
    }

    public void setIdFichaTreino(int idFichaTreino) {
        this.idFichaTreino = idFichaTreino;
    }

    public Exercicio getExercicio() throws ClassNotFoundException, SQLException {
        if ((this.idExercicio != 0) && (this.exercicio == null)) {
            this.exercicio = Exercicio.obterExercicio(this.idExercicio);
        }
        return this.exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public int getIdExercicio() {
        return idExercicio;
    }

    public void setIdExercicio(int idExercicio) {
        this.idExercicio = idExercicio;
    }
    
    public static int obterOrdem(int idFichaTreino) throws ClassNotFoundException, SQLException {
        return AerobicoDAO.obterOrdem(idFichaTreino);
    }

    public static Aerobico obterAerobico(int idAerobico) throws ClassNotFoundException, SQLException {
        return AerobicoDAO.obterAerobico(idAerobico);
    }

    public static List<Aerobico> obterAerobicos(int idFichaTreino) throws ClassNotFoundException, SQLException {
        return AerobicoDAO.obterAerobicos(idFichaTreino);
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        AerobicoDAO.gravar(this);
    }

    public void editar() throws SQLException, ClassNotFoundException {
        AerobicoDAO.editar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        AerobicoDAO.excluir(this);
    }
}
