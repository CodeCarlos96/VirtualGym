package model;

import dao.FichaTreinoDAO;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FichaTreino")
public class FichaTreino implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFichaTreino;
    private Date dataInicio;
    private Date dataReavaliacao;
    private String dias;
    private String observacao;
    @ManyToOne
    private Aluno aluno;
    @ManyToOne
    private Professor professor;

    public FichaTreino() {
    }

    public FichaTreino(Integer idFichaTreino, Date dataInicio, Date dataReavaliacao, String dias, String observacao, Aluno aluno, Professor professor) {
        this.idFichaTreino = idFichaTreino;
        this.dataInicio = dataInicio;
        this.dataReavaliacao = dataReavaliacao;
        this.dias = dias;
        this.observacao = observacao;
        this.aluno = aluno;
        this.professor = professor;
    }

    public Integer getIdFichaTreino() {
        return idFichaTreino;
    }

    public void setIdFichaTreino(Integer idFichaTreino) {
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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public static FichaTreino obterFichaTreino(Integer idFichaTreino) {
        return FichaTreinoDAO.getInstancia().obterFichaTreino(idFichaTreino);
    }

    public static List<FichaTreino> obterFichaTreinos() {
        return FichaTreinoDAO.getInstancia().obterFichaTreinos();
    }

    public FichaTreino gravar() {
        return FichaTreinoDAO.getInstancia().gravar(this);
    }

    public FichaTreino excluir() {
        return FichaTreinoDAO.getInstancia().excluir(this.idFichaTreino);
    }
    
    public static void excluirFichaAluno(Aluno aluno, EntityManager em) throws Exception {
        FichaTreinoDAO.getInstancia().excluirFichaAluno(aluno, em);
    }
}
