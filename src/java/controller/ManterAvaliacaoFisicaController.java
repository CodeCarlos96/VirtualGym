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
import model.AvaliacaoFisica;
import model.Professor;

/**
 *
 * @author Dudu
 */
public class ManterAvaliacaoFisicaController extends HttpServlet {

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
            request.setAttribute("professores", Professor.obterProfessores());
            request.setAttribute("alunos", Aluno.obterAlunos());

            if (!operacao.equals("Incluir")) {
                int idAvaliacaoFisica = Integer.parseInt(request.getParameter("idAvaliacaoFisica"));
                AvaliacaoFisica avaliacaoFisica = AvaliacaoFisica.obterAvaliacaoFisica(idAvaliacaoFisica);
                request.setAttribute("avaliacaoFisica", avaliacaoFisica);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarAvaliacaoFisica.jsp");
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
            Logger.getLogger(ManterAvaliacaoFisicaController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterAvaliacaoFisicaController.class.getName()).log(Level.SEVERE, null, ex);
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

        int idAvaliacaoFisica = Integer.parseInt(request.getParameter("txtIdAvaliacaoFisica"));
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dataAvaliacao = new java.sql.Date(fmt.parse(request.getParameter("txtDataAvaliacao")).getTime());
        java.sql.Date dataReavaliacao = new java.sql.Date(fmt.parse(request.getParameter("txtDataReavaliacao")).getTime());
        float peso = Float.parseFloat(request.getParameter("txtPeso"));
        float altura = Float.parseFloat(request.getParameter("txtAltura"));
        float perimetroTorax = Float.parseFloat(request.getParameter("txtPerimetroTorax"));
        float perimetroQuadril = Float.parseFloat(request.getParameter("txtPerimetroQuadril"));
        float perimetroAbdomen = Float.parseFloat(request.getParameter("txtPerimetroAbdomen"));
        float perimetroCintura = Float.parseFloat(request.getParameter("txtPerimetroCintura"));
        float perimetroAnteBracoDireito = Float.parseFloat(request.getParameter("txtPerimetroAntebracoDireito"));
        float perimetroAnteBracoEsquerdo = Float.parseFloat(request.getParameter("txtPerimetroAntebracoEsquerdo"));
        float perimetroBracoDireito = Float.parseFloat(request.getParameter("txtPerimetroBracoDireito"));
        float perimetroBracoEsquerdo = Float.parseFloat(request.getParameter("txtPerimetroBracoEsquerdo"));
        float perimetroCoxaDireita = Float.parseFloat(request.getParameter("txtPerimetroCoxaDireita"));
        float perimetroCoxaEsquerda = Float.parseFloat(request.getParameter("txtPerimetroCoxaEsquerda"));
        float perimetroPanturrilhaDireita = Float.parseFloat(request.getParameter("txtPerimetroPanturrilhaDireita"));
        float perimetroPanturrilhaEsquerda = Float.parseFloat(request.getParameter("txtPerimetroPanturrilhaEsquerda"));
        float dobraSubescapular = Float.parseFloat(request.getParameter("txtDobraSubescapular"));
        float dobraTricipital = Float.parseFloat(request.getParameter("txtDobraTricipital"));
        float dobraPeitoral = Float.parseFloat(request.getParameter("txtDobraPeitoral"));
        float dobraAbdominal = Float.parseFloat(request.getParameter("txtDobraAbdominal"));
        float dobraSuprailiaca = Float.parseFloat(request.getParameter("txtDobraSuprailiaca"));
        float dobraCoxaDireita = Float.parseFloat(request.getParameter("txtDobraCoxaDireita"));
        float dobraCoxaEsquerda = Float.parseFloat(request.getParameter("txtDobraCoxaEsquerda"));
        int idAluno = operacao.equals("Excluir") ? 0 : Integer.parseInt(request.getParameter("optAluno"));
        int idProfessor = operacao.equals("Excluir") ? 0 : Integer.parseInt(request.getParameter("optProfessor"));

        try {
            Aluno aluno = null;
            Professor professor = null;
            if (idAluno != 0) {
                aluno = Aluno.obterAluno(idAluno);
            }
            if (idProfessor != 0) {
                professor = Professor.obterProfessor(idProfessor);
            }

            AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica(idAvaliacaoFisica, dataAvaliacao, dataReavaliacao, peso, altura, perimetroTorax,
                    perimetroQuadril, perimetroAbdomen, perimetroCintura, perimetroAnteBracoDireito, perimetroAnteBracoEsquerdo,
                    perimetroBracoDireito, perimetroBracoEsquerdo, perimetroCoxaDireita, perimetroCoxaEsquerda, perimetroPanturrilhaDireita,
                    perimetroPanturrilhaEsquerda, dobraSubescapular, dobraTricipital, dobraPeitoral, dobraAbdominal, dobraSuprailiaca,
                    dobraCoxaDireita, dobraCoxaEsquerda, aluno, professor);
            if (operacao.equals("Incluir")) {
                avaliacaoFisica.gravar();
            }
            if (operacao.equals("Editar")) {
                avaliacaoFisica.editar();
            }
            if (operacao.equals("Excluir")) {
                avaliacaoFisica.excluir();
            }

            RequestDispatcher view = request.getRequestDispatcher("PesquisaAvaliacaoFisicaController");
            view.forward(request, response);
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            AvaliacaoFisica avaliacaoFisica;
            if (!operacao.equals("Excluir")) {
                Aluno aluno = Aluno.obterAluno(idAluno);
                Professor professor = Professor.obterProfessor(idProfessor);
                avaliacaoFisica = new AvaliacaoFisica(idAvaliacaoFisica, dataAvaliacao, dataReavaliacao, peso, altura, perimetroTorax,
                        perimetroQuadril, perimetroAbdomen, perimetroCintura, perimetroAnteBracoDireito, perimetroAnteBracoEsquerdo,
                        perimetroBracoDireito, perimetroBracoEsquerdo, perimetroCoxaDireita, perimetroCoxaEsquerda, perimetroPanturrilhaDireita,
                        perimetroPanturrilhaEsquerda, dobraSubescapular, dobraTricipital, dobraPeitoral, dobraAbdominal, dobraSuprailiaca,
                        dobraCoxaDireita, dobraCoxaEsquerda, aluno, professor);
            } else {
                avaliacaoFisica = AvaliacaoFisica.obterAvaliacaoFisica(idAvaliacaoFisica);
            }

            request.setAttribute("operacao", operacao);
            request.setAttribute("professores", Professor.obterProfessores());
            request.setAttribute("alunos", Aluno.obterAlunos());
            request.setAttribute("avaliacaoFisica", avaliacaoFisica);
            request.setAttribute("erro", "Erro: " + TraduzirExcecao.ex(ex.getMessage()));

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarAvaliacaoFisica.jsp");
            view.forward(request, response);

        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManterAvaliacaoFisicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
