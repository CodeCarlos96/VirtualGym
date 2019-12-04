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
import model.FichaTreino;
import model.Musculacao;

/**
 *
 * @author Dudu
 */
public class ManterMusculacaoController extends HttpServlet {

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
            int idFichaTreino = Integer.parseInt(request.getParameter("idFichaTreino"));
            
            request.setAttribute("operacao", operacao);
            request.setAttribute("fichaTreino", FichaTreino.obterFichaTreino(idFichaTreino));
            request.setAttribute("exercicios", Exercicio.obterExerciciosMusculacao());
            request.setAttribute("ordem", Musculacao.obterOrdem(idFichaTreino));
            
            if (!operacao.equals("Incluir")) {
                int idMusculacao = Integer.parseInt(request.getParameter("idMusculacao"));
                Musculacao musculacao = Musculacao.obterMusculacao(idMusculacao);
                request.setAttribute("musculacao", musculacao);
            }
            
            RequestDispatcher view = request.getRequestDispatcher("/cadastrarMusculacao.jsp");
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
            Logger.getLogger(ManterMusculacaoController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterMusculacaoController.class.getName()).log(Level.SEVERE, null, ex);
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
        int idFichaTreino = Integer.parseInt(request.getParameter("idFichaTreino"));
        int idMusculacao = operacao.equals("Incluir") ? 0 : Integer.parseInt(request.getParameter("idMusculacao"));
        
        int series = Integer.parseInt(request.getParameter("txtSeries"));
        int peso = Integer.parseInt(request.getParameter("txtPeso"));
        int repeticoes = Integer.parseInt(request.getParameter("txtRepeticoes"));
        int ordem = operacao.equals("Excluir") ? 0 : Integer.parseInt(request.getParameter("optOrdem"));
        int idExercicio = operacao.equals("Excluir") ? 0 : Integer.parseInt(request.getParameter("optNome"));
        
        try {
            FichaTreino fichaTreino = null;
            Exercicio exercicio = null;
            if (idExercicio != 0) {
                exercicio = Exercicio.obterExercicio(idExercicio);
            }
            if (idFichaTreino != 0) {
                fichaTreino = FichaTreino.obterFichaTreino(idFichaTreino);
                request.setAttribute("idFichaTreino", idFichaTreino);
            }
            
            Musculacao musculacao = new Musculacao(ordem, series, peso, repeticoes, fichaTreino, exercicio) {
            };
            if (operacao.equals("Incluir")) {
                musculacao.gravar();
            } else {
                musculacao.setIdMusculacao(idMusculacao);
                if (operacao.equals("Editar")) {
                    musculacao.editar();
                }
                if (operacao.equals("Excluir")) {
                    musculacao.excluir();
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaExercicioFichaController");
            view.forward(request, response);
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            Musculacao musculacao;
            if (!operacao.equals("Excluir")) {
                Exercicio exercicio = Exercicio.obterExercicio(idExercicio);
                FichaTreino fichaTreino = FichaTreino.obterFichaTreino(idFichaTreino);
                musculacao = new Musculacao(ordem, series, peso, repeticoes, fichaTreino, exercicio) {
                };
            } else {
                musculacao = Musculacao.obterMusculacao(idMusculacao);
            }
            
            request.setAttribute("operacao", operacao);
            request.setAttribute("fichaTreino", FichaTreino.obterFichaTreino(idFichaTreino));
            request.setAttribute("exercicios", Exercicio.obterExerciciosMusculacao());
            request.setAttribute("ordem", Musculacao.obterOrdem(idFichaTreino));
            request.setAttribute("musculacao", musculacao);
            request.setAttribute("erro", "Erro: " + TraduzirExcecao.ex(ex.getMessage()));
            
            RequestDispatcher view = request.getRequestDispatcher("/cadastrarMusculacao.jsp");
            view.forward(request, response);
            
        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManterMusculacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
