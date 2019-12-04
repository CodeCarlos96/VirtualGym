/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ProfessorDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Professor extends Usuario {

    private int idProfessor;
    private Date dataAdmissao;

    public Professor(int idProfessor, Date dataAdmissao, int idUsuario, String email, String senha, String nome, String cpf, String rg, String sexo, Date dataNascimento, String status, String telefone, Endereco endereco) {
        super(idUsuario, email, senha, nome, cpf, rg, sexo, dataNascimento, status, telefone, endereco);
        this.idProfessor = idProfessor;
        this.dataAdmissao = dataAdmissao;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public static Professor obterProfessor(int idProfessor) throws ClassNotFoundException, SQLException {
        return ProfessorDAO.obterProfessor(idProfessor);
    }

    public static List<Professor> obterProfessores() throws ClassNotFoundException, SQLException {
        return ProfessorDAO.obterProfessores();
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    @Override
    public void gravar() throws SQLException, ClassNotFoundException {
        ProfessorDAO.gravar(this);
    }

    @Override
    public void editar() throws SQLException, ClassNotFoundException {
        ProfessorDAO.editar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        ProfessorDAO.excluir(this);
    }
}
