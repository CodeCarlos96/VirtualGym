package controller;

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
import model.Endereco;
import model.Professor;
import model.Usuario;

public class ManterProfessorController extends HttpServlet {

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
            request.setAttribute("operacao", operacao);

            if (!operacao.equals("Incluir")) {
                Integer idProfessor = Integer.parseInt(request.getParameter("idProfessor"));
                Professor professor = Professor.obterProfessor(idProfessor);
                request.setAttribute("professor", professor);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarProfessor.jsp");
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
            Logger.getLogger(ManterProfessorController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterProfessorController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, ClassNotFoundException, ServletException, IOException {
        String operacao = request.getParameter("operacao");

        Integer idProfessor = operacao.equals("Incluir") ? null : Integer.parseInt(request.getParameter("idProfessor"));
        String nome = request.getParameter("txtNome");
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        String cpf = request.getParameter("txtCpf");
        String rg = request.getParameter("txtRg");
        String sexo = operacao.equals("Excluir") ? "" : request.getParameter("optSexo");
        String status = operacao.equals("Excluir") ? "" : request.getParameter("optStatus");
        String telefone = request.getParameter("txtTelefone");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dataNascimento = new java.sql.Date(fmt.parse(request.getParameter("txtDataNascimento")).getTime());
        java.sql.Date dataAdmissao = new java.sql.Date(fmt.parse(request.getParameter("txtDataAdmissao")).getTime());

        String logradouro = request.getParameter("txtLogradouro");
        String bairro = request.getParameter("txtBairro");
        String cidade = request.getParameter("txtCidade");
        String uf = request.getParameter("txtUf");
        String cep = request.getParameter("txtCep");
        String complemento = request.getParameter("txtComplemento");
        String numero = request.getParameter("txtNumero");

        try {
            Integer idEndereco = null;
            Integer idUsuario = null;

            if (!operacao.equals("Incluir")) {
                Professor professorFind = Professor.obterProfessor(idProfessor);
                idUsuario = professorFind.getUsuario().getIdUsuario();
                idEndereco = professorFind.getUsuario().getEndereco().getIdEndereco();
            }

            Endereco endereco = new Endereco(idEndereco, logradouro, bairro, cidade, uf, cep, complemento, numero);
            Usuario usuario = new Usuario(idUsuario, email, senha, nome, cpf, rg, sexo, dataNascimento, status, telefone, endereco);
            Professor professor = new Professor(idProfessor, dataAdmissao, usuario);

            professor = operacao.equals("Excluir") ? professor.excluir() : professor.gravar();

            RequestDispatcher view = request.getRequestDispatcher("PesquisaProfessorController");
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ManterProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
