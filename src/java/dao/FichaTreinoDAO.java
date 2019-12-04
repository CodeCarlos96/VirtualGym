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
import model.FichaTreino;

public class FichaTreinoDAO {

    public static void gravar(FichaTreino fichaTreino) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into FichaTreino (idFichaTreino, dataInicio, dataReavaliacao, dias, observacao, Aluno_idAluno, Professor_idProfessor) "
                    + "values (?, ?, ?, ?, ?, ?, ?)"
            );
            comando.setInt(1, fichaTreino.getIdFichaTreino());
            comando.setDate(2, new java.sql.Date(fichaTreino.getDataInicio().getTime()));
            comando.setDate(3, new java.sql.Date(fichaTreino.getDataReavaliacao().getTime()));
            comando.setString(4, fichaTreino.getDias());
            comando.setString(5, fichaTreino.getObservacao());

            if (fichaTreino.getAluno() == null) {
                comando.setNull(6, Types.INTEGER);
            } else {
                comando.setInt(6, fichaTreino.getAluno().getIdAluno());
            }
            if (fichaTreino.getProfessor() == null) {
                comando.setNull(7, Types.INTEGER);
            } else {
                comando.setInt(7, fichaTreino.getProfessor().getIdProfessor());
            }

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(FichaTreino fichaTreino) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update FichaTreino set dataInicio=?, dataReavaliacao=?, dias=?, observacao=?, Aluno_idAluno=?, "
                    + "Professor_idProfessor=? where idFichaTreino=?"
            );
            comando.setDate(1, new java.sql.Date(fichaTreino.getDataInicio().getTime()));
            comando.setDate(2, new java.sql.Date(fichaTreino.getDataReavaliacao().getTime()));
            comando.setString(3, fichaTreino.getDias());
            comando.setString(4, fichaTreino.getObservacao());

            if (fichaTreino.getAluno() == null) {
                comando.setNull(5, Types.INTEGER);
            } else {
                comando.setInt(5, fichaTreino.getAluno().getIdAluno());
            }
            if (fichaTreino.getProfessor() == null) {
                comando.setNull(6, Types.INTEGER);
            } else {
                comando.setInt(6, fichaTreino.getProfessor().getIdProfessor());
            }
            comando.setInt(7, fichaTreino.getIdFichaTreino());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(FichaTreino fichaTreino) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from FichaTreino where idFichaTreino = " + fichaTreino.getIdFichaTreino();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<FichaTreino> obterFichaTreinos()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<FichaTreino> fichaTreinos = new ArrayList<FichaTreino>();
        FichaTreino fichaTreino = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from FichaTreino");
            while (rs.next()) {
                fichaTreino = instanciarFichaTreino(rs);
                fichaTreinos.add(fichaTreino);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return fichaTreinos;
    }

    public static FichaTreino obterFichaTreino(int idFichaTreino) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        FichaTreino fichaTreino = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from fichaTreino where idFichaTreino = " + idFichaTreino);
            rs.first();
            fichaTreino = instanciarFichaTreino(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return fichaTreino;
    }

    public static FichaTreino instanciarFichaTreino(ResultSet rs) throws SQLException {
        FichaTreino fichaTreino;
        try {
            fichaTreino = new FichaTreino(rs.getInt("idFichaTreino"),
                    rs.getDate("dataInicio"),
                    rs.getDate("dataReavaliacao"),
                    rs.getString("dias"),
                    rs.getString("observacao"), null, null);
            fichaTreino.setIdProfessor(rs.getInt("Professor_idProfessor"));
            fichaTreino.setIdAluno(rs.getInt("Aluno_idAluno"));
        } catch (SQLException ex) {
            fichaTreino = null;
        }
        return fichaTreino;
    }
}
