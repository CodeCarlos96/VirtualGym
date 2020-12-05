package model;

import dao.UsuarioDAO;
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
@Table(name = "Usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private String rg;
    private String sexo;
    private Date dataNascimento;
    private String status;
    private String telefone;
    @OneToOne
    private Endereco endereco;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String email, String senha, String nome, String cpf, String rg, String sexo, Date dataNascimento, String status, String telefone, Endereco endereco) {
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static Usuario obterUsuario(Integer idUsuario) {
        return UsuarioDAO.getInstancia().obterUsuario(idUsuario);
    }

    public static List<Usuario> obterUsuarios() {
        return UsuarioDAO.getInstancia().obterUsuarios();
    }

    public Usuario gravar() {
        return UsuarioDAO.getInstancia().gravar(this);
    }

    public Usuario excluir() {
        return UsuarioDAO.getInstancia().excluir(this.idUsuario);
    }
}
