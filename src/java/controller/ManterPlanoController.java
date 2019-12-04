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
import model.Plano;

/**
 *
 * @author Dudu
 */
public class ManterPlanoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
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
                int idPlano = Integer.parseInt(request.getParameter("idPlano"));
                Plano plano = Plano.obterPlano(idPlano);
                request.setAttribute("plano", plano);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarPlano.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
            throw e;
        } catch (IOException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManterPlanoController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManterPlanoController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManterPlanoController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        String operacao = request.getParameter("operacao");

        int idPlano = Integer.parseInt(request.getParameter("txtIdPlano"));
        String nome = request.getParameter("txtNome");
        float valor = Float.parseFloat(request.getParameter("txtValor"));
        float taxaAdesao = Float.parseFloat(request.getParameter("txtTaxaAdesao"));
        int parcelas = Integer.parseInt(request.getParameter("txtParcelas"));
        float taxaJuros = Float.parseFloat(request.getParameter("txtTaxaJuros"));
        String tipo = operacao.equals("Excluir") ? "" : request.getParameter("optTipoPlano");

        try {
            Plano plano = new Plano(idPlano, nome, valor, taxaAdesao, parcelas, tipo, taxaJuros);
            if (operacao.equals("Incluir")) {
                plano.gravar();
            }
            if (operacao.equals("Editar")) {
                plano.editar();
            }
            if (operacao.equals("Excluir")) {
                plano.excluir();
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaPlanoController");
            view.forward(request, response);
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            Plano plano;
            if (!operacao.equals("Excluir")) {
                plano = new Plano(idPlano, nome, valor, taxaAdesao, parcelas, tipo, taxaJuros);
            } else {
                plano = Plano.obterPlano(idPlano);
            }

            request.setAttribute("operacao", operacao);
            request.setAttribute("plano", plano);
            request.setAttribute("erro", "Erro: " + TraduzirExcecao.ex(ex.getMessage()));

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarPlano.jsp");
            view.forward(request, response);

        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManterPlanoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
