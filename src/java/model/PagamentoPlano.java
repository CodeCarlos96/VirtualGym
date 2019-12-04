package model;

import dao.PagamentoPlanoDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PagamentoPlano extends Pagamento {
    private int idPagamentoPlano;
    
    private MatriculaAcademia matriculaAcademia;
    private int idMatriculaAcademia;

    public PagamentoPlano(int idPagamentoPlano, MatriculaAcademia matriculaAcademia, int idPagamento, int tipoPagamento, int parcelas, float valorPagamento, Date dataPagamento) {
        super(idPagamento, tipoPagamento, parcelas, valorPagamento, dataPagamento);
        this.idPagamentoPlano = idPagamentoPlano;
        this.matriculaAcademia = matriculaAcademia;
    }

    public int getIdPagamentoPlano() {
        return idPagamentoPlano;
    }

    public void setIdPagamentoPlano(int idPagamentoPlano) {
        this.idPagamentoPlano = idPagamentoPlano;
    }

    public int getIdMatriculaAcademia() {
        return idMatriculaAcademia;
    }

    public void setIdMatriculaAcademia(int idMatriculaAcademia) {
        this.idMatriculaAcademia = idMatriculaAcademia;
    }

    public MatriculaAcademia getMatriculaAcademia() throws ClassNotFoundException, SQLException {
        if((this.idMatriculaAcademia != 0) && (this.matriculaAcademia == null)){
            this.matriculaAcademia = MatriculaAcademia.obterMatriculaAcademia(this.idMatriculaAcademia);
        }
        return this.matriculaAcademia;
    }

    public void setMatriculaAcademia(MatriculaAcademia matriculaAcademia) {
        this.matriculaAcademia = matriculaAcademia;
    }

    public static PagamentoPlano obterPagamentoPlano(int idPagamentoPlano) throws ClassNotFoundException, SQLException {
        return PagamentoPlanoDAO.obterPagamentoPlano(idPagamentoPlano);
    }

    public static List<PagamentoPlano> obterPagamentoPlanos() throws ClassNotFoundException, SQLException {
        return PagamentoPlanoDAO.obterPagamentoPlanos();
    }
    
    @Override
    public void gravar() throws SQLException, ClassNotFoundException{
        PagamentoPlanoDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        PagamentoPlanoDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        PagamentoPlanoDAO.excluir(this);
    }
}
