/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.EntradaDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Entrada {
    private int idEntrada;
    private Date dataEntrada;
    private String horarioEntrada;
    
    private Aluno aluno;
    private int idAluno;

    public Entrada(int idEntrada, Date dataEntrada, String horarioEntrada, Aluno aluno) {
        this.idEntrada = idEntrada;
        this.dataEntrada = dataEntrada;
        this.horarioEntrada = horarioEntrada;
        this.aluno = aluno;
    }

    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }
    
    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(String horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public Aluno getAluno() throws ClassNotFoundException, SQLException {
        if((this.idAluno != 0) && (this.aluno == null)){
            this.aluno = Aluno.obterAluno(this.idAluno);
        }
        return this.aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
    
    public static Entrada obterEntrada(int idEntrada) throws ClassNotFoundException, SQLException{
        return EntradaDAO.obterEntrada(idEntrada);
    }
    
    public static List<Entrada> obterEntradas() throws ClassNotFoundException, SQLException{
        return EntradaDAO.obterEntradas();
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        EntradaDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        EntradaDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        EntradaDAO.excluir(this);
    }
}
