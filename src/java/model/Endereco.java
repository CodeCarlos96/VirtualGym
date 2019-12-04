/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.EnderecoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Endereco {
    private int idEndereco;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String complemento;
    private String numero;

    public Endereco(int idEndereco, String logradouro, String bairro, String cidade, String uf, String cep, String complemento, String numero) {
        this.idEndereco = idEndereco;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.complemento = complemento;
        this.numero = numero;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
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
    
    public static Endereco obterEndereco(int idEndereco) throws ClassNotFoundException, SQLException{
        return EnderecoDAO.obterEndereco(idEndereco);
    }
    
    public static List<Endereco> obterEnderecos() throws ClassNotFoundException, SQLException{
        return EnderecoDAO.obterEnderecos();
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        EnderecoDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        EnderecoDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        EnderecoDAO.excluir(this);
    }
}
