/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import model.Aluno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Dudu
 */
public class RelatorioFrequenciaController extends HttpServlet {

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
        switch (acao) {
            case "buscar":
                buscar(request, response);
                break;
            case "escolher":
                escolher(request, response);
                break;
            case "gerar":
                gerar(request, response);
                break;
            default:
                break;
        }
    }

    protected void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String nomeAluno = request.getParameter("txtNome");
        request.setAttribute("alunos", Aluno.obterAlunosNome(nomeAluno));
        RequestDispatcher view = request.getRequestDispatcher("/relatorioFrequenciaBuscaResultado.jsp");
        view.forward(request, response);
    }

    protected void escolher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idAluno = Integer.parseInt(request.getParameter("idAluno"));
        request.setAttribute("idAluno", idAluno);
        RequestDispatcher view = request.getRequestDispatcher("/relatorioFrequenciaGerar.jsp");
        view.forward(request, response);
    }

    protected void gerar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/VirtualGym", "root", "");

            Integer idAluno = Integer.parseInt(request.getParameter("idAluno"));
            String dataInicio = request.getParameter("txtDataInicio");
            String dataFim = request.getParameter("txtDataFim");

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date dataMin = new Date(fmt.parse(dataInicio.equals("") ? "1900-01-01" : dataInicio).getTime());

            Date dataMax = dataFim.equals("")
                    ? Date.valueOf(LocalDate.now())
                    : new Date(fmt.parse(request.getParameter("txtDataFim")).getTime());

            HashMap parametros = new HashMap();
            parametros.put("PAR_idAluno", idAluno);
            parametros.put("PAR_dataInicio", dataMin);
            parametros.put("PAR_dataFim", dataMax);

            String relatorio = getServletContext().getRealPath("/WEB-INF") + "/relatorioFrequencia.jasper";
            JasperPrint jp = JasperFillManager.fillReport(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(jp);

            String relatorioNome = "Frequencia_" + Aluno.obterAluno(idAluno).getUsuario().getNome() + "_" + LocalDate.now() + ".pdf";

            response.setHeader("Content-Disposition", "attachment;filename=" + relatorioNome);
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);

        } catch (Exception e) {
            Integer idAluno = Integer.parseInt(request.getParameter("idAluno"));
            String dataInicio = request.getParameter("txtDataInicio");
            String dataFim = request.getParameter("txtDataFim");

            request.setAttribute("idAluno", idAluno);
            request.setAttribute("dataInicio", dataInicio);
            request.setAttribute("dataFim", dataFim);

            request.setAttribute("erro", "Erro: " + "Relatório Vazio ou Indisponível");

            RequestDispatcher view = request.getRequestDispatcher("/relatorioFrequenciaGerar.jsp");
            view.forward(request, response);
        } finally {
            try {
                if (!conexao.isClosed()) {
                    conexao.close();
                }
            } catch (SQLException ex) {
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
        } catch (ParseException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RelatorioFrequenciaController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RelatorioFrequenciaController.class.getName()).log(Level.SEVERE, null, ex);
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
