package model;

import dao.AlunoDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Aluno")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluno;
    private String responsavel;
    private String cpfResponsavel;
    @OneToOne
    private Usuario usuario;

    public Aluno() {
    }

    public Aluno(Integer idAluno, String responsavel, String cpfResponsavel, Usuario usuario) {
        this.idAluno = idAluno;
        this.responsavel = responsavel;
        this.cpfResponsavel = cpfResponsavel;
        this.usuario = usuario;
    }

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static Aluno obterAluno(Integer idAluno) {
        return AlunoDAO.getInstancia().obterAluno(idAluno);
    }

    public static List<Aluno> obterAlunos() {
        return AlunoDAO.getInstancia().obterAlunos();
    }

    public static List<Aluno> obterAlunosNome(String nomeAluno) {
        return AlunoDAO.getInstancia().obterAlunosNome(nomeAluno);
    }

    public Aluno gravar() {
        return AlunoDAO.getInstancia().gravar(this);
    }

    public Aluno excluir() {
        return AlunoDAO.getInstancia().excluir(this.idAluno);
    }
}
