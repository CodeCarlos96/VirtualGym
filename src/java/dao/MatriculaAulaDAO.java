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
import model.MatriculaAula;

public class MatriculaAulaDAO {

    public static void gravar(MatriculaAula matriculaAula) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into MatriculaAula (idMatriculaAula, dataMatricula, diaVencimento, Aluno_idAluno, Turma_idTurma) values (?, ?, ?, ?, ?)"
            );

            comando.setInt(1, matriculaAula.getIdMatriculaAula());
            comando.setDate(2, new java.sql.Date(matriculaAula.getDataMatricula().getTime()));
            comando.setInt(3, matriculaAula.getDiaVencimento());

            if (matriculaAula.getAluno() == null) {
                comando.setNull(4, Types.INTEGER);
            } else {
                comando.setInt(4, matriculaAula.getAluno().getIdAluno());
            }
            if (matriculaAula.getTurma() == null) {
                comando.setNull(5, Types.INTEGER);
            } else {
                comando.setInt(5, matriculaAula.getTurma().getIdTurma());
            }

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(MatriculaAula matriculaAula) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update MatriculaAula set dataMatricula=?, diaVencimento=?, Aluno_idAluno=?, Turma_idTurma=? where idMatriculaAula=?"
            );
            comando.setDate(1, new java.sql.Date(matriculaAula.getDataMatricula().getTime()));
            comando.setInt(2, matriculaAula.getDiaVencimento());

            if (matriculaAula.getAluno() == null) {
                comando.setNull(3, Types.INTEGER);
            } else {
                comando.setInt(3, matriculaAula.getAluno().getIdAluno());
            }
            if (matriculaAula.getTurma() == null) {
                comando.setNull(4, Types.INTEGER);
            } else {
                comando.setInt(4, matriculaAula.getTurma().getIdTurma());
            }
            comando.setInt(5, matriculaAula.getIdMatriculaAula());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(MatriculaAula matriculaAula) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from MatriculaAula where idMatriculaAula = " + matriculaAula.getIdMatriculaAula();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<MatriculaAula> obterMatriculasAula()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<MatriculaAula> matriculaAulas = new ArrayList<MatriculaAula>();
        MatriculaAula matriculaAula = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from MatriculaAula");
            while (rs.next()) {
                matriculaAula = instanciarMatriculaAula(rs);
                matriculaAulas.add(matriculaAula);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return matriculaAulas;
    }

    public static MatriculaAula obterMatriculaAula(int idMatriculaAula) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        MatriculaAula matriculaAula = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from matriculaAula where idMatriculaAula = " + idMatriculaAula);
            rs.first();
            matriculaAula = instanciarMatriculaAula(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return matriculaAula;
    }

    public static int getMatriculados(int idTurma) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        int matriculados;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select count(idMatriculaAula) from MatriculaAula where Turma_idTurma = " + idTurma);
            rs.first();
            matriculados = rs.getInt(1);
        } finally {
            fecharConexao(conexao, comando);
        }
        return matriculados;
    }

    public static boolean matriculado(int idAluno, int idTurma) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        boolean matriculado;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select count(idMatriculaAula) from MatriculaAula where Aluno_idAluno=" + idAluno + " and Turma_idTurma=" + idTurma);
            rs.first();
            matriculado = rs.getBoolean(1);
        } finally {
            fecharConexao(conexao, comando);
        }
        return matriculado;
    }

    public static MatriculaAula instanciarMatriculaAula(ResultSet rs) throws SQLException {
        MatriculaAula matriculaAula;
        try {
            matriculaAula = new MatriculaAula(rs.getInt("idMatriculaAula"),
                    rs.getDate("dataMatricula"),
                    rs.getInt("diaVencimento"), null, null);

            matriculaAula.setIdAluno(rs.getInt("Aluno_idAluno"));
            matriculaAula.setIdTurma(rs.getInt("Turma_idTurma"));
        } catch (SQLException ex) {
            matriculaAula = null;
        }
        return matriculaAula;
    }
}
