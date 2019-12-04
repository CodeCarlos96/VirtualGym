package dao;

import static dao.DAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Exercicio;

public class ExercicioDAO {

    public static void gravar(Exercicio exercicio) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into Exercicio (idExercicio, nome, tipoTreino) values (?, ?, ?)"
            );
            comando.setInt(1, exercicio.getIdExercicio());
            comando.setString(2, exercicio.getNome());
            comando.setString(3, exercicio.getTipoTreino());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Exercicio exercicio) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update Exercicio set nome=?, tipoTreino=? where idExercicio=?"
            );
            comando.setString(1, exercicio.getNome());
            comando.setString(2, exercicio.getTipoTreino());
            comando.setInt(3, exercicio.getIdExercicio());

            comando.executeUpdate();

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Exercicio exercicio) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();

            stringSQL = "delete from Exercicio where idExercicio = " + exercicio.getIdExercicio();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<Exercicio> obterExercicios()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Exercicio> exercicios = new ArrayList<>();
        Exercicio exercicio;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Exercicio order by nome");
            while (rs.next()) {
                exercicio = instanciarExercicio(rs);
                exercicios.add(exercicio);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return exercicios;
    }

    public static List<Exercicio> obterExerciciosAerobicos()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Exercicio> exercicios = new ArrayList<>();
        Exercicio exercicio;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Exercicio where tipoTreino = 'Aerobico'");
            while (rs.next()) {
                exercicio = instanciarExercicio(rs);
                exercicios.add(exercicio);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return exercicios;
    }

    public static List<Exercicio> obterExerciciosMusculacao()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Exercicio> exercicios = new ArrayList<>();
        Exercicio exercicio;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Exercicio where tipoTreino = 'Musculação'");
            while (rs.next()) {
                exercicio = instanciarExercicio(rs);
                exercicios.add(exercicio);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return exercicios;
    }

    public static Exercicio obterExercicio(int idExercicio) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Exercicio exercicio = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Exercicio where idExercicio = " + idExercicio);
            rs.first();
            exercicio = instanciarExercicio(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return exercicio;
    }

    public static Exercicio instanciarExercicio(ResultSet rs) throws SQLException {
        Exercicio exercicio;
        try {
            exercicio = new Exercicio(rs.getInt("idExercicio"),
                    rs.getString("nome"),
                    rs.getString("tipoTreino")) {
            };
        } catch (SQLException ex) {
            exercicio = null;
        }
        return exercicio;
    }
}
