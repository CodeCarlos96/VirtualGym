package model;

import dao.EnderecoDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String complemento;
    private String numero;

    public Endereco() {
    }

    public Endereco(Integer idEndereco, String logradouro, String bairro, String cidade, String uf, String cep, String complemento, String numero) {
        this.idEndereco = idEndereco;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.complemento = complemento;
        this.numero = numero;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public static Endereco obterEndereco(Integer idEndereco){
        return EnderecoDAO.getInstancia().obterEndereco(idEndereco);
    }

    public static List<Endereco> obterEnderecos() {
        return EnderecoDAO.getInstancia().obterEnderecos();
    }

    public Endereco gravar() {
        return EnderecoDAO.getInstancia().gravar(this);
    }

    public Endereco excluir() {
        return EnderecoDAO.getInstancia().excluir(this.idEndereco);
    }
}
