package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Administrador;
import model.Usuario;

public class AdministradorDAO {

    public static void gravar(Administrador administrador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            Usuario usuario = new Usuario(administrador.getIdAdministrador(), administrador.getEmail(), administrador.getSenha(), administrador.getNome(), administrador.getCpf(),
                    administrador.getRg(), administrador.getSexo(), administrador.getDataNascimento(), administrador.getStatus(), administrador.getTelefone(), administrador.getEndereco()) {
            };
            usuario.gravar();

            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into Administrador (idAdministrador, dataAdmissao, Usuario_idUsuario) values (?, ?, ?)"
            );

            comando.setInt(1, administrador.getIdAdministrador());
            comando.setDate(2, new java.sql.Date(administrador.getDataAdmissao().getTime()));
            comando.setInt(3, usuario.getIdUsuario());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Administrador administrador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            Usuario usuario = new Usuario(administrador.getIdAdministrador(), administrador.getEmail(), administrador.getSenha(), administrador.getNome(), administrador.getCpf(),
                    administrador.getRg(), administrador.getSexo(), administrador.getDataNascimento(), administrador.getStatus(), administrador.getTelefone(), administrador.getEndereco()) {
            };
            usuario.editar();

            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update Administrador set Usuario_idUsuario=?, dataAdmissao=? where idAdministrador=?"
            );
            comando.setInt(1, usuario.getIdUsuario());
            comando.setDate(2, new java.sql.Date(administrador.getDataAdmissao().getTime()));
            comando.setInt(3, administrador.getIdAdministrador());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Administrador administrador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();

            stringSQL = "delete from Administrador where idAdministrador = " + administrador.getIdAdministrador();
            comando.execute(stringSQL);

            stringSQL = "delete from Usuario where idUsuario = " + administrador.getIdUsuario();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<Administrador> obterAdministradores()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Administrador> administradores = new ArrayList<>();
        Administrador administrador;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from administrador join usuario on administrador.Usuario_idUsuario = usuario.idUsuario order by nome");
            while (rs.next()) {
                administrador = instanciarAdministrador(rs);
                administradores.add(administrador);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return administradores;
    }

    public static Administrador obterAdministrador(int idAdministrador) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Administrador administrador = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from administrador join usuario on administrador.Usuario_idUsuario = usuario.idUsuario"
                    + " where idAdministrador = " + idAdministrador);
            rs.first();
            administrador = instanciarAdministrador(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return administrador;
    }

    public static Administrador instanciarAdministrador(ResultSet rs) throws SQLException {
        Administrador administrador;
        try {
            administrador = new Administrador(rs.getInt("idAdministrador"),
                    rs.getDate("dataAdmissao"),
                    rs.getInt("Usuario_idUsuario"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getString("sexo"),
                    rs.getDate("dataNascimento"),
                    rs.getString("status"),
                    rs.getString("telefone"), null);
            administrador.setIdEndereco(rs.getInt("Endereco_idEndereco"));
        } catch (SQLException ex) {
            administrador = null;
        }
        return administrador;
    }
}
