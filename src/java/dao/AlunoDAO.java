package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Usuario;

public class AlunoDAO {

    public static void gravar(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();

            Usuario usuario = new Usuario(aluno.getIdAluno(), aluno.getEmail(), aluno.getSenha(), aluno.getNome(), aluno.getCpf(),
                    aluno.getRg(), aluno.getSexo(), aluno.getDataNascimento(), aluno.getStatus(), aluno.getTelefone(), aluno.getEndereco()) {
            };
            usuario.gravar();

            comando = conexao.prepareStatement(
                    "insert into Aluno (idAluno, responsavel, Usuario_idUsuario) values (?, ?, ?)"
            );
            comando.setInt(1, aluno.getIdAluno());
            comando.setString(2, aluno.getResponsavel());
            comando.setInt(3, usuario.getIdUsuario());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }

    }

    public static void editar(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();

            Usuario usuario = new Usuario(aluno.getIdAluno(), aluno.getEmail(), aluno.getSenha(), aluno.getNome(), aluno.getCpf(),
                    aluno.getRg(), aluno.getSexo(), aluno.getDataNascimento(), aluno.getStatus(), aluno.getTelefone(), aluno.getEndereco()) {
            };
            usuario.editar();

            comando = conexao.prepareStatement(
                    "update Aluno set responsavel=?, Usuario_idUsuario=? where idAluno=?"
            );
            comando.setString(1, aluno.getResponsavel());
            comando.setInt(2, usuario.getIdUsuario());
            comando.setInt(3, aluno.getIdAluno());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }

    }

    public static void excluir(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from aluno where idAluno = " + aluno.getIdAluno();
            comando.execute(stringSQL);

            stringSQL = "delete from usuario where idUsuario = " + aluno.getIdUsuario();
            comando.execute(stringSQL);

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<Aluno> obterAlunos()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Aluno> alunos = new ArrayList<Aluno>();
        Aluno aluno = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from aluno join usuario on aluno.Usuario_idUsuario = usuario.idUsuario order by nome");
            while (rs.next()) {
                aluno = instanciarAluno(rs);
                alunos.add(aluno);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return alunos;
    }

    public static Aluno obterAluno(int idAluno) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Aluno aluno = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from aluno join usuario on aluno.Usuario_idUsuario = usuario.idUsuario"
                    + " where idAluno = " + idAluno);
            rs.first();
            aluno = instanciarAluno(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return aluno;
    }

    public static Aluno instanciarAluno(ResultSet rs) throws SQLException {
        Aluno aluno;
        try {
            aluno = new Aluno(rs.getInt("idAluno"),
                    rs.getString("responsavel"),
                    rs.getInt("Usuario_idUsuario"),
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
            aluno.setIdEndereco(rs.getInt("Endereco_idEndereco"));
        } catch (SQLException ex) {
            aluno = null;
        }
        return aluno;
    }

}
