package model;

import dao.PagamentoDAO;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pagamento")
public class Pagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPagamento;
    private int tipoPagamento;
    private int parcelas;
    private float valorPagamento;
    private Date dataPagamento;
    private String nomeCartao;
    private String dataValidade;
    private String numeroCartao;
    private String cvv;

    public Pagamento() {
    }

    public Pagamento(Integer idPagamento, int tipoPagamento, int parcelas, float valorPagamento, Date dataPagamento, String nomeCartao, String dataValidade, String numeroCartao, String cvv) {
        this.idPagamento = idPagamento;
        this.tipoPagamento = tipoPagamento;
        this.parcelas = parcelas;
        this.valorPagamento = valorPagamento;
        this.dataPagamento = dataPagamento;
        this.nomeCartao = nomeCartao;
        this.dataValidade = dataValidade;
        this.numeroCartao = numeroCartao;
        this.cvv = cvv;
    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
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

    public static Pagamento obterPagamento(Integer idPagamento) {
        return PagamentoDAO.getInstancia().obterPagamento(idPagamento);
    }

    public static List<Pagamento> obterPagamentos() {
        return PagamentoDAO.getInstancia().obterPagamentos();
    }

    public Pagamento gravar() {
        return PagamentoDAO.getInstancia().gravar(this);
    }

    public Pagamento excluir() {
        return PagamentoDAO.getInstancia().excluir(this.idPagamento);
    }
}
