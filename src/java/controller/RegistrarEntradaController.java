package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Entrada;

public class RegistrarEntradaController extends HttpServlet {

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
            case "entradas":
                entradas(request, response);
                break;
            case "prepararOperacao":
                prepararOperacao(request, response);
                break;
            case "confirmarOperacao":
                confirmarOperacao(request, response);
                break;
            default:
                break;
        }
    }

    protected void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String nomeAluno = request.getParameter("txtNome");
        request.setAttribute("alunos", Aluno.obterAlunosNome(nomeAluno));
        RequestDispatcher view = request.getRequestDispatcher("/registrarEntradaBuscaResultado.jsp");
        view.forward(request, response);
    }

    protected void entradas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idAluno = Integer.parseInt(request.getParameter("idAluno"));
        request.setAttribute("aluno", Aluno.obterAluno(idAluno));
        request.setAttribute("entradas", Entrada.obterEntradasAluno(idAluno));
        RequestDispatcher view = request.getRequestDispatcher("/registrosEntrada.jsp");
        view.forward(request, response);
    }

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            Integer idAluno = Integer.parseInt(request.getParameter("idAluno"));
            request.setAttribute("aluno", Aluno.obterAluno(idAluno));

            if (!operacao.equals("Incluir")) {
                Integer idEntrada = Integer.parseInt(request.getParameter("idEntrada"));
                Entrada entrada = Entrada.obterEntrada(idEntrada);
                request.setAttribute("entrada", entrada);

                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String data = fmt.format(entrada.getDataEntrada()).replace(" ", "T");

                request.setAttribute("data", data);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarEntrada.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
            throw e;
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    protected void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String operacao = request.getParameter("operacao");

        Integer idEntrada = operacao.equals("Incluir") ? null : Integer.parseInt(request.getParameter("idEntrada"));
        Integer idAluno = Integer.parseInt(request.getParameter("idAluno"));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dataStr = request.getParameter("txtDataEntrada").replace("T", " ");
        Timestamp dataEntrada = Timestamp.valueOf(LocalDateTime.parse(dataStr, fmt));

        try {
            Aluno aluno = Aluno.obterAluno(idAluno);
            Entrada entrada = new Entrada(idEntrada, dataEntrada, aluno);

            if (operacao.equals("Excluir")) {
                entrada.excluir();
            } else {
                entrada.gravar();
            }

            request.setAttribute("entradas", Entrada.obterEntradasAluno(idAluno));
            request.setAttribute("aluno", aluno);
            RequestDispatcher view = request.getRequestDispatcher("/registrosEntrada.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("erro", ex.getLocalizedMessage());
            request.setAttribute("operacao", operacao);

            Aluno aluno = Aluno.obterAluno(idAluno);
            request.setAttribute("aluno", aluno);
            request.setAttribute("entrada", new Entrada(idEntrada, dataEntrada, aluno));

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarEntrada.jsp");
            view.forward(request, response);
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
            Logger.getLogger(RegistrarEntradaController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistrarEntradaController.class.getName()).log(Level.SEVERE, null, ex);
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
