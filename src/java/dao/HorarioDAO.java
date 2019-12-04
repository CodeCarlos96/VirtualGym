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
import model.Horario;

public class HorarioDAO {

    public static void gravar(Horario horario) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into Horario (idHorario, dia, horaInicio, horaFim, Turma_idTurma) values (default, ?, ?, ?, ?)"
            );

            comando.setString(1, horario.getDia());
            comando.setString(2, horario.getHoraInicio());
            comando.setString(3, horario.getHoraFim());

            if (horario.getTurma() == null) {
                comando.setNull(4, Types.INTEGER);
            } else {
                comando.setInt(4, horario.getTurma().getIdTurma());
            }

            comando.executeUpdate();

            ResultSet rs = comando.executeQuery("select LAST_INSERT_ID()");
            rs.first();
            horario.setIdHorario(rs.getInt(1));
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Horario horario) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update Horario set dia=?, horaInicio=?, horaFim=?, Turma_idTurma=? where idHorario=?"
            );
            comando.setString(1, horario.getDia());
            comando.setString(2, horario.getHoraInicio());
            comando.setString(3, horario.getHoraFim());

            if (horario.getTurma() == null) {
                comando.setNull(4, Types.INTEGER);
            } else {
                comando.setInt(4, horario.getTurma().getIdTurma());
            }
            comando.setInt(5, horario.getIdHorario());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Horario horario) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Horario where idHorario = " + horario.getIdHorario();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluirHorarioTurma(int idTurma) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Horario where Turma_idTurma = " + idTurma;
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Horario obterHorario(int idHorario)
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Horario horario = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Horario where idHorario = " + idHorario);
            rs.first();

            horario = instanciarHorario(rs);

        } finally {
            fecharConexao(conexao, comando);
        }
        return horario;
    }

    public static List<Horario> obterHorarios(int idTurma)
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Horario> horarios = new ArrayList<Horario>();
        Horario horario = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs;
            if (idTurma == 0) {
                rs = comando.executeQuery("select * from Horario order by dia, horaInicio, horaFim");
            } else {
                rs = comando.executeQuery("select * from Horario where Turma_idTurma = " + idTurma + " order by dia, horaInicio, horaFim");
            }
            while (rs.next()) {
                horario = instanciarHorario(rs);
                horarios.add(horario);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return horarios;
    }

    public static Horario instanciarHorario(ResultSet rs) throws SQLException {
        Horario horario;
        try {
            horario = new Horario(rs.getString("dia"), rs.getString("horaInicio"), rs.getString("horaFim"), null);
            horario.setIdHorario(rs.getInt("idHorario"));
            horario.setIdTurma(rs.getInt("Turma_idTurma"));
        } catch (SQLException ex) {
            horario = null;
        }
        return horario;
    }
}
