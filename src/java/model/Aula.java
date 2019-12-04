/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AulaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Aula {
    private int idAula;
    private String nome;
    private String descricao;
    private float valor;
    private float taxaJuros;

    public Aula(int idAula, String nome, String descricao, float valor, float taxaJuros) {
        this.idAula = idAula;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.taxaJuros = taxaJuros;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(float taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public static Aula obterAula(int idAula) throws ClassNotFoundException, SQLException{
        return AulaDAO.obterAula(idAula);
    }
    
    public static List<Aula> obterAulas() throws ClassNotFoundException, SQLException{
        return AulaDAO.obterAulas();
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        AulaDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        AulaDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        AulaDAO.excluir(this);
    }
}
