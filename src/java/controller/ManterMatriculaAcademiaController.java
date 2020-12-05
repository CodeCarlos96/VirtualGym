/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Date;
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
import model.MatriculaAcademia;
import model.Plano;

/**
 *
 * @author Dudu
 */
public class ManterMatriculaAcademiaController extends HttpServlet {

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
            request.setAttribute("planos", Plano.obterPlanos());
            request.setAttribute("alunos", Aluno.obterAlunos());

            if (!operacao.equals("Incluir")) {
                Integer idMatriculaAcademia = Integer.parseInt(request.getParameter("idMatriculaAcademia"));
                MatriculaAcademia matriculaAcademia = MatriculaAcademia.obterMatriculaAcademia(idMatriculaAcademia);
                request.setAttribute("matriculaAcademia", matriculaAcademia);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarMatriculaAcademia.jsp");
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
            Logger.getLogger(ManterMatriculaAcademiaController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterMatriculaAcademiaController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException, ClassNotFoundException, SQLException {
        String operacao = request.getParameter("operacao");

        Integer idMatriculaAcademia = operacao.equals("Incluir") ? null : Integer.parseInt(request.getParameter("idMatriculaAcademia"));
        int diaVencimento = Integer.parseInt(request.getParameter("txtDiaVencimento"));

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date dataMatricula = new Date(fmt.parse(request.getParameter("txtDataMatricula")).getTime());

        Integer idAluno = operacao.equals("Excluir") ? 0 : Integer.parseInt(request.getParameter("optAluno"));
        Integer idPlano = operacao.equals("Excluir") ? 0 : Integer.parseInt(request.getParameter("optPlano"));

        try {
            Aluno aluno = Aluno.obterAluno(idAluno);
            Plano plano = Plano.obterPlano(idPlano);
            MatriculaAcademia matriculaAcademia = new MatriculaAcademia(idMatriculaAcademia, dataMatricula, diaVencimento, aluno, plano);

            if (operacao.equals("Excluir")) {
                matriculaAcademia.excluir();
            } else if (operacao.equals("Incluir") && MatriculaAcademia.matriculado(idAluno)) {
                throw new Exception("Aluno já está matriculado");
            } else {
                matriculaAcademia.gravar();
            }

            RequestDispatcher view = request.getRequestDispatcher("PesquisaMatriculaAcademiaController");
            view.forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("erro", ex.getLocalizedMessage());
            request.setAttribute("operacao", operacao);
            request.setAttribute("planos", Plano.obterPlanos());
            request.setAttribute("alunos", Aluno.obterAlunos());

            Aluno aluno = Aluno.obterAluno(idAluno);
            Plano plano = Plano.obterPlano(idPlano);
            MatriculaAcademia matriculaAcademia = new MatriculaAcademia(idMatriculaAcademia, dataMatricula, diaVencimento, aluno, plano);
            request.setAttribute("matriculaAcademia", matriculaAcademia);

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarMatriculaAcademia.jsp");
            view.forward(request, response);
        }
    }
}
