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
import model.AvaliacaoFisica;

public class AvaliacaoFisicaDAO {

    public static void gravar(AvaliacaoFisica avaliacaoFisica) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "insert into AvaliacaoFisica "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            comando.setInt(1, avaliacaoFisica.getIdAvaliacaoFisica());
            comando.setDate(2, new java.sql.Date(avaliacaoFisica.getDataAvaliacao().getTime()));
            comando.setDate(3, new java.sql.Date(avaliacaoFisica.getDataReavaliacao().getTime()));
            comando.setFloat(4, avaliacaoFisica.getPeso());
            comando.setFloat(5, avaliacaoFisica.getAltura());
            comando.setFloat(6, avaliacaoFisica.getPerimetroTorax());
            comando.setFloat(7, avaliacaoFisica.getPerimetroQuadril());
            comando.setFloat(8, avaliacaoFisica.getPerimetroAbdomen());
            comando.setFloat(9, avaliacaoFisica.getPerimetroCintura());
            comando.setFloat(10, avaliacaoFisica.getPerimetroAntebracoDireito());
            comando.setFloat(11, avaliacaoFisica.getPerimetroAntebracoEsquerdo());
            comando.setFloat(12, avaliacaoFisica.getPerimetroBracoDireito());
            comando.setFloat(13, avaliacaoFisica.getPerimetroBracoEsquerdo());
            comando.setFloat(14, avaliacaoFisica.getPerimetroCoxaDireita());
            comando.setFloat(15, avaliacaoFisica.getPerimetroCoxaEsquerda());
            comando.setFloat(16, avaliacaoFisica.getPerimetroPanturrilhaDireita());
            comando.setFloat(17, avaliacaoFisica.getPerimetroPanturrilhaEsquerda());
            comando.setFloat(18, avaliacaoFisica.getDobraSubescapular());
            comando.setFloat(19, avaliacaoFisica.getDobraTricipital());
            comando.setFloat(20, avaliacaoFisica.getDobraPeitoral());
            comando.setFloat(21, avaliacaoFisica.getDobraAbdominal());
            comando.setFloat(22, avaliacaoFisica.getDobraSuprailiaca());
            comando.setFloat(23, avaliacaoFisica.getDobraCoxaDireita());
            comando.setFloat(24, avaliacaoFisica.getDobraCoxaEsquerda());

            if (avaliacaoFisica.getAluno() == null) {
                comando.setNull(25, Types.INTEGER);
            } else {
                comando.setInt(25, avaliacaoFisica.getAluno().getIdAluno());
            }
            if (avaliacaoFisica.getProfessor() == null) {
                comando.setNull(26, Types.INTEGER);
            } else {
                comando.setInt(26, avaliacaoFisica.getProfessor().getIdProfessor());
            }

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void editar(AvaliacaoFisica avaliacaoFisica) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement(
                    "update AvaliacaoFisica set dataAvaliacao=?, dataReavaliacao=?, "
                    + "peso=?, altura=?, perimetroTorax=?, perimetroQuadril=?, perimetroAbdomen=?, perimetroCintura=?, "
                    + "perimetroAntebracoDireito=?, perimetroAntebracoEsquerdo=?, perimetroBracoDireito=?, "
                    + "perimetroBracoEsquerdo=?, perimetroCoxaDireita=?, perimetroCoxaEsquerda=?, perimetroPanturrilhaDireita=?, "
                    + "perimetroPanturrilhaEsquerda=?, dobraSubescapular=?, dobraTricipital=?, dobraPeitoral=?, dobraAbdominal=?, "
                    + "dobraSuprailiaca=?, dobraCoxaDireita=?, dobraCoxaEsquerda=?, Aluno_idAluno=?, Professor_idProfessor=? "
                    + "where idAvaliacaoFisica=?");
            comando.setDate(1, new java.sql.Date(avaliacaoFisica.getDataAvaliacao().getTime()));
            comando.setDate(2, new java.sql.Date(avaliacaoFisica.getDataReavaliacao().getTime()));
            comando.setFloat(3, avaliacaoFisica.getPeso());
            comando.setFloat(4, avaliacaoFisica.getAltura());
            comando.setFloat(5, avaliacaoFisica.getPerimetroTorax());
            comando.setFloat(6, avaliacaoFisica.getPerimetroQuadril());
            comando.setFloat(7, avaliacaoFisica.getPerimetroAbdomen());
            comando.setFloat(8, avaliacaoFisica.getPerimetroCintura());
            comando.setFloat(9, avaliacaoFisica.getPerimetroAntebracoDireito());
            comando.setFloat(10, avaliacaoFisica.getPerimetroAntebracoEsquerdo());
            comando.setFloat(11, avaliacaoFisica.getPerimetroBracoDireito());
            comando.setFloat(12, avaliacaoFisica.getPerimetroBracoEsquerdo());
            comando.setFloat(13, avaliacaoFisica.getPerimetroCoxaDireita());
            comando.setFloat(14, avaliacaoFisica.getPerimetroCoxaEsquerda());
            comando.setFloat(15, avaliacaoFisica.getPerimetroPanturrilhaDireita());
            comando.setFloat(16, avaliacaoFisica.getPerimetroPanturrilhaEsquerda());
            comando.setFloat(17, avaliacaoFisica.getDobraSubescapular());
            comando.setFloat(18, avaliacaoFisica.getDobraTricipital());
            comando.setFloat(19, avaliacaoFisica.getDobraPeitoral());
            comando.setFloat(20, avaliacaoFisica.getDobraAbdominal());
            comando.setFloat(21, avaliacaoFisica.getDobraSuprailiaca());
            comando.setFloat(22, avaliacaoFisica.getDobraCoxaDireita());
            comando.setFloat(23, avaliacaoFisica.getDobraCoxaEsquerda());

