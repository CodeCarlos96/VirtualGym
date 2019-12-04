/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.TraduzirExcecao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aula;

/**
 *
 * @author Dudu
 */
public class ManterAulaController extends HttpServlet {

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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarOperacao")) {
            confirmarOperacao(request, response);
        } else {
            if (acao.equals("prepararOperacao")) {
                prepararOperacao(request, response);
            }
        }
    }

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);

            if (!operacao.equals("Incluir")) {
                int idAula = Integer.parseInt(request.getParameter("idAula"));
                Aula aula = Aula.obterAula(idAula);
                request.setAttribute("aula", aula);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarAula.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
            throw e;
        } catch (IOException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManterAulaController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManterAulaController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManterAulaController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String operacao = request.getParameter("operacao");

        int idAula = Integer.parseInt(request.getParameter("txtIdAula"));
        String nome = request.getParameter("txtNome");
        String descricao = request.getParameter("txtDescricaoAula");
        float valor = Float.parseFloat(request.getParameter("txtValorAula"));
        float taxaJuros = Float.parseFloat(request.getParameter("txtTaxaJuros"));

        try {
            Aula aula = new Aula(idAula, nome, descricao, valor, taxaJuros);
            if (operacao.equals("Incluir")) {
                aula.gravar();
            }
            if (operacao.equals("Editar")) {
                aula.editar();
            }
            if (operacao.equals("Excluir")) {
                aula.excluir();
            }

            RequestDispatcher view = request.getRequestDispatcher("PesquisaAulaController");
            view.forward(request, response);
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            Aula aula;
            if (!operacao.equals("Excluir")) {
                aula = new Aula(idAula, nome, descricao, valor, taxaJuros);
            } else {
                aula = Aula.obterAula(idAula);
            }

            request.setAttribute("operacao", operacao);
            request.setAttribute("aula", aula);
            request.setAttribute("erro", "Erro: " + TraduzirExcecao.ex(ex.getMessage()));

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarAula.jsp");
            view.forward(request, response);

        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManterAulaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
