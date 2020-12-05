
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Cadastrar Professor</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaProfessorController"><button>Voltar</button></a>
        <h1>${operacao} Professor</h1>
        <form action="ManterProfessorController?acao=confirmarOperacao&operacao=${operacao}&idProfessor=${professor.idProfessor}" method="post" name="formManterProfessor" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <th>Dados Pessoais</th>
                </tr>
                <tr>
                    <td>Nome: </td> <td><input type="text" name="txtNome" maxlength="40" value="${professor.usuario.nome}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Email: </td> <td><input type="text" name="txtEmail" maxlength="40" value="${professor.usuario.email}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>CPF: </td> <td><input type="text" name="txtCpf" maxlength="11" value="${professor.usuario.cpf}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>RG: </td> <td><input type="text" name="txtRg" maxlength="10" value="${professor.usuario.rg}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Sexo: </td>
                        <td>
                            <select name="optSexo" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="Masculino" <c:if test="${professor.usuario.sexo == 'Masculino'}"> selected </c:if>>Masculino</option>
                            <option value="Feminino" <c:if test="${professor.usuario.sexo == 'Feminino'}"> selected </c:if>>Feminino</option>
                            <option value="Outros" <c:if test="${professor.usuario.sexo == 'Outros'}"> selected </c:if>>Outros</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Data de Nascimento: </td> <td><input type="date" name="txtDataNascimento" value="${professor.usuario.dataNascimento}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Telefone: </td> <td><input type="text" name="txtTelefone" maxlength="14" value="${professor.usuario.telefone}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <th>Endereço</th>
                    </tr>
                    <tr>
                        <td>Logradouro: </td> <td><input type="text" name="txtLogradouro" maxlength="32" value="${professor.usuario.endereco.logradouro}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Número: </td> <td><input type="text" name="txtNumero" min="0" maxlength="10" value="${professor.usuario.endereco.numero}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Complemento: </td> <td><input type="text" name="txtComplemento" maxlength="10" value="${professor.usuario.endereco.complemento}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Bairro: </td> <td><input type="text" name="txtBairro" maxlength="32" value="${professor.usuario.endereco.bairro}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Cidade:</td> <td> <input type="text" name="txtCidade" maxlength="32" value="${professor.usuario.endereco.cidade}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>UF: </td> <td><input type="text" name="txtUf" maxlength="2" value="${professor.usuario.endereco.uf}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>CEP:</td> <td> <input type="text" name="txtCep" maxlength="8" value="${professor.usuario.endereco.cep}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <th>Outros</th>
                    </tr>
                    <tr>
                        <td>Status: </td>
                        <td>
                            <select name="optStatus" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="Ativo" <c:if test="${professor.usuario.status == 'Ativo'}"> selected </c:if>>Ativo</option>
                            <option value="Inativo" <c:if test="${professor.usuario.status == 'Inativo'}"> selected </c:if>>Inativo</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Data de Admissão: </td> <td><input type="date" name="txtDataAdmissao" value="${professor.dataAdmissao}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Senha: </td> <td><input type="text" name="txtSenha" maxlength="20" value="${professor.usuario.senha}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
        <script src="scripts/professor.js" charset="utf-8"></script>
    </body>
</html>