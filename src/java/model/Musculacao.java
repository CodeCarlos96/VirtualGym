package model;

import dao.MusculacaoDAO;
import java.io.Serializable;
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
@Table(name = "Musculacao")
public class Musculacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMusculacao;
    private int ordem;
    private int series;
    private int peso;
    private int repeticoes;

    @OneToOne
    private FichaTreino fichaTreino;
    @ManyToOne
    private Exercicio exercicio;

    public Musculacao() {
    }

    public Musculacao(Integer idMusculacao, int ordem, int series, int peso, int repeticoes, FichaTreino fichaTreino, Exercicio exercicio) {
        this.idMusculacao = idMusculacao;
        this.ordem = ordem;
        this.series = series;
        this.peso = peso;
        this.repeticoes = repeticoes;
        this.fichaTreino = fichaTreino;
        this.exercicio = exercicio;
    }

    public Integer getIdMusculacao() {
        return idMusculacao;
    }

    public void setIdMusculacao(Integer idMusculacao) {
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

    public FichaTreino getFichaTreino() {
        return fichaTreino;
    }

    public void setFichaTreino(FichaTreino fichaTreino) {
        this.fichaTreino = fichaTreino;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public static int obterOrdem(Integer idFichaTreino) {
        return MusculacaoDAO.getInstancia().obterOrdem(idFichaTreino);
    }

    public static Musculacao obterMusculacao(Integer idMusculacao) {
        return MusculacaoDAO.getInstancia().obterMusculacao(idMusculacao);
    }

    public static List<Musculacao> obterMusculacoes(Integer idFichaTreino) {
        return MusculacaoDAO.getInstancia().obterMusculacaos(idFichaTreino);
    }

    public Musculacao gravar() {
        return MusculacaoDAO.getInstancia().gravar(this);
    }

    public Musculacao excluir() {
        return MusculacaoDAO.getInstancia().excluir(this.idMusculacao);
    }
    
    public static void excluirExerciciosFicha(FichaTreino fichaTreino, EntityManager em) throws Exception{
        MusculacaoDAO.getInstancia().excluirExerciciosFicha(fichaTreino, em);
    }
}
