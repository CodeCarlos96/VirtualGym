
package model;

import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public abstract class Usuario {
    private int idUsuario;
    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private String rg;
    private String sexo;
    private Date dataNascimento;
    private String status;
    private String telefone;

    private int idEndereco;
    private Endereco endereco;

    public Usuario(int idUsuario, String email, String senha, String nome, String cpf, String rg, String sexo, Date dataNascimento, String status, String telefone, Endereco endereco) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.status = status;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Endereco getEndereco() throws ClassNotFoundException, SQLException {
        if ((this.getIdEndereco() != 0) && (this.endereco == null)) {
            this.endereco = Endereco.obterEndereco(this.idEndereco);
        }
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static Usuario obterUsuario(int idUsuario) throws ClassNotFoundException, SQLException {
        return UsuarioDAO.obterUsuario(idUsuario);
    }
    
    public static List<Usuario> obterUsuarios() throws ClassNotFoundException, SQLException {
        return UsuarioDAO.obterUsuarios();
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        UsuarioDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        UsuarioDAO.editar(this);
    }
}