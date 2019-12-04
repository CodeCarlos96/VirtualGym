/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AlunoDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Aluno extends Usuario {
    private int idAluno;
    private String responsavel;

    public Aluno(int idAluno, String responsavel, int idUsuario, String email, String senha, String nome, String cpf, String rg, String sexo, Date dataNascimento, String status, String telefone, Endereco endereco) {
        super(idUsuario, email, senha, nome, cpf, rg, sexo, dataNascimento, status, telefone, endereco);
        this.idAluno = idAluno;
        this.responsavel = responsavel;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public static Aluno obterAluno(int idAluno) throws ClassNotFoundException, SQLException {
        return AlunoDAO.obterAluno(idAluno);
    }

    public static List<Aluno> obterAlunos() throws ClassNotFoundException, SQLException {
        return AlunoDAO.obterAlunos();
    }
    
    @Override
    public void gravar() throws SQLException, ClassNotFoundException{
        AlunoDAO.gravar(this);
    }
    
    @Override
    public void editar() throws SQLException, ClassNotFoundException{
        AlunoDAO.editar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException{
        AlunoDAO.excluir(this);
    }
}
