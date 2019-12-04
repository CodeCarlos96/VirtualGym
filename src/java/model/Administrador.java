package model;

import dao.AdministradorDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Administrador extends Usuario {

    private int idAdministrador;
    private Date dataAdmissao;

    public Administrador(int idAdministrador, Date dataAdmissao, int idUsuario, String email, String senha, String nome, String cpf, String rg, String sexo, Date dataNascimento, String status, String telefone, Endereco endereco) {
        super(idUsuario, email, senha, nome, cpf, rg, sexo, dataNascimento, status, telefone, endereco);
        this.idAdministrador = idAdministrador;
        this.dataAdmissao = dataAdmissao;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public static Administrador obterAdministrador(int idAdministrador) throws ClassNotFoundException, SQLException {
        return AdministradorDAO.obterAdministrador(idAdministrador);
    }

    public static List<Administrador> obterAdministradores() throws ClassNotFoundException, SQLException {
        return AdministradorDAO.obterAdministradores();
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    @Override
    public void gravar() throws SQLException, ClassNotFoundException {
        AdministradorDAO.gravar(this);
    }

    @Override
    public void editar() throws SQLException, ClassNotFoundException {
        AdministradorDAO.editar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        AdministradorDAO.excluir(this);
    }
}
