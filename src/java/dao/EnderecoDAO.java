package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Endereco;

public class EnderecoDAO {

    public static void gravar(Endereco endereco) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into Endereco (idEndereco, logradouro, bairro, cidade, uf, cep, complemento, numero)"
                    + " values (? , ?, ?, ?, ?, ?, ?, ?)"
            );
            comando.setInt(1, endereco.getIdEndereco());
            comando.setString(2, endereco.getLogradouro());
            comando.setString(3, endereco.getBairro());
            comando.setString(4, endereco.getCidade());
            comando.setString(5, endereco.getUf());
            comando.setString(6, endereco.getCep());
            comando.setString(7, endereco.getComplemento());
            comando.setString(8, endereco.getNumero());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Endereco endereco) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update Endereco set logradouro=?, bairro=?, cidade=?, uf=?, cep=?, complemento=?, numero=? where idEndereco=?"
            );
            comando.setString(1, endereco.getLogradouro());
            comando.setString(2, endereco.getBairro());
            comando.setString(3, endereco.getCidade());
            comando.setString(4, endereco.getUf());
            comando.setString(5, endereco.getCep());
            comando.setString(6, endereco.getComplemento());
            comando.setString(7, endereco.getNumero());
            comando.setInt(8, endereco.getIdEndereco());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Endereco endereco) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from endereco where idEndereco = " + endereco.getIdEndereco();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<Endereco> obterEnderecos()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Endereco> enderecos = new ArrayList<Endereco>();
        Endereco endereco = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Endereco");
            while (rs.next()) {
                endereco = instanciarEndereco(rs);
                enderecos.add(endereco);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return enderecos;
    }

    public static Endereco obterEndereco(int idEndereco) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Endereco endereco = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from endereco where idEndereco = " + idEndereco);
            rs.first();
            endereco = instanciarEndereco(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return endereco;
    }

    public static Endereco instanciarEndereco(ResultSet rs) throws SQLException {
        Endereco endereco;
        try {
            endereco = new Endereco(rs.getInt("idEndereco"),
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("uf"),
                    rs.getString("cep"),
                    rs.getString("complemento"),
                    rs.getString("numero"));
        } catch (SQLException ex) {
            endereco = null;
        }
        return endereco;
    }
}
