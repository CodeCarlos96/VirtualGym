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
import model.Aerobico;

public class AerobicoDAO {

    public static void gravar(Aerobico aerobico) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();

            if (aerobico.getOrdem() <= Aerobico.obterOrdem(aerobico.getFichaTreino().getIdFichaTreino())) {
                comando = conexao.prepareStatement(
                        "update Aerobico set ordem=(ordem+1) where FichaTreino_idFichaTreino="
                        + aerobico.getFichaTreino().getIdFichaTreino()
                        + " and ordem >= " + aerobico.getOrdem()
                );
                comando.executeUpdate();
            }

            comando = conexao.prepareStatement(
                    "insert into Aerobico values (default, ?, ?, ?, ?, ?)"
            );

            comando.setInt(1, aerobico.getOrdem());
            comando.setInt(2, aerobico.getTempo());
            comando.setInt(3, aerobico.getDistancia());

            if (aerobico.getFichaTreino() == null) {
                comando.setNull(4, Types.INTEGER);
            } else {
                comando.setInt(4, aerobico.getFichaTreino().getIdFichaTreino());
            }
            if (aerobico.getExercicio() == null) {
                comando.setNull(5, Types.INTEGER);
            } else {
                comando.setInt(5, aerobico.getExercicio().getIdExercicio());
            }

            comando.executeUpdate();

            ResultSet rs = comando.executeQuery("select LAST_INSERT_ID()");
            rs.first();
            aerobico.setIdAerobico(rs.getInt(1));
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Aerobico aerobico) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;
        Statement comandoOrdem;

        try {
            conexao = BD.getConexao();
            comandoOrdem = conexao.createStatement();

            ResultSet rs = comandoOrdem.executeQuery("select ordem from Aerobico where idAerobico = " + aerobico.getIdAerobico());
            rs.first();
            int antigaOrdem = rs.getInt(1);

            if (aerobico.getOrdem() > antigaOrdem) {
                comando = conexao.prepareStatement(
                        "update Aerobico set ordem=(ordem-1) where FichaTreino_idFichaTreino="
                        + aerobico.getFichaTreino().getIdFichaTreino()
                        + " and ordem between " + antigaOrdem + " and " + aerobico.getOrdem()
                );
                comando.executeUpdate();
            }
            if (aerobico.getOrdem() < antigaOrdem) {
                comando = conexao.prepareStatement(
                        "update Aerobico set ordem=(ordem+1) where FichaTreino_idFichaTreino="
                        + aerobico.getFichaTreino().getIdFichaTreino()
                        + " and ordem between " + aerobico.getOrdem() + " and " + antigaOrdem
                );
                comando.executeUpdate();
            }

            comando = conexao.prepareStatement(
                    "update Aerobico set ordem=?, tempo=?, distancia=?, FichaTreino_idFichaTreino=?, Exercicio_idExercicio=? where idAerobico=?"
            );
            comando.setInt(1, aerobico.getOrdem());
            comando.setInt(2, aerobico.getTempo());
            comando.setInt(3, aerobico.getDistancia());

            if (aerobico.getFichaTreino() == null) {
                comando.setNull(4, Types.INTEGER);
            } else {
                comando.setInt(4, aerobico.getFichaTreino().getIdFichaTreino());
            }
            if (aerobico.getExercicio() == null) {
                comando.setNull(5, Types.INTEGER);
            } else {
                comando.setInt(5, aerobico.getExercicio().getIdExercicio());
            }
            comando.setInt(6, aerobico.getIdAerobico());

            comando.executeUpdate();

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Aerobico aerobico) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();

            stringSQL = "update aerobico set ordem=(ordem-1) where FichaTreino_idFichaTreino="
                    + aerobico.getFichaTreino().getIdFichaTreino()
                    + " and ordem > " + aerobico.getOrdem();
            comando.execute(stringSQL);

            stringSQL = "delete from aerobico where idAerobico = " + aerobico.getIdAerobico();
            comando.execute(stringSQL);
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static int obterOrdem(int idFichaTreino) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        int ordem;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select count(aerobico.idAerobico) from Aerobico where FichaTreino_idFichaTreino = " + idFichaTreino);

            rs.first();
            ordem = rs.getInt(1);
        } finally {
            fecharConexao(conexao, comando);
        }
        return ordem;
    }

    public static List<Aerobico> obterAerobicos(int idFichaTreino)
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Aerobico> aerobicos = new ArrayList<>();
        Aerobico aerobico;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs;
            if (idFichaTreino != 0) {
                rs = comando.executeQuery("select * from Aerobico where FichaTreino_idFichaTreino = " + idFichaTreino + " order by ordem");
            } else {
                rs = comando.executeQuery("select * from Aerobico");
            }
            while (rs.next()) {
                aerobico = instanciarAerobico(rs);
                aerobicos.add(aerobico);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return aerobicos;
    }

    public static Aerobico obterAerobico(int idAerobico) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Aerobico aerobico = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Aerobico where idAerobico = " + idAerobico);
            rs.first();
            aerobico = instanciarAerobico(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return aerobico;
    }

    public static Aerobico instanciarAerobico(ResultSet rs) throws SQLException {
        Aerobico aerobico;
        try {
            aerobico = new Aerobico(rs.getInt("ordem"),
                    rs.getInt("tempo"),
                    rs.getInt("distancia"),
                    null, null) {
            };
            aerobico.setIdAerobico(rs.getInt("idAerobico"));
            aerobico.setIdExercicio(rs.getInt("Exercicio_idExercicio"));
            aerobico.setIdFichaTreino(rs.getInt("FichaTreino_idFichaTreino"));
        } catch (SQLException ex) {
            aerobico = null;
        }
        return aerobico;
    }
}
