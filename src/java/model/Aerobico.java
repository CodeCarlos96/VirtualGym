package model;

import dao.AerobicoDAO;
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
@Table(name = "Aerobico")
public class Aerobico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAerobico;
    private int ordem;
    private int tempo;
    private int distancia;

    @OneToOne
    private FichaTreino fichaTreino;
    @ManyToOne
    private Exercicio exercicio;

    public Aerobico() {
    }

    public Aerobico(Integer idAerobico, int ordem, int tempo, int distancia, FichaTreino fichaTreino, Exercicio exercicio) {
        this.idAerobico = idAerobico;
        this.ordem = ordem;
        this.tempo = tempo;
        this.distancia = distancia;
        this.fichaTreino = fichaTreino;
        this.exercicio = exercicio;
    }

    public Integer getIdAerobico() {
        return idAerobico;
    }

    public void setIdAerobico(Integer idAerobico) {
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
        return AerobicoDAO.getInstancia().obterOrdem(idFichaTreino);
    }

    public static Aerobico obterAerobico(Integer idAerobico) {
        return AerobicoDAO.getInstancia().obterAerobico(idAerobico);
    }

    public static List<Aerobico> obterAerobicos(Integer idFichaTreino) {
        return AerobicoDAO.getInstancia().obterAerobicos(idFichaTreino);
    }

    public Aerobico gravar() {
        return AerobicoDAO.getInstancia().gravar(this);
    }

    public Aerobico excluir() {
        return AerobicoDAO.getInstancia().excluir(this.idAerobico);
    }
    
    public static void excluirExerciciosFicha(FichaTreino fichaTreino, EntityManager em) throws Exception{
        AerobicoDAO.getInstancia().excluirExerciciosFicha(fichaTreino, em);
    }
}
