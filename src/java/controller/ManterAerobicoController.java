/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.Aerobico;
import model.Exercicio;
import model.FichaTreino;

/**
 *
 * @author Dudu
 */
public class ManterAerobicoController extends HttpServlet {

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
            Integer idFichaTreino = Integer.parseInt(request.getParameter("idFichaTreino"));

            request.setAttribute("operacao", operacao);
            request.setAttribute("fichaTreino", FichaTreino.obterFichaTreino(idFichaTreino));
            request.setAttribute("exercicios", Exercicio.obterExerciciosAerobicos());
            request.setAttribute("ordem", Aerobico.obterOrdem(idFichaTreino));

            if (!operacao.equals("Incluir")) {
                Integer idAerobico = Integer.parseInt(request.getParameter("idAerobico"));
                Aerobico aerobico = Aerobico.obterAerobico(idAerobico);
                request.setAttribute("aerobico", aerobico);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarAerobico.jsp");
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
            Logger.getLogger(ManterAerobicoController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterAerobicoController.class.getName()).log(Level.SEVERE, null, ex);
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
        Integer idFichaTreino = Integer.parseInt(request.getParameter("idFichaTreino"));
        Integer idAerobico = operacao.equals("Incluir") ? null : Integer.parseInt(request.getParameter("idAerobico"));

        int tempo = Integer.parseInt(request.getParameter("txtTempo"));
        int distancia = Integer.parseInt(request.getParameter("txtDistancia"));
        int ordem = operacao.equals("Excluir") ? 0 : Integer.parseInt(request.getParameter("optOrdem"));
        Integer idExercicio = operacao.equals("Excluir") ? null : Integer.parseInt(request.getParameter("optNome"));

        try {
            FichaTreino fichaTreino = FichaTreino.obterFichaTreino(idFichaTreino);
            Exercicio exercicio = Exercicio.obterExercicio(idExercicio);
            if (operacao.equals("Incluir")) {
                request.setAttribute("idFichaTreino", idFichaTreino);
            }

            Aerobico aerobico = new Aerobico(idAerobico, ordem, tempo, distancia, fichaTreino, exercicio);

            if (operacao.equals("Excluir")) {
                aerobico.excluir();
            } else {
                aerobico.gravar();
            }

            RequestDispatcher view = request.getRequestDispatcher("PesquisaExercicioFichaController");
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ManterAerobicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
