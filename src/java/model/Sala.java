package model;

import dao.SalaDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Sala")
public class Sala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSala;
    private int capacidade;
    private String descricao;
    private String nome;

    public Sala() {
    }

    public Sala(Integer idSala, int capacidade, String descricao, String nome) {
        this.idSala = idSala;
        this.capacidade = capacidade;
        this.descricao = descricao;
        this.nome = nome;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
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

    public static Sala obterSala(Integer idSala) {
        return SalaDAO.getInstancia().obterSala(idSala);
    }

    public static List<Sala> obterSalas() {
        return SalaDAO.getInstancia().obterSalas();
    }

    public Sala gravar() {
        return SalaDAO.getInstancia().gravar(this);
    }

    public Sala excluir() {
        return SalaDAO.getInstancia().excluir(this.idSala);
    }
}
