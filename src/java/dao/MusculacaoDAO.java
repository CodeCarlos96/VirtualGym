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
import model.Musculacao;

public class MusculacaoDAO {

    public static void gravar(Musculacao musculacao) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();

            if (musculacao.getOrdem() <= Musculacao.obterOrdem(musculacao.getFichaTreino().getIdFichaTreino())) {
                comando = conexao.prepareStatement(
                        "update Musculacao set ordem=(ordem+1) where FichaTreino_idFichaTreino="
                        + musculacao.getFichaTreino().getIdFichaTreino()
                        + " and ordem >= " + musculacao.getOrdem()
                );
                comando.executeUpdate();
            }

            comando = conexao.prepareStatement(
                    "insert into Musculacao values (default, ?, ?, ?, ?, ?, ?)"
            );
            comando.setInt(1, musculacao.getOrdem());
            comando.setInt(2, musculacao.getSeries());
            comando.setInt(3, musculacao.getPeso());
            comando.setInt(4, musculacao.getRepeticoes());

            if (musculacao.getFichaTreino() == null) {
                comando.setNull(5, Types.INTEGER);
            } else {
                comando.setInt(5, musculacao.getFichaTreino().getIdFichaTreino());
            }
            if (musculacao.getExercicio() == null) {
                comando.setNull(6, Types.INTEGER);
            } else {
                comando.setInt(6, musculacao.getExercicio().getIdExercicio());
            }

            comando.executeUpdate();

            ResultSet rs = comando.executeQuery("select LAST_INSERT_ID()");
            rs.first();
            musculacao.setIdMusculacao(rs.getInt(1));
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(Musculacao musculacao) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;
        Statement comandoOrdem;

        try {
            conexao = BD.getConexao();
            comandoOrdem = conexao.createStatement();

            ResultSet rs = comandoOrdem.executeQuery("select ordem from Musculacao where idMusculacao = " + musculacao.getIdMusculacao());
            rs.first();
            int antigaOrdem = rs.getInt(1);

            if (musculacao.getOrdem() > antigaOrdem) {
                comando = conexao.prepareStatement(
                        "update Musculacao set ordem=(ordem-1) where FichaTreino_idFichaTreino="
                        + musculacao.getFichaTreino().getIdFichaTreino()
                        + " and ordem between " + antigaOrdem + " and " + musculacao.getOrdem()
                );
                comando.executeUpdate();
            }
            if (musculacao.getOrdem() < antigaOrdem) {
                comando = conexao.prepareStatement(
                        "update Musculacao set ordem=(ordem+1) where FichaTreino_idFichaTreino="
                        + musculacao.getFichaTreino().getIdFichaTreino()
                        + " and ordem between " + musculacao.getOrdem() + " and " + antigaOrdem
                );
                comando.executeUpdate();
            }

            comando = conexao.prepareStatement(
                    "update Musculacao set ordem=?, series=?, peso=?, repeticoes=?, FichaTreino_idFichaTreino=?, Exercicio_idExercicio=? where idMusculacao=?"
            );
            comando.setInt(1, musculacao.getOrdem());
            comando.setInt(2, musculacao.getSeries());
            comando.setInt(3, musculacao.getPeso());
            comando.setInt(4, musculacao.getRepeticoes());

            if (musculacao.getFichaTreino() == null) {
                comando.setNull(5, Types.INTEGER);
            } else {
                comando.setInt(5, musculacao.getFichaTreino().getIdFichaTreino());
            }
            if (musculacao.getExercicio() == null) {
                comando.setNull(6, Types.INTEGER);
            } else {
                comando.setInt(6, musculacao.getExercicio().getIdExercicio());
            }
            comando.setInt(7, musculacao.getIdMusculacao());

            comando.executeUpdate();

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(Musculacao musculacao) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();

            stringSQL = "update Musculacao set ordem=(ordem-1) where FichaTreino_idFichaTreino="
                    + musculacao.getFichaTreino().getIdFichaTreino()
                    + " and ordem > " + musculacao.getOrdem();
            comando.execute(stringSQL);

            stringSQL = "delete from musculacao where idMusculacao = " + musculacao.getIdMusculacao();
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

            ResultSet rs = comando.executeQuery("select count(musculacao.idMusculacao) from Musculacao where FichaTreino_idFichaTreino = " + idFichaTreino);
            rs.first();
            ordem = rs.getInt(1);
        } finally {
            fecharConexao(conexao, comando);
        }
        return ordem;
    }

    public static List<Musculacao> obterMusculacoes(int idFichaTreino)
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<Musculacao> musculacoes = new ArrayList<>();
        Musculacao musculacao;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs;
            if (idFichaTreino != 0) {
                rs = comando.executeQuery("select * from Musculacao where FichaTreino_idFichaTreino = " + idFichaTreino + " order by ordem");
            } else {
                rs = comando.executeQuery("select * from Musculacao");
            }
            while (rs.next()) {
                musculacao = instanciarMusculacao(rs);
                musculacoes.add(musculacao);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return musculacoes;
    }

    public static Musculacao obterMusculacao(int idMusculacao) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        Musculacao musculacao = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from musculacao where idMusculacao = " + idMusculacao);
            rs.first();
            musculacao = instanciarMusculacao(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return musculacao;
    }

    public static Musculacao instanciarMusculacao(ResultSet rs) throws SQLException {
        Musculacao musculacao;
        try {
            musculacao = new Musculacao(rs.getInt("ordem"),
                    rs.getInt("series"),
                    rs.getInt("peso"),
                    rs.getInt("repeticoes"),
                    null, null) {
            };
            musculacao.setIdMusculacao(rs.getInt("idMusculacao"));
            musculacao.setIdExercicio(rs.getInt("Exercicio_idExercicio"));
            musculacao.setIdFichaTreino(rs.getInt("FichaTreino_idFichaTreino"));
        } catch (SQLException ex) {
            musculacao = null;
        }
        return musculacao;
    }

}
