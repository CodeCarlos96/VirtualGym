/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.PlanoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Plano {
    private int idPlano;
    private String nome;
    private float valor;
    private float taxaAdesao;
    private int parcelas;
    private String tipo;
    private float taxaJuros;

    public Plano(int idPlano, String nome, float valor, float taxaAdesao, int parcelas, String tipo, float taxaJuros) {
        this.idPlano = idPlano;
        this.nome = nome;
        this.valor = valor;
        this.taxaAdesao = taxaAdesao;
        this.parcelas = parcelas;
        this.taxaJuros = taxaJuros;
        this.tipo = tipo;
    }

    public float getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(float taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int id) {
        this.idPlano = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getTaxaAdesao() {
        return taxaAdesao;
    }

    public void setTaxaAdesao(float taxaAdesao) {
        this.taxaAdesao = taxaAdesao;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public static Plano obterPlano(int idPlano) throws ClassNotFoundException, SQLException{
        return PlanoDAO.obterPlano(idPlano);
    }
    
    public static List<Plano> obterPlanos() throws ClassNotFoundException, SQLException{
        return PlanoDAO.obterPlanos();
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        PlanoDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        PlanoDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        PlanoDAO.excluir(this);
    }
}
