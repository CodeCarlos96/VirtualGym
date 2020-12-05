package model;

import dao.HorarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Horario")
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHorario;
    private String dia;
    private String horaInicio;
    private String horaFim;
    
    @ManyToOne
    private Turma turma;

    public Horario() {
    }

    public Horario(Integer idHorario, String dia, String horaInicio, String horaFim, Turma turma) {
        this.idHorario = idHorario;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.turma = turma;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public static Horario obterHorario(Integer idHorario) {
        return HorarioDAO.getInstancia().obterHorario(idHorario);
    }

    public static List<Horario> obterHorarios(Integer idTurma) {
        return HorarioDAO.getInstancia().obterHorarios(idTurma);
    }

    public Horario gravar() {
        return HorarioDAO.getInstancia().gravar(this);
    }

    public Horario excluir() {
        return HorarioDAO.getInstancia().excluir(this.idHorario);
    }

    public static void excluirHorarioTurma(Turma turma, EntityManager em) throws Exception {
        HorarioDAO.getInstancia().excluirHorarioTurma(turma, em);
    }
}
