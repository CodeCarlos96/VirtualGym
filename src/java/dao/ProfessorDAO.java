package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Professor;
import model.Usuario;

public class ProfessorDAO {

    public static void gravar(Professor professor) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            Usuario usuario = new Usuario(professor.getIdProfessor(), professor.getEmail(), professor.getSenha(), professor.getNome(),
                    professor.getCpf(), professor.getRg(), professor.getSexo(), professor.getDataNascimento(), professor.getStatus(),
                    professor.getTelefone(), professor.getEndereco()) {
            };
            usuario.gravar();

            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into Professor (idProfessor, dataAdmissao, Usuario_idUsuario) "
                    + "values (?, ?, ?)"
            );
            comando.setInt(1, professor.getIdProfessor());
            comando.setDate(2, new java.sql.Date(professor.getDataAdmissao().getTime()));
            comando.setInt(3, usuario.getIdUsuario());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Professor professor) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            Usuario usuario = new Usuario(professor.getIdProfessor(), professor.getEmail(), professor.getSenha(), professor.getNome(),
                    professor.getCpf(), professor.getRg(), professor.getSexo(), professor.getDataNascimento(), professor.getStatus(),
                    professor.getTelefone(), professor.getEndereco()) {
            };
            usuario.editar();

            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update Professor set Usuario_idUsuario=?, dataAdmissao=? where idProfessor=?"
            );
            comando.setInt(1, usuario.getIdUsuario());
            comando.setDate(2, new java.sql.Date(professor.getDataAdmissao().getTime()));
            comando.setInt(3, professor.getIdProfessor());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Professor professor) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();

            stringSQL = "delete from Professor where idProfessor = " + professor.getIdProfessor();
            comando.execute(stringSQL);

            stringSQL = "delete from Usuario where idUsuario = " + professor.getIdUsuario();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Professor obterProfessor(int idProfessor)
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Professor professor = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Professor join usuario on Professor.Usuario_idUsuario = usuario.idUsuario"
                    + " where idProfessor = " + idProfessor);
            rs.first();

            professor = instanciarProfessor(rs);

        } finally {
            fecharConexao(conexao, comando);
        }
        return professor;
    }

    public static List<Professor> obterProfessores()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Professor> professores = new ArrayList<>();
        Professor professor;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Professor join usuario on Professor.Usuario_idUsuario = usuario.idUsuario order by nome");
            while (rs.next()) {
                professor = instanciarProfessor(rs);
                professores.add(professor);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return professores;
    }

    public static Professor instanciarProfessor(ResultSet rs) throws SQLException {
        Professor professor;
        try {
            professor = new Professor(rs.getInt("idProfessor"),
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
            professor.setIdEndereco(rs.getInt("Endereco_idEndereco"));
        } catch (SQLException ex) {
            professor = null;
        }
        return professor;
    }

}
