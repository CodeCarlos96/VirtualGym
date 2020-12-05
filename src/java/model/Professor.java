package model;

import dao.ProfessorDAO;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Professor")
public class Professor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfessor;
    private Date dataAdmissao;
    @OneToOne
    private Usuario usuario;

    public Professor() {
    }

    public Professor(Integer idProfessor, Date dataAdmissao, Usuario usuario) {
        this.idProfessor = idProfessor;
        this.dataAdmissao = dataAdmissao;
        this.usuario = usuario;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static Professor obterProfessor(Integer idProfessor) {
        return ProfessorDAO.getInstancia().obterProfessor(idProfessor);
    }

    public static List<Professor> obterProfessores(){
        return ProfessorDAO.getInstancia().obterProfessors();
    }

    public Professor gravar(){
        return ProfessorDAO.getInstancia().gravar(this);
    }

    public Professor excluir() {
        return ProfessorDAO.getInstancia().excluir(this.idProfessor);
    }
}
