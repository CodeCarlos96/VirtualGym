package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.PagamentoAula;

public class PagamentoAulaDAO {

    public static void gravar(PagamentoAula pagamentoAula) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into PagamentoAula (idPagamentoAula, Pagamento_idPagamento, MatriculaAula_idMatriculaAula)"
                    + " values (?, ?, ?)"
            );
            comando.setInt(1, pagamentoAula.getIdPagamentoAula());
            comando.setInt(2, pagamentoAula.getIdPagamento());

            if (pagamentoAula.getMatriculaAula() == null) {
                comando.setNull(3, Types.INTEGER);
            } else {
                comando.setInt(3, pagamentoAula.getMatriculaAula().getIdMatriculaAula());
            }

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }
    
    public static void editar(PagamentoAula pagamentoAula) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update PagamentoAula set Pagamento_idPagamento=?, MatriculaAula_idMatriculaAula=? where idPagamentoAula=?"
            );
            comando.setInt(1, pagamentoAula.getIdPagamento());

            if (pagamentoAula.getMatriculaAula() == null) {
                comando.setNull(2, Types.INTEGER);
            } else {
                comando.setInt(2, pagamentoAula.getMatriculaAula().getIdMatriculaAula());
            }
            comando.setInt(3, pagamentoAula.getIdPagamentoAula());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(PagamentoAula pagamentoAula) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            
            stringSQL = "delete from PagamentoAula where idPagamentoAula = " + pagamentoAula.getIdPagamentoAula();
            comando.execute(stringSQL);
            
            stringSQL = "delete from Pagamento where idPagamento = " + pagamentoAula.getIdPagamento();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static PagamentoAula obterPagamentoAula(int idPagamentoAula) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        PagamentoAula pagamentoAula = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from pagamentoAula"
                    + " join pagamento on pagamentoAula.Pagamento_idPagamento = pagamento.idPagamento where idPagamentoAula = " + idPagamentoAula);
            rs.first();
            pagamentoAula = instanciarPagamentoAula(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return pagamentoAula;
    }

    public static List<PagamentoAula> obterPagamentoAulas()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<PagamentoAula> pagamentoAulas = new ArrayList<PagamentoAula>();
        PagamentoAula pagamentoAula = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from PagamentoAula"
                    + " join pagamento on pagamentoAula.Pagamento_idPagamento = pagamento.idPagamento");
            while (rs.next()) {
                pagamentoAula = instanciarPagamentoAula(rs);
                pagamentoAulas.add(pagamentoAula);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return pagamentoAulas;
    }

    public static PagamentoAula instanciarPagamentoAula(ResultSet rs) throws SQLException {
        PagamentoAula pagamentoAula = new PagamentoAula(rs.getInt("idPagamentoAula"),
                null,
                rs.getInt("idPagamento"),
                rs.getInt("tipoPagamento"),
                rs.getInt("parcelas"),
                rs.getFloat("valorPagamento"),
                rs.getDate("dataPagamento")) {
        };

        pagamentoAula.setIdMatriculaAula(rs.getInt("MatriculaAula_idMatriculaAula"));

        return pagamentoAula;
    }
}
