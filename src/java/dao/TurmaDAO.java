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
import model.Turma;

public class TurmaDAO {

    public static void gravar(Turma turma) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into Turma (idTurma, Professor_idProfessor, Sala_idSala, Aula_idAula) values (?, ?, ?, ?)"
            );
            comando.setInt(1, turma.getIdTurma());

            if (turma.getProfessor() == null) {
                comando.setNull(2, Types.INTEGER);
            } else {
                comando.setInt(2, turma.getProfessor().getIdProfessor());
            }
            if (turma.getSala() == null) {
                comando.setNull(3, Types.INTEGER);
            } else {
                comando.setInt(3, turma.getSala().getIdSala());
            }
            if (turma.getAula() == null) {
                comando.setNull(4, Types.INTEGER);
            } else {
                comando.setInt(4, turma.getAula().getIdAula());
            }

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Turma turma) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update Turma set Professor_idProfessor=?, Sala_idSala=?, Aula_idAula=? where idTurma=?"
            );
            if (turma.getProfessor() == null) {
                comando.setNull(1, Types.INTEGER);
            } else {
                comando.setInt(1, turma.getProfessor().getIdProfessor());
            }
            if (turma.getSala() == null) {
                comando.setNull(2, Types.INTEGER);
            } else {
                comando.setInt(2, turma.getSala().getIdSala());
            }
            if (turma.getAula() == null) {
                comando.setNull(3, Types.INTEGER);
            } else {
                comando.setInt(3, turma.getAula().getIdAula());
            }
            comando.setInt(4, turma.getIdTurma());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Turma turma) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            
            stringSQL = "delete from turma where idTurma = " + turma.getIdTurma();
            comando.execute(stringSQL);

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Turma obterTurma(int idTurma)
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Turma turma = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Turma where idTurma = " + idTurma);
            rs.first();

            turma = instanciarTurma(rs);

        } finally {
            fecharConexao(conexao, comando);
        }
        return turma;
    }

    public static List<Turma> obterTurmas()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Turma> turmas = new ArrayList<Turma>();
        Turma turma = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Turma");
            while (rs.next()) {
                turma = instanciarTurma(rs);
                turmas.add(turma);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return turmas;
    }

    public static List<Turma> obterTurmasDisponiveis()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Turma> turmas = new ArrayList<>();
        Turma turma;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Turma"
                    + " join Sala on Turma.Sala_idSala = Sala.idSala"
                    + " left join MatriculaAula on Turma.idTurma = MatriculaAula.Turma_idTurma"
                    + " group by Turma.idTurma"
                    + " having count(MatriculaAula.idMatriculaAula) < Sala.capacidade");
            while (rs.next()) {
                turma = instanciarTurma(rs);
                turmas.add(turma);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return turmas;
    }

    public static Turma instanciarTurma(ResultSet rs) throws SQLException {
        Turma turma;
        try {
            turma = new Turma(rs.getInt("idTurma"), null, null, null);

            turma.setIdAula(rs.getInt("Aula_idAula"));
            turma.setIdProfessor(rs.getInt("Professor_idProfessor"));
            turma.setIdSala(rs.getInt("Sala_idSala"));
        } catch (SQLException ex) {
            turma = null;
        }
        return turma;
    }
}
