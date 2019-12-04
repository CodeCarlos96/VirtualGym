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
import model.PagamentoPlano;

public class PagamentoPlanoDAO {
    public static void gravar(PagamentoPlano pagamentoPlano) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into PagamentoPlano (idPagamentoPlano, Pagamento_idPagamento, MatriculaAcademia_idMatriculaAcademia)"
                    + " values (?, ?, ?)"
            );
            comando.setInt(1, pagamentoPlano.getIdPagamentoPlano());
            comando.setInt(2, pagamentoPlano.getIdPagamento());

            if (pagamentoPlano.getMatriculaAcademia() == null) {
                comando.setNull(3, Types.INTEGER);
            } else {
                comando.setInt(3, pagamentoPlano.getMatriculaAcademia().getIdMatriculaAcademia());
            }

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }
    
    public static void editar(PagamentoPlano pagamentoPlano) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update PagamentoPlano set Pagamento_idPagamento=?, MatriculaAcademia_idMatriculaAcademia=? where idPagamentoPlano=?"
            );
            comando.setInt(1, pagamentoPlano.getIdPagamento());

            if (pagamentoPlano.getMatriculaAcademia() == null) {
                comando.setNull(2, Types.INTEGER);
            } else {
                comando.setInt(2, pagamentoPlano.getMatriculaAcademia().getIdMatriculaAcademia());
            }
            comando.setInt(3, pagamentoPlano.getIdPagamentoPlano());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }
    
    public static void excluir(PagamentoPlano pagamentoPlano) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            
            stringSQL = "delete from PagamentoPlano where idPagamentoPlano = "+pagamentoPlano.getIdPagamentoPlano();
            comando.execute(stringSQL);
            
            stringSQL = "delete from Pagamento where idPagamento = "+pagamentoPlano.getIdPagamento();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static PagamentoPlano obterPagamentoPlano(int idPagamentoPlano) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        PagamentoPlano pagamentoPlano = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from pagamentoPlano"
                    + " join pagamento on pagamentoPlano.Pagamento_idPagamento = pagamento.idPagamento where idPagamentoPlano = " + idPagamentoPlano);
            rs.first();
            pagamentoPlano = instanciarPagamentoPlano(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return pagamentoPlano;
    }

    public static List<PagamentoPlano> obterPagamentoPlanos()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<PagamentoPlano> pagamentoPlanos = new ArrayList<PagamentoPlano>();
        PagamentoPlano pagamentoPlano = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from PagamentoPlano"
                    + " join pagamento on pagamentoPlano.Pagamento_idPagamento = pagamento.idPagamento");
            
            while (rs.next()) {
                pagamentoPlano = instanciarPagamentoPlano(rs);
                pagamentoPlanos.add(pagamentoPlano);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return pagamentoPlanos;
    }

    public static PagamentoPlano instanciarPagamentoPlano(ResultSet rs) throws SQLException {
        PagamentoPlano pagamentoPlano = new PagamentoPlano(rs.getInt("idPagamentoPlano"),
                null,
                rs.getInt("idPagamento"),
                rs.getInt("tipoPagamento"),
                rs.getInt("parcelas"),
                rs.getFloat("valorPagamento"),
                rs.getDate("dataPagamento")) {
        };

        pagamentoPlano.setIdMatriculaAcademia(rs.getInt("MatriculaAcademia_idMatriculaAcademia"));

        return pagamentoPlano;
    }
}
