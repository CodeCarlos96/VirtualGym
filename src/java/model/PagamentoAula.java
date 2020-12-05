package model;

import dao.PagamentoAulaDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PagamentoAula")
public class PagamentoAula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPagamentoAula;

    @ManyToOne
    private MatriculaAula matriculaAula;
    @OneToOne
    private Pagamento pagamento;

    public PagamentoAula() {
    }

    public PagamentoAula(Integer idPagamentoAula, MatriculaAula matriculaAula, Pagamento pagamento) {
        this.idPagamentoAula = idPagamentoAula;
        this.matriculaAula = matriculaAula;
        this.pagamento = pagamento;
    }

    public Integer getIdPagamentoAula() {
        return idPagamentoAula;
    }

    public void setIdPagamentoAula(Integer idPagamentoAula) {
        this.idPagamentoAula = idPagamentoAula;
    }

    public MatriculaAula getMatriculaAula() {
        return matriculaAula;
    }

    public void setMatriculaAula(MatriculaAula matriculaAula) {
        this.matriculaAula = matriculaAula;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public static PagamentoAula obterPagamentoAula(Integer idPagamentoAula) {
        return PagamentoAulaDAO.getInstancia().obterPagamentoAula(idPagamentoAula);
    }

    public static List<PagamentoAula> obterPagamentoAulas() {
        return PagamentoAulaDAO.getInstancia().obterPagamentoAulas();
    }

    public PagamentoAula gravar() {
        return PagamentoAulaDAO.getInstancia().gravar(this);
    }

    public PagamentoAula excluir() {
        return PagamentoAulaDAO.getInstancia().excluir(this.idPagamentoAula);
    }
}
