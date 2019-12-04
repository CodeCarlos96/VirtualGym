package model;

import dao.PagamentoAulaDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PagamentoAula extends Pagamento {
    private int idPagamentoAula;
    
    private MatriculaAula matriculaAula;
    private int idMatriculaAula;

    public PagamentoAula(int idPagamentoAula, MatriculaAula matriculaAula, int idPagamento, int tipoPagamento, int parcelas, float valorPagamento, Date dataPagamento) {
        super(idPagamento, tipoPagamento, parcelas, valorPagamento, dataPagamento);
        this.idPagamentoAula = idPagamentoAula;
        this.matriculaAula = matriculaAula;
    }

    public int getIdPagamentoAula() {
        return idPagamentoAula;
    }

    public void setIdPagamentoAula(int idPagamentoAula) {
        this.idPagamentoAula = idPagamentoAula;
    }

    public MatriculaAula getMatriculaAula() throws ClassNotFoundException, SQLException {
        if ((this.getIdMatriculaAula() != 0) && (this.matriculaAula == null)) {
            this.matriculaAula = MatriculaAula.obterMatriculaAula(this.idMatriculaAula);
        }
        return this.matriculaAula;
    }

    public void setMatriculaAula(MatriculaAula matriculaAula) {
        this.matriculaAula = matriculaAula;
    }

    public int getIdMatriculaAula() {
        return idMatriculaAula;
    }

    public void setIdMatriculaAula(int idMatriculaAula) {
        this.idMatriculaAula = idMatriculaAula;
    }

    public static PagamentoAula obterPagamentoAula(int idPagamentoAula) throws ClassNotFoundException, SQLException{
        return PagamentoAulaDAO.obterPagamentoAula(idPagamentoAula);
    }
    
    public static List<PagamentoAula> obterPagamentoAulas() throws ClassNotFoundException, SQLException{
        return PagamentoAulaDAO.obterPagamentoAulas();
    }
    
    @Override
    public void gravar() throws SQLException, ClassNotFoundException{
        PagamentoAulaDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        PagamentoAulaDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        PagamentoAulaDAO.excluir(this);
    }
}
