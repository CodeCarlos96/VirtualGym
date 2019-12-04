/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.PagamentoDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dudu
 */
public abstract class Pagamento {

    private int idPagamento;
    private int tipoPagamento;
    private int parcelas;
    private float valorPagamento;
    private Date dataPagamento;
    private String nomeCartao;
    private String dataValidade;
    private String numeroCartao;
    private String cvv;

    public Pagamento(int idPagamento, int tipoPagamento, int parcelas, float valorPagamento, Date dataPagamento) {
        this.idPagamento = idPagamento;
        this.tipoPagamento = tipoPagamento;
        this.parcelas = parcelas;
        this.valorPagamento = valorPagamento;
        this.dataPagamento = dataPagamento;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(int tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public float getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(float valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public static Pagamento obterPagamento(int idPagamento) throws ClassNotFoundException, SQLException {
        return PagamentoDAO.obterPagamento(idPagamento);
    }

    public static List<Pagamento> obterPagamentos() throws ClassNotFoundException, SQLException {
        return PagamentoDAO.obterPagamentos();
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        PagamentoDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        PagamentoDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        PagamentoDAO.excluir(this);
    }
}
