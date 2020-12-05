package controller;

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
import model.Professor;
import model.Sala;
import model.Turma;

public class ManterTurmaController extends HttpServlet {

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

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("salas", Sala.obterSalas());
            request.setAttribute("aulas", Aula.obterAulas());
            request.setAttribute("professores", Professor.obterProfessores());

            if (!operacao.equals("Incluir")) {
                Integer idTurma = Integer.parseInt(request.getParameter("idTurma"));
                Turma turma = Turma.obterTurma(idTurma);
                request.setAttribute("turma", turma);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarTurma.jsp");
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManterTurmaController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterTurmaController.class.getName()).log(Level.SEVERE, null, ex);
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

        Integer idTurma = operacao.equals("Incluir") ? null : Integer.parseInt(request.getParameter("idTurma"));
        Integer idSala = operacao.equals("Excluir") ? null : Integer.parseInt(request.getParameter("optSala"));
        Integer idAula = operacao.equals("Excluir") ? null : Integer.parseInt(request.getParameter("optAula"));
        Integer idProfessor = operacao.equals("Excluir") ? null : Integer.parseInt(request.getParameter("optProfessor"));

        try {
            Turma turma = new Turma(idTurma, Professor.obterProfessor(idProfessor), Sala.obterSala(idSala), Aula.obterAula(idAula));

            turma = operacao.equals("Excluir") ? turma.excluir() : turma.gravar();

            RequestDispatcher view = request.getRequestDispatcher("PesquisaTurmaController");
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ManterTurmaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
