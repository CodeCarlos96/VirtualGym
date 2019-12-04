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
import model.Horario;
import model.Turma;

/**
 *
 * @author Dudu
 */
public class ManterHorarioController extends HttpServlet {

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

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
            String operacao = request.getParameter("operacao");
            int idTurma = Integer.parseInt(request.getParameter("idTurma"));
            request.setAttribute("operacao", operacao);
            request.setAttribute("turma", Turma.obterTurma(idTurma));

            if (!operacao.equals("Incluir")) {
                int idHorario = Integer.parseInt(request.getParameter("idHorario"));
                Horario horario = Horario.obterHorario(idHorario);
                request.setAttribute("horario", horario);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarHorario.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
            throw e;
        } catch (IOException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManterHorarioController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterHorarioController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterHorarioController.class.getName()).log(Level.SEVERE, null, ex);
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
        int idTurma = Integer.parseInt(request.getParameter("idTurma"));
        int idHorario = operacao.equals("Incluir") ? 0 : Integer.parseInt(request.getParameter("idHorario"));

        String dia = operacao.equals("Excluir") ? "" : request.getParameter("optDia");
        String horaInicio = request.getParameter("txtHoraInicio");
        String horaFim = request.getParameter("txtHoraFim");

        try {
            Turma turma = Turma.obterTurma(idTurma);
            Horario horario = new Horario(dia, horaInicio, horaFim, turma);

            if (operacao.equals("Incluir")) {
                horario.gravar();
            } else {
                horario.setIdHorario(idHorario);
                if (operacao.equals("Editar")) {
                    horario.editar();
                }
                if (operacao.equals("Excluir")) {
                    horario.excluir();
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaHorarioController");
            view.forward(request, response);
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            Horario horario;
            if (!operacao.equals("Excluir")) {
                Turma turma = Turma.obterTurma(idTurma);
                horario = new Horario(dia, horaInicio, horaFim, turma);
            } else {
                horario = Horario.obterHorario(idHorario);
            }

            request.setAttribute("operacao", operacao);
            request.setAttribute("turma", Turma.obterTurma(idTurma));
            request.setAttribute("horario", horario);
            request.setAttribute("erro", "Erro: " + TraduzirExcecao.ex(ex.getMessage()));

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarHorario.jsp");
            view.forward(request, response);

        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManterHorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
