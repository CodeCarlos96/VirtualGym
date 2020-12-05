package model;

import dao.PagamentoPlanoDAO;
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
@Table(name = "PagamentoPlano")
public class PagamentoPlano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPagamentoPlano;

    @ManyToOne
    private MatriculaAcademia matriculaAcademia;
    @OneToOne
    private Pagamento pagamento;

    public PagamentoPlano() {
    }

    public PagamentoPlano(Integer idPagamentoPlano, MatriculaAcademia matriculaAcademia, Pagamento pagamento) {
        this.idPagamentoPlano = idPagamentoPlano;
        this.matriculaAcademia = matriculaAcademia;
        this.pagamento = pagamento;
    }

    public Integer getIdPagamentoPlano() {
        return idPagamentoPlano;
    }

    public void setIdPagamentoPlano(Integer idPagamentoPlano) {
        this.idPagamentoPlano = idPagamentoPlano;
    }

    public MatriculaAcademia getMatriculaAcademia() {
        return matriculaAcademia;
    }

    public void setMatriculaAcademia(MatriculaAcademia matriculaAcademia) {
        this.matriculaAcademia = matriculaAcademia;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public static PagamentoPlano obterPagamentoPlano(Integer idPagamentoPlano) {
        return PagamentoPlanoDAO.getInstancia().obterPagamentoPlano(idPagamentoPlano);
    }

    public static List<PagamentoPlano> obterPagamentoPlanos() {
        return PagamentoPlanoDAO.getInstancia().obterPagamentoPlanos();
    }

    public PagamentoPlano gravar() {
        return PagamentoPlanoDAO.getInstancia().gravar(this);
    }

    public PagamentoPlano excluir() {
        return PagamentoPlanoDAO.getInstancia().excluir(this.idPagamentoPlano);
    }
}
