package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Plano;

public class PlanoDAO {

    public static void excluir(Plano plano) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from plano where idPlano = " + plano.getIdPlano();
            comando.execute(stringSQL);

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Plano plano) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update plano set nome=?, valor=?, taxaAdesao=?, parcelas=?, tipo=?, taxaJuros=? where idPlano=?"
            );
            comando.setString(1, plano.getNome());
            comando.setFloat(2, plano.getValor());
            comando.setFloat(3, plano.getTaxaAdesao());
            comando.setInt(4, plano.getParcelas());
            comando.setString(5, plano.getTipo());
            comando.setFloat(6, plano.getTaxaJuros());
            comando.setInt(7, plano.getIdPlano());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void gravar(Plano plano) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into plano (idPlano, nome, valor, taxaAdesao, parcelas, tipo, taxaJuros) "
                    + "values (?, ?, ?, ?, ?, ?, ?)"
            );
            comando.setInt(1, plano.getIdPlano());
            comando.setString(2, plano.getNome());
            comando.setFloat(3, plano.getValor());
            comando.setFloat(4, plano.getTaxaAdesao());
            comando.setInt(5, plano.getParcelas());
            comando.setString(6, plano.getTipo());
            comando.setFloat(7, plano.getTaxaJuros());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }

    }

    public static List<Plano> obterPlanos()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Plano> planos = new ArrayList<Plano>();
        Plano plano = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Plano");
            while (rs.next()) {
                plano = instanciarPlano(rs);
                planos.add(plano);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return planos;
    }

    public static Plano obterPlano(int idPlano) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Plano plano = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from plano where idPlano = " + idPlano);
            rs.first();
            plano = instanciarPlano(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return plano;
    }

    public static Plano instanciarPlano(ResultSet rs) throws SQLException {
        Plano plano;
        try {
            plano = new Plano(rs.getInt("idPlano"),
                    rs.getString("nome"),
                    rs.getFloat("valor"),
                    rs.getFloat("taxaAdesao"),
                    rs.getInt("parcelas"),
                    rs.getString("tipo"),
                    rs.getFloat("taxaJuros"));
        } catch (SQLException ex) {
            plano = null;
        }
        return plano;
    }
}
