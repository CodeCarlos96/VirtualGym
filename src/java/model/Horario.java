/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.HorarioDAO;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Dudu
 */
public class Horario {
    private int idHorario;
    private String dia;
    private String horaInicio;
    private String horaFim;
    
    private Turma turma;
    private int idTurma;

    public Horario(String dia, String horaInicio, String horaFim, Turma turma) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.turma = turma;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public Turma getTurma() throws ClassNotFoundException, SQLException {
        if ((this.getIdTurma() != 0) && (this.turma == null)) {
            this.turma = Turma.obterTurma(this.idTurma);
        }
        return this.turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }
    
    public static Horario obterHorario(int idHorario) throws ClassNotFoundException, SQLException{
        return HorarioDAO.obterHorario(idHorario);
    }
    
    public static List<Horario> obterHorarios(int idTurma) throws ClassNotFoundException, SQLException{
        return HorarioDAO.obterHorarios(idTurma);
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        HorarioDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        HorarioDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        HorarioDAO.excluir(this);
    }
    
    public static void excluirHorarioTurma(int idTurma) throws SQLException, ClassNotFoundException{
        HorarioDAO.excluirHorarioTurma(idTurma);
    }
}
