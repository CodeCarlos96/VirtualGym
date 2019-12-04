package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Pagamento;

public class PagamentoDAO {
    public static void gravar(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into PagamentoAula (idPagamento, tipoPagamento, parcelas, valorPagamento, dataPagamento)"
                    + " values (?, ?, ?, ?, ?)"
            );
            comando.setInt(1, pagamento.getIdPagamento());
            comando.setInt(2, pagamento.getTipoPagamento());
            comando.setInt(3, pagamento.getParcelas());
            comando.setFloat(4, pagamento.getValorPagamento());
            comando.setDate(5, (Date) pagamento.getDataPagamento());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }
    
    public static void editar(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update PagamentoAula set tipoPagamento=?, parcelas=?, valorPagamento=?, dataPagamento=? where idPagamento=?"
            );
            comando.setInt(1, pagamento.getTipoPagamento());
            comando.setInt(2, pagamento.getParcelas());
            comando.setFloat(3, pagamento.getValorPagamento());
            comando.setDate(4, (Date) pagamento.getDataPagamento());
            comando.setInt(5, pagamento.getIdPagamento());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }
    
    public static void excluir(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Pagamento where idPagamento = "+pagamento.getIdPagamento();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<Pagamento> obterPagamentos()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Pagamento> Pagamentos = new ArrayList<Pagamento>();
        Pagamento Pagamento = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Pagamento");
            while (rs.next()) {
                Pagamento = instanciarPagamento(rs);
                Pagamentos.add(Pagamento);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return Pagamentos;
    }

    public static Pagamento obterPagamento(int idPagamento) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Pagamento pagamento = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from pagamento where idPagamento = " + idPagamento);
            rs.first();
            pagamento = instanciarPagamento(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return pagamento;
    }

    public static Pagamento instanciarPagamento(ResultSet rs) throws SQLException {
        Pagamento pagamento = new Pagamento(rs.getInt("idPagamento"),
                rs.getInt("tipoPagamento"),
                rs.getInt("parcelas"),
                rs.getFloat("valorPagamento"),
                rs.getDate("dataPagamento")) {
        };

        return pagamento;
    }

}
