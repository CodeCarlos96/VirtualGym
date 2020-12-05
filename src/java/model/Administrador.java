package model;

import dao.AdministradorDAO;
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
@Table(name = "Administrador")
public class Administrador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdministrador;
    private Date dataAdmissao;
    @OneToOne
    private Usuario usuario;

    public Administrador() {
    }

    public Administrador(Integer idAdministrador, Date dataAdmissao, Usuario usuario) {
        this.idAdministrador = idAdministrador;
        this.dataAdmissao = dataAdmissao;
        this.usuario = usuario;
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
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

    public static Administrador obterAdministrador(Integer idAdministrador) {
        return AdministradorDAO.getInstancia().obterAdministrador(idAdministrador);
    }

    public static List<Administrador> obterAdministradores() {
        return AdministradorDAO.getInstancia().obterAdministradores();
    }

    public Administrador gravar() {
        return AdministradorDAO.getInstancia().gravar(this);
    }

    public Administrador excluir() {
        return AdministradorDAO.getInstancia().excluir(this.idAdministrador);
    }
}
