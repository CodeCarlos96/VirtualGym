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
import model.Endereco;

/**
 *
 * @author Dudu
 */
public class ManterAlunoController extends HttpServlet {

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
                int idAluno = Integer.parseInt(request.getParameter("idAluno"));
                Aluno aluno = Aluno.obterAluno(idAluno);
                request.setAttribute("aluno", aluno);
            }

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarAluno.jsp");
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
            Logger.getLogger(ManterAlunoController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterAlunoController.class.getName()).log(Level.SEVERE, null, ex);
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

        int idAluno = Integer.parseInt(request.getParameter("txtIdAluno"));
        String nome = request.getParameter("txtNome");
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        String cpf = request.getParameter("txtCpf");
        String rg = request.getParameter("txtRg");
        String sexo = operacao.equals("Excluir") ? "" : request.getParameter("optSexo");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dataNascimento = new java.sql.Date(fmt.parse(request.getParameter("txtDataNascimento")).getTime());

        String status = operacao.equals("Excluir") ? "" : request.getParameter("optStatus");
        String responsavel = request.getParameter("txtResponsavel");
        String telefone = request.getParameter("txtTelefone");

        String logradouro = request.getParameter("txtLogradouro");
        String bairro = request.getParameter("txtBairro");
        String cidade = request.getParameter("txtCidade");
        String uf = request.getParameter("txtUf");
        String cep = request.getParameter("txtCep");
        String complemento = request.getParameter("txtComplemento");
        String numero = request.getParameter("txtNumero");

        try {
            Endereco endereco = new Endereco(idAluno, logradouro, bairro, cidade, uf, cep, complemento, numero);
            Aluno aluno = new Aluno(idAluno, responsavel, idAluno, email, senha, nome, cpf, rg, sexo, dataNascimento, status, telefone, endereco) {
            };
            if (operacao.equals("Incluir")) {
                endereco.gravar();
                aluno.gravar();
            } else {
                if (operacao.equals("Editar")) {
                    aluno.editar();
                    endereco.editar();
                }
                if (operacao.equals("Excluir")) {
                    aluno.excluir();
                    endereco.excluir();
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaAlunoController");
            view.forward(request, response);
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            Aluno aluno;
            if (!operacao.equals("Excluir")) {
                Endereco endereco = new Endereco(idAluno, logradouro, bairro, cidade, uf, cep, complemento, numero);
                if (operacao.equals("Incluir") && Aluno.obterUsuario(idAluno) == null) {
                    endereco.excluir();
                }
                aluno = new Aluno(idAluno, responsavel, idAluno, email, senha, nome, cpf, rg, sexo, dataNascimento, status, telefone, endereco) {
                };
            } else {
                aluno = Aluno.obterAluno(idAluno);
            }

            request.setAttribute("operacao", operacao);
            request.setAttribute("aluno", aluno);
            request.setAttribute("erro", "Erro: " + TraduzirExcecao.ex(ex.getMessage()));

            RequestDispatcher view = request.getRequestDispatcher("/cadastrarAluno.jsp");
            view.forward(request, response);

        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManterAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