            if (avaliacaoFisica.getAluno() == null) {
                comando.setNull(24, Types.INTEGER);
            } else {
                comando.setInt(24, avaliacaoFisica.getAluno().getIdAluno());
            }
            if (avaliacaoFisica.getProfessor() == null) {
                comando.setNull(25, Types.INTEGER);
            } else {
                comando.setInt(25, avaliacaoFisica.getProfessor().getIdProfessor());
            }
            comando.setInt(26, avaliacaoFisica.getIdAvaliacaoFisica());

            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluir(AvaliacaoFisica avaliacaoFisica) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from avaliacaofisica where idAvaliacaoFisica = " + avaliacaoFisica.getIdAvaliacaoFisica();
            comando.execute(stringSQL);

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static List<AvaliacaoFisica> obterAvaliacoesFisicas()
            throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        List<AvaliacaoFisica> avaliacoesFisicas = new ArrayList<AvaliacaoFisica>();
        AvaliacaoFisica avaliacaoFisica = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from AvaliacaoFisica");
            while (rs.next()) {
                avaliacaoFisica = instanciarAvaliacaoFisica(rs);
                avaliacoesFisicas.add(avaliacaoFisica);
            }
        } finally {
            fecharConexao(conexao, comando);
        }
        return avaliacoesFisicas;
    }

    public static AvaliacaoFisica obterAvaliacaoFisica(int idAvaliacaoFisica) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Statement comando = null;
        AvaliacaoFisica avaliacaoFisica = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from avaliacaoFisica where idAvaliacaoFisica = " + idAvaliacaoFisica);
            rs.first();
            avaliacaoFisica = instanciarAvaliacaoFisica(rs);
        } finally {
            fecharConexao(conexao, comando);
        }
        return avaliacaoFisica;
    }

    public static AvaliacaoFisica instanciarAvaliacaoFisica(ResultSet rs) throws SQLException {
        AvaliacaoFisica avaliacaoFisica;
        try {
            avaliacaoFisica = new AvaliacaoFisica(rs.getInt("idAvaliacaoFisica"),
                    rs.getDate("dataAvaliacao"),
                    rs.getDate("dataReavaliacao"),
                    rs.getFloat("peso"),
                    rs.getFloat("altura"),
                    rs.getFloat("perimetroTorax"),
                    rs.getFloat("perimetroQuadril"),
                    rs.getFloat("perimetroAbdomen"),
                    rs.getFloat("perimetroCintura"),
                    rs.getFloat("perimetroAntebracoDireito"),
                    rs.getFloat("perimetroAntebracoEsquerdo"),
                    rs.getFloat("perimetroBracoDireito"),
                    rs.getFloat("perimetroBracoEsquerdo"),
                    rs.getFloat("perimetroCoxaDireita"),
                    rs.getFloat("perimetroCoxaEsquerda"),
                    rs.getFloat("perimetroPanturrilhaDireita"),
                    rs.getFloat("perimetroPanturrilhaEsquerda"),
                    rs.getFloat("dobraSubescapular"),
                    rs.getFloat("dobraTricipital"),
                    rs.getFloat("dobraPeitoral"),
                    rs.getFloat("dobraAbdominal"),
                    rs.getFloat("dobraSuprailiaca"),
                    rs.getFloat("dobraCoxaDireita"),
                    rs.getFloat("dobraCoxaEsquerda"), null, null);

            avaliacaoFisica.setIdAluno(rs.getInt("Aluno_idAluno"));
            avaliacaoFisica.setIdProfessor(rs.getInt("Professor_idProfessor"));
        } catch (SQLException ex) {
            avaliacaoFisica = null;
        }
        return avaliacaoFisica;
    }
}
