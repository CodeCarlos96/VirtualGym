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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Exercicio;

/**
 *
 * @author Dudu
 */
public class ManterExercicioController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException, ClassNotFoundException {
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

            if (!operacao.equals("Incluir")) {
                int idExercicio = Integer.parseInt(request.getParameter("idExercicio"));
                Exercicio exercicio = Exercicio.obterExercicio(idExercicio);
                request.setAttribute("exercicio", exercicio);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarExercicio.jsp");
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
        } catch (SQLException | ParseException | ClassNotFoundException ex) {
            Logger.getLogger(ManterExercicioController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException | ParseException | ClassNotFoundException ex) {
            Logger.getLogger(ManterExercicioController.class.getName()).log(Level.SEVERE, null, ex);
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

        int idExercicio = Integer.parseInt(request.getParameter("txtIdExercicio"));
        String nome = request.getParameter("txtNome");
        String tipoTreino = operacao.equals("Excluir") ? "" : request.getParameter("optTipoTreino");

        try {
            Exercicio exercicio = new Exercicio(idExercicio, nome, tipoTreino) {
            };
            if (operacao.equals("Incluir")) {
                exercicio.gravar();
            }
            if (operacao.equals("Editar")) {
                exercicio.editar();
            }
            if (operacao.equals("Excluir")) {
                exercicio.excluir();
            }

            RequestDispatcher view = request.getRequestDispatcher("PesquisaExercicioController");
            view.forward(request, response);
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            Exercicio exercicio;
            if (!operacao.equals("Excluir")) {
                exercicio = new Exercicio(idExercicio, nome, tipoTreino) {
                };
            } else {
                exercicio = Exercicio.obterExercicio(idExercicio);
            }

            request.setAttribute("operacao", operacao);
            request.setAttribute("exercicio", exercicio);
            request.setAttribute("erro", "Erro: " + TraduzirExcecao.ex(ex.getMessage()));

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarExercicio.jsp");
            view.forward(request, response);

        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManterExercicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
