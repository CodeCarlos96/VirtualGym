
package model;

import dao.SalaDAO;
import java.sql.SQLException;
import java.util.List;

public class Sala {
    private int idSala;
    private int capacidade;
    private String descricao;
    private String nome;

    public Sala(int idSala, int capacidade, String descricao, String nome) {
        this.idSala = idSala;
        this.capacidade = capacidade;
        this.descricao = descricao;
        this.nome = nome;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static Sala obterSala(int idSala) throws ClassNotFoundException, SQLException {
        return SalaDAO.obterSala(idSala);
    }

    public static List<Sala> obterSalas() throws ClassNotFoundException, SQLException {
        return SalaDAO.obterSalas();
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        SalaDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        SalaDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        SalaDAO.excluir(this);
    }
}
