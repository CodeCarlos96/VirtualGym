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
import model.MatriculaAcademia;

public class MatriculaAcademiaDAO {

    public static void gravar(MatriculaAcademia matriculaAcademia) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into MatriculaAcademia (idMatriculaAcademia, dataMatricula, diaVencimento, Plano_idPlano, Aluno_idAluno) values (?, ?, ?, ?, ?)"
            );

            comando.setInt(1, matriculaAcademia.getIdMatriculaAcademia());
            comando.setDate(2, new java.sql.Date(matriculaAcademia.getDataMatricula().getTime()));
            comando.setInt(3, matriculaAcademia.getDiaVencimento());

            if (matriculaAcademia.getPlano() == null) {
                comando.setNull(4, Types.INTEGER);
            } else {
                comando.setInt(4, matriculaAcademia.getPlano().getIdPlano());
            }
            if (matriculaAcademia.getAluno() == null) {
                comando.setNull(5, Types.INTEGER);
            } else {
                comando.setInt(5, matriculaAcademia.getAluno().getIdAluno());
            }

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(MatriculaAcademia matriculaAcademia) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update MatriculaAcademia set dataMatricula=?, diaVencimento=?, Plano_idPlano=?, Aluno_idAluno=? where idMatriculaAcademia=?"
            );
            comando.setDate(1, new java.sql.Date(matriculaAcademia.getDataMatricula().getTime()));
            comando.setInt(2, matriculaAcademia.getDiaVencimento());

            if (matriculaAcademia.getPlano() == null) {
                comando.setNull(3, Types.INTEGER);
            } else {
                comando.setInt(3, matriculaAcademia.getPlano().getIdPlano());
            }
            if (matriculaAcademia.getAluno() == null) {
                comando.setNull(4, Types.INTEGER);
            } else {
                comando.setInt(4, matriculaAcademia.getAluno().getIdAluno());
            }
            comando.setInt(5, matriculaAcademia.getIdMatriculaAcademia());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(MatriculaAcademia matriculaAcademia) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from MatriculaAcademia where idMatriculaAcademia = " + matriculaAcademia.getIdMatriculaAcademia();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<MatriculaAcademia> obterMatriculasAcademia()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<MatriculaAcademia> matriculaAcademias = new ArrayList<MatriculaAcademia>();
        MatriculaAcademia matriculaAcademia = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from MatriculaAcademia");
            while (rs.next()) {
                matriculaAcademia = instanciarMatriculaAcademia(rs);
                matriculaAcademias.add(matriculaAcademia);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return matriculaAcademias;
    }

    public static MatriculaAcademia obterMatriculaAcademia(int idMatriculaAcademia) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        MatriculaAcademia matriculaAcademia = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from matriculaAcademia where idMatriculaAcademia = " + idMatriculaAcademia);
            rs.first();
            matriculaAcademia = instanciarMatriculaAcademia(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return matriculaAcademia;
    }

    public static MatriculaAcademia instanciarMatriculaAcademia(ResultSet rs) throws SQLException {
        MatriculaAcademia matriculaAcademia;
        try {
            matriculaAcademia = new MatriculaAcademia(rs.getInt("idMatriculaAcademia"),
                    rs.getDate("dataMatricula"),
                    rs.getInt("diaVencimento"), null, null);

            matriculaAcademia.setIdAluno(rs.getInt("Aluno_idAluno"));
            matriculaAcademia.setIdPlano(rs.getInt("Plano_idPlano"));
        } catch (SQLException ex) {
            matriculaAcademia = null;
        }
        return matriculaAcademia;
    }
}
