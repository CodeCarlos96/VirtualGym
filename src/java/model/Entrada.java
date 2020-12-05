/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.EntradaDAO;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Entrada")
public class Entrada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntrada;
    private Timestamp dataEntrada;
    @ManyToOne
    private Aluno aluno;

    public Entrada() {
    }

    public Entrada(Integer idEntrada, Timestamp dataEntrada, Aluno aluno) {
        this.idEntrada = idEntrada;
        this.dataEntrada = dataEntrada;
        this.aluno = aluno;
    }

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Timestamp getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Timestamp dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public static Entrada obterEntrada(Integer idEntrada) {
        return EntradaDAO.getInstancia().obterEntrada(idEntrada);
    }

    public static List<Entrada> obterEntradas() {
        return EntradaDAO.getInstancia().obterEntradas();
    }
    
    public static List<Entrada> obterEntradasAluno(Integer idAluno) {
        return EntradaDAO.getInstancia().obterEntradasAluno(idAluno);
    }

    public Entrada gravar() {
        return EntradaDAO.getInstancia().gravar(this);
    }

    public Entrada excluir() {
        return EntradaDAO.getInstancia().excluir(this.idEntrada);
    }
}
