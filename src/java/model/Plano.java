package model;

import dao.PlanoDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Plano")
public class Plano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlano;
    private String nome;
    private float valor;
    private float taxaAdesao;
    private int parcelas;
    private String tipo;
    private float taxaJuros;

    public Plano() {
    }

    public Plano(Integer idPlano, String nome, float valor, float taxaAdesao, int parcelas, String tipo, float taxaJuros) {
        this.idPlano = idPlano;
        this.nome = nome;
        this.valor = valor;
        this.taxaAdesao = taxaAdesao;
        this.parcelas = parcelas;
        this.tipo = tipo;
        this.taxaJuros = taxaJuros;
    }

    public Integer getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(Integer idPlano) {
        this.idPlano = idPlano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getTaxaAdesao() {
        return taxaAdesao;
    }

    public void setTaxaAdesao(float taxaAdesao) {
        this.taxaAdesao = taxaAdesao;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(float taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public static Plano obterPlano(Integer idPlano) {
        return PlanoDAO.getInstancia().obterPlano(idPlano);
    }

    public static List<Plano> obterPlanos() {
        return PlanoDAO.getInstancia().obterPlanos();
    }

    public Plano gravar() {
        return PlanoDAO.getInstancia().gravar(this);
    }

    public Plano excluir() {
        return PlanoDAO.getInstancia().excluir(this.idPlano);
    }
}
