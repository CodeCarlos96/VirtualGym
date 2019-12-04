package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Entrada;

public class EntradaDAO {

    public static void gravar(Entrada entrada) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into Entrada (idEntrada, dataEntrada, diaVencimento, Aluno_idAluno) values (?, ?, ?)"
            );

            comando.setInt(1, entrada.getIdEntrada());
            comando.setDate(2, (Date) entrada.getDataEntrada());

            if (entrada.getAluno() == null) {
                comando.setNull(3, Types.INTEGER);
            } else {
                comando.setInt(3, entrada.getAluno().getIdAluno());
            }

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Entrada entrada) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update Entrada set dataEntrada=?, diaVencimento=?, Aluno_idAluno=? where idEntrada=?"
            );
            comando.setDate(1, (Date) entrada.getDataEntrada());

            if (entrada.getAluno() == null) {
                comando.setNull(2, Types.INTEGER);
            } else {
                comando.setInt(2, entrada.getAluno().getIdAluno());
            }
            comando.setInt(3, entrada.getIdEntrada());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Entrada entrada) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Entrada where idEntrada = " + entrada.getIdEntrada();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<Entrada> obterEntradas()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Entrada> entradas = new ArrayList<Entrada>();
        Entrada entrada = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Entrada");
            while (rs.next()) {
                entrada = instanciarEntrada(rs);
                entradas.add(entrada);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return entradas;
    }

    public static Entrada obterEntrada(int idEntrada) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Entrada entrada = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from entrada where idEntrada = " + idEntrada);
            rs.first();
            entrada = instanciarEntrada(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return entrada;
    }

    public static Entrada instanciarEntrada(ResultSet rs) throws SQLException {
        Entrada entrada;
        try {
            entrada = new Entrada(rs.getInt("idEntrada"),
                    rs.getDate("dataEntrada"),
                    rs.getString("horarioEntrada"), null);

            entrada.setIdAluno(rs.getInt("Aluno_idAluno"));
        } catch (SQLException ex) {
            entrada = null;
        }
        return entrada;
    }
}
