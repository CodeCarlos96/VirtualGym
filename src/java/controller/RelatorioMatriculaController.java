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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Dudu
 */
public class RelatorioMatriculaController extends HttpServlet {

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
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/VirtualGym", "root", "");

            HashMap parametros = new HashMap();
            String status1 = request.getParameter("txtStatus1");
            String status2 = request.getParameter("txtStatus2");
            String status3 = request.getParameter("txtStatus3");
            int diaInicio = Integer.parseInt(request.getParameter("txtDiaInicio").equals("") ? "0" : request.getParameter("txtDiaInicio"));
            int diaFim = Integer.parseInt(request.getParameter("txtDiaFim").equals("") ? "31" : request.getParameter("txtDiaFim"));
            String dataInicio = request.getParameter("txtDataInicio");
            String dataFim = request.getParameter("txtDataFim");
            
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date dataMin = new java.sql.Date(fmt.parse(dataInicio.equals("") ? "1900-01-01" : dataInicio).getTime());
            java.sql.Date dataMax = dataFim.equals("")
                    ? java.sql.Date.valueOf(LocalDate.now())
                    : new java.sql.Date(fmt.parse(dataFim).getTime());
            
            parametros.put("PAR_status1", status1 != null ? "ativo" : " ");
            parametros.put("PAR_status2", status2 != null ? "inativo" : " ");
            parametros.put("PAR_status3", status3 != null ? "inadimplente" : " ");
            parametros.put("PAR_diaInicio", diaInicio);
            parametros.put("PAR_diaFim", diaFim);
            parametros.put("PAR_dataInicio", dataMin);
            parametros.put("PAR_dataFim", dataMax);

            String relatorio = getServletContext().getRealPath("/WEB-INF") + "/relatorioMatricula.jasper";
            JasperPrint jp = JasperFillManager.fillReport(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(jp);

            String relatorioNome = "Matricula_" + LocalDate.now() + ".pdf";

            response.setHeader("Content-Disposition", "attachment;filename=" + relatorioNome);
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        } catch (Exception e) {
            String diaInicio = request.getParameter("txtDiaInicio");
            String diaFim = request.getParameter("txtDiaFim");
            String dataInicio = request.getParameter("txtDataInicio");
            String dataFim = request.getParameter("txtDataFim");
            
            request.setAttribute("dataInicio", dataInicio);
            request.setAttribute("dataFim", dataFim);
            request.setAttribute("diaInicio", diaInicio);
            request.setAttribute("diaFim", diaFim);
            request.setAttribute("erro", "Erro: " + "Relatório Vazio ou Indisponível");
            RequestDispatcher view = request.getRequestDispatcher("/relatorioMatriculaGerar.jsp");
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
            Logger.getLogger(RelatorioMatriculaController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RelatorioMatriculaController.class.getName()).log(Level.SEVERE, null, ex);
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
