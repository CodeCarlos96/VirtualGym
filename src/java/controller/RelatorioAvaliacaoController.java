/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aluno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Dudu
 */
public class RelatorioAvaliacaoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     * @throws java.sql.SQLException
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException, JRException, ClassNotFoundException {
        String acao = request.getParameter("acao");
        switch (acao) {
            case "buscar":
                buscar(request, response);
                break;
            case "gerar":
                gerar(request, response);
                break;
            default:
                break;
        }
    }

    protected void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        String nomeAluno = request.getParameter("txtNome");
        request.setAttribute("busca", nomeAluno);
        request.setAttribute("alunos", Aluno.obterAlunosNome(nomeAluno));
        RequestDispatcher view = request.getRequestDispatcher("/relatorioAvaliacaoBuscaResultado.jsp");
        view.forward(request, response);
    }

    protected void gerar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, JRException, SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/VirtualGym", "root", "");

            HashMap parametros = new HashMap();
            Integer idAluno = Integer.parseInt(request.getParameter("idAluno"));
            parametros.put("PAR_idAluno", idAluno);

            String relatorio = getServletContext().getRealPath("/WEB-INF") + "/relatorioAvaliacao.jasper";
            JasperPrint jp = JasperFillManager.fillReport(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(jp);

            String relatorioNome = "Avaliacao_" + Aluno.obterAluno(idAluno).getUsuario().getNome() + "_" + LocalDate.now() + ".pdf";

            response.setHeader("Content-Disposition", "attachment;filename=" + relatorioNome);
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        } catch (Exception e) {
            String busca = request.getParameter("busca");
            request.setAttribute("busca", busca);
            request.setAttribute("alunos", Aluno.obterAlunosNome(busca));
            request.setAttribute("erro", "Erro: " + "Relatório Vazio ou Indisponível");
            RequestDispatcher view = request.getRequestDispatcher("/relatorioAvaliacaoBuscaResultado.jsp");
            view.forward(request, response);
        } finally {
            if (!conexao.isClosed()) {
                conexao.close();
            }
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
        } catch (ParseException | SQLException | JRException | ClassNotFoundException ex) {
            Logger.getLogger(RelatorioAvaliacaoController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException | SQLException | JRException | ClassNotFoundException ex) {
            Logger.getLogger(RelatorioAvaliacaoController.class.getName()).log(Level.SEVERE, null, ex);
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

}
