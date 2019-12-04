package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Sala;

public class SalaDAO {

    public static void gravar(Sala sala) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into sala (idSala, capacidade, descricao, nome) values (?, ?, ?, ?)"
            );
            comando.setInt(1, sala.getIdSala());
            comando.setInt(2, sala.getCapacidade());
            comando.setString(3, sala.getDescricao());
            comando.setString(4, sala.getNome());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }

    }

    public static void editar(Sala sala) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update sala set capacidade=?, descricao=?, nome=? where idSala=?"
            );
            comando.setInt(1, sala.getCapacidade());
            comando.setString(2, sala.getDescricao());
            comando.setString(3, sala.getNome());
            comando.setInt(4, sala.getIdSala());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }

    }

    public static void excluir(Sala sala) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from sala where idSala = " + sala.getIdSala();
            comando.execute(stringSQL);

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<Sala> obterSalas()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Sala> salas = new ArrayList<Sala>();
        Sala sala = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from sala order by nome");
            while (rs.next()) {
                sala = instanciarSala(rs);
                salas.add(sala);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return salas;
    }

    public static Sala obterSala(int idSala) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Sala sala = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from sala where idSala = " + idSala);
            rs.first();
            sala = instanciarSala(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return sala;
    }

    public static Sala instanciarSala(ResultSet rs) throws SQLException {
        Sala sala;
        try {
            sala = new Sala(rs.getInt("idSala"),
                    rs.getInt("capacidade"),
                    rs.getString("descricao"),
                    rs.getString("nome"));
        } catch (SQLException ex) {
            sala = null;
        }
        return sala;
    }

}
