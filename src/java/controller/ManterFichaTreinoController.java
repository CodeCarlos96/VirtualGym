/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.FichaTreino;
import model.Professor;

/**
 *
 * @author Dudu
 */
public class ManterFichaTreinoController extends HttpServlet {

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
            request.setAttribute("professores", Professor.obterProfessores());

            if (!operacao.equals("Incluir")) {
                Integer idFichaTreino = Integer.parseInt(request.getParameter("idFichaTreino"));
                FichaTreino fichaTreino = FichaTreino.obterFichaTreino(idFichaTreino);
                request.setAttribute("fichaTreino", fichaTreino);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarFichaTreino.jsp");
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
            Logger.getLogger(ManterFichaTreinoController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterFichaTreinoController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, ServletException, IOException, ClassNotFoundException {
        String operacao = request.getParameter("operacao");

        Integer idFichaTreino = operacao.equals("Incluir") ? null : Integer.parseInt(request.getParameter("idFichaTreino"));

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dataInicio = new java.sql.Date(fmt.parse(request.getParameter("txtDataInicio")).getTime());
        java.sql.Date dataReavaliacao = new java.sql.Date(fmt.parse(request.getParameter("txtDataReavaliacao")).getTime());

        String dias = ((request.getParameter("optDia1") == null) ? "" : request.getParameter("optDia1"))
                + ((request.getParameter("optDia2") == null) ? "" : request.getParameter("optDia2"))
                + ((request.getParameter("optDia3") == null) ? "" : request.getParameter("optDia3"))
                + ((request.getParameter("optDia4") == null) ? "" : request.getParameter("optDia4"))
                + ((request.getParameter("optDia5") == null) ? "" : request.getParameter("optDia5"))
                + ((request.getParameter("optDia6") == null) ? "" : request.getParameter("optDia6"))
                + ((request.getParameter("optDia7") == null) ? "" : request.getParameter("optDia7"));

        String observacao = request.getParameter("txtObservacao");

        Integer idAluno = operacao.equals("Excluir") ? null : Integer.parseInt(request.getParameter("optAluno"));
        Integer idProfessor = operacao.equals("Excluir") ? null : Integer.parseInt(request.getParameter("optProfessor"));

        try {
            Aluno aluno = Aluno.obterAluno(idAluno);
            Professor professor = Professor.obterProfessor(idProfessor);

            FichaTreino fichaTreino = new FichaTreino(idFichaTreino, dataInicio, dataReavaliacao, dias, observacao, aluno, professor);
            
            if (operacao.equals("Excluir")) {
                fichaTreino.excluir();
            }else{
                fichaTreino.gravar();
            }
            
            RequestDispatcher view = request.getRequestDispatcher("PesquisaFichaTreinoController");
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ManterFichaTreinoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
