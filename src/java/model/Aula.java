
package model;

import dao.AulaDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Aula")
public class Aula implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAula;
    private String nome;
    private String descricao;
    private float valor;
    private float taxaJuros;

    public Aula() {
    }

    public Aula(Integer idAula, String nome, String descricao, float valor, float taxaJuros) {
        this.idAula = idAula;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.taxaJuros = taxaJuros;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(float taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public static Aula obterAula(Integer idAula){
        return AulaDAO.getInstancia().obterAula(idAula);
    }
    
    public static List<Aula> obterAulas(){
        return AulaDAO.getInstancia().obterAulas();
    }
    
    public Aula gravar(){
        return AulaDAO.getInstancia().gravar(this);
    }
    
    public Aula excluir(){
        return AulaDAO.getInstancia().excluir(this.idAula);
    }
}
