package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aula;

public class AulaDAO {

    public static void gravar(Aula aula) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into Aula (idAula, nome, descricao, valor, taxaJuros) values (?, ?, ?, ?, ?)"
            );
            comando.setInt(1, aula.getIdAula());
            comando.setString(2, aula.getNome());
            comando.setString(3, aula.getDescricao());
            comando.setFloat(4, aula.getValor());
            comando.setFloat(5, aula.getTaxaJuros());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Aula aula) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update Aula set nome=?, descricao=?, valor=?, taxaJuros=? where idAula=?"
            );
            comando.setString(1, aula.getNome());
            comando.setString(2, aula.getDescricao());
            comando.setFloat(3, aula.getValor());
            comando.setFloat(4, aula.getTaxaJuros());
            comando.setInt(5, aula.getIdAula());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Aula aula) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from aula where idAula = " + aula.getIdAula();
            comando.execute(stringSQL);

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<Aula> obterAulas()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Aula> aulas = new ArrayList<Aula>();
        Aula aula = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Aula order by nome");
            while (rs.next()) {
                aula = instanciarAula(rs);
                aulas.add(aula);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return aulas;
    }

    public static Aula obterAula(int idAula) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Aula aula = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from aula where idAula = " + idAula);
            rs.first();
            aula = instanciarAula(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return aula;
    }

    public static Aula instanciarAula(ResultSet rs) throws SQLException {
        Aula aula;
        try {
            aula = new Aula(rs.getInt("idAula"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getFloat("valor"),
                    rs.getFloat("taxaJuros"));
        } catch (SQLException ex) {
            aula = null;
        }
        return aula;
    }
}
