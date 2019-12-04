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
import model.Usuario;

public class UsuarioDAO {

    public static void gravar(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into Usuario (idUsuario, email, senha, nome, cpf, rg, sexo,"
                    + " dataNascimento, status, telefone, Endereco_idEndereco)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            comando.setInt(1, usuario.getIdUsuario());
            comando.setString(2, usuario.getEmail());
            comando.setString(3, usuario.getSenha());
            comando.setString(4, usuario.getNome());
            comando.setString(5, usuario.getCpf());
            comando.setString(6, usuario.getRg());
            comando.setString(7, usuario.getSexo());
            comando.setDate(8, new java.sql.Date(usuario.getDataNascimento().getTime()));
            comando.setString(9, usuario.getStatus());
            comando.setString(10, usuario.getTelefone());

            if (usuario.getEndereco() == null) {
                comando.setNull(11, Types.INTEGER);
            } else {
                comando.setInt(11, usuario.getEndereco().getIdEndereco());
            }

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update Usuario set email=?, senha=?, nome=?, cpf=?, rg=?, sexo=?,"
                    + " dataNascimento=?, status=?, telefone=?, Endereco_idEndereco=? where idUsuario=?"
            );
            comando.setString(1, usuario.getEmail());
            comando.setString(2, usuario.getSenha());
            comando.setString(3, usuario.getNome());
            comando.setString(4, usuario.getCpf());
            comando.setString(5, usuario.getRg());
            comando.setString(6, usuario.getSexo());
            comando.setDate(7, new java.sql.Date(usuario.getDataNascimento().getTime()));
            comando.setString(8, usuario.getStatus());
            comando.setString(9, usuario.getTelefone());
            if (usuario.getEndereco() == null) {
                comando.setNull(10, Types.INTEGER);
            } else {
                comando.setInt(10, usuario.getEndereco().getIdEndereco());
            }
            comando.setInt(11, usuario.getIdUsuario());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;
        String stringSQL;
        try {
            stringSQL = "delete from Usuario where idUsuario = " + usuario.getIdUsuario();

            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Usuario obterUsuario(int idUsuario)
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Usuario usuario = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from usuario where idUsuario = " + idUsuario);
            rs.first();

            usuario = instanciarUsuario(rs);

        } finally {
            fecharConexao(conexao, comando);
        }
        return usuario;
    }

    public static List<Usuario> obterUsuarios()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from usuario");
            while (rs.next()) {
                usuario = instanciarUsuario(rs);
                usuarios.add(usuario);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return usuarios;
    }

    public static Usuario instanciarUsuario(ResultSet rs) throws SQLException {
        Usuario usuario;
        try {
            usuario = new Usuario(rs.getInt("idUsuario"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getString("sexo"),
                    rs.getDate("dataNascimento"),
                    rs.getString("status"),
                    rs.getString("telefone"), null) {
            };
            usuario.setIdEndereco(rs.getInt("endereco_idEndereco"));
        } catch (SQLException ex) {
            usuario = null;
        }
        return usuario;
    }
}
