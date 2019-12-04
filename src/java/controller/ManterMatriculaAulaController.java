/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.TraduzirExcecao;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Horario;
import model.MatriculaAula;
import model.Turma;

/**
 *
 * @author Dudu
 */
public class ManterMatriculaAulaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarOperacao")) {
            confirmarOperacao(request, response);
        } else {
            if (acao.equals("prepararOperacao")) {
                prepararOperacao(request, response);
            }
        }
    }

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("alunos", Aluno.obterAlunos());
            request.setAttribute("horarios", Horario.obterHorarios(0));

            if (!operacao.equals("Incluir")) {
                int idMatriculaAula = Integer.parseInt(request.getParameter("idMatriculaAula"));
                MatriculaAula matriculaAula = MatriculaAula.obterMatriculaAula(idMatriculaAula);
                request.setAttribute("matriculaAula", matriculaAula);
                request.setAttribute("turmas", Turma.obterTurmas());
            } else {
                request.setAttribute("turmas", Turma.obterTurmasDisponiveis());
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarMatriculaAula.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
            throw e;
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(ManterMatriculaAulaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(ManterMatriculaAulaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ParseException, ClassNotFoundException, SQLException, ServletException, IOException {
        String operacao = request.getParameter("operacao");

        int idMatriculaAula = Integer.parseInt(request.getParameter("txtIdMatriculaAula"));
        int diaVencimento = Integer.parseInt(request.getParameter("txtDiaVencimento"));

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dataMatricula = new java.sql.Date(fmt.parse(request.getParameter("txtDataMatricula")).getTime());

        int idAluno = operacao.equals("Excluir") ? 0 : Integer.parseInt(request.getParameter("optAluno"));
        int idTurma = operacao.equals("Excluir") ? 0 : Integer.parseInt(request.getParameter("optTurma"));

        try {
            Aluno aluno = null;
            Turma turma = null;
            if (idAluno != 0) {
                aluno = Aluno.obterAluno(idAluno);
            }
            if (idTurma != 0) {
                turma = Turma.obterTurma(idTurma);
            }

            MatriculaAula matriculaAula = new MatriculaAula(idMatriculaAula, dataMatricula, diaVencimento, aluno, turma);
            if (operacao.equals("Incluir") && !MatriculaAula.matriculado(aluno.getIdAluno(), turma.getIdTurma())) {
                matriculaAula.gravar();
            }
            if (operacao.equals("Editar")) {
                matriculaAula.editar();
            }
            if (operacao.equals("Excluir")) {
                matriculaAula.excluir();
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaMatriculaAulaController");
            view.forward(request, response);
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            MatriculaAula matriculaAula;
            if (!operacao.equals("Excluir")) {
                Aluno aluno = Aluno.obterAluno(idAluno);
                Turma turma = Turma.obterTurma(idTurma);
                matriculaAula = new MatriculaAula(idMatriculaAula, dataMatricula, diaVencimento, aluno, turma);
            } else {
                matriculaAula = MatriculaAula.obterMatriculaAula(idMatriculaAula);
            }

            request.setAttribute("operacao", operacao);
            request.setAttribute("turmas", Turma.obterTurmasDisponiveis());
            request.setAttribute("alunos", Aluno.obterAlunos());
            request.setAttribute("horarios", Horario.obterHorarios(0));
            request.setAttribute("matriculaAula", matriculaAula);
            request.setAttribute("erro", "Erro: " + TraduzirExcecao.ex(ex.getMessage()));

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarMatriculaAula.jsp");
            view.forward(request, response);

        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManterMatriculaAulaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
