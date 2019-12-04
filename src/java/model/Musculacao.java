package model;

import dao.MusculacaoDAO;
import java.sql.SQLException;
import java.util.List;

public class Musculacao {

    private int idMusculacao;
    private int ordem;
    private int series;
    private int peso;
    private int repeticoes;

    private FichaTreino fichaTreino;
    private int idFichaTreino;
    private Exercicio exercicio;
    private int idExercicio;

    public Musculacao(int ordem, int series, int peso, int repeticoes, FichaTreino fichaTreino, Exercicio exercicio) {
        this.ordem = ordem;
        this.series = series;
        this.peso = peso;
        this.repeticoes = repeticoes;
        this.fichaTreino = fichaTreino;
        this.exercicio = exercicio;
    }

    public int getIdMusculacao() {
        return idMusculacao;
    }

    public void setIdMusculacao(int idMusculacao) {
        this.idMusculacao = idMusculacao;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
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
        return MusculacaoDAO.obterOrdem(idFichaTreino);
    }

    public static Musculacao obterMusculacao(int idMusculacao) throws ClassNotFoundException, SQLException {
        return MusculacaoDAO.obterMusculacao(idMusculacao);
    }

    public static List<Musculacao> obterMusculacoes(int idFichaTreino) throws ClassNotFoundException, SQLException {
        return MusculacaoDAO.obterMusculacoes(idFichaTreino);
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        MusculacaoDAO.gravar(this);
    }

    public void editar() throws SQLException, ClassNotFoundException {
        MusculacaoDAO.editar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        MusculacaoDAO.excluir(this);
    }
}
