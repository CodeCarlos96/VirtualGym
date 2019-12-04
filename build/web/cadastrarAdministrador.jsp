
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/administrador.js" charset="utf-8"></script>
        <title>Virtual Gym</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaAdministradorController"><button>Voltar</button></a>
        <h1>${operacao} Administrador</h1>
        <form action="ManterAdministradorController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="formManterAdministrador" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <th>Dados Pessoais</th>
                </tr>
                <tr>
                    <td>Código: </td><td><input type="number" name="txtIdAdministrador" min="1" value="${administrador.idAdministrador}" <c:if test="${operacao != 'Incluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>Nome: </td> <td><input type="text" name="txtNome" maxlength="40" value="${administrador.nome}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>Email: </td> <td><input type="text" name="txtEmail" maxlength="40" value="${administrador.email}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>CPF: </td> <td><input type="text" id="txtCpf" name="txtCpf" maxlength="11" value="${administrador.cpf}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>RG: </td> <td><input type="text" name="txtRg" maxlength="10" value="${administrador.rg}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>Sexo: </td>
                    <td>
                        <select name="optSexo" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                        <option value="Masculino" <c:if test="${administrador.sexo == 'Masculino'}"> selected </c:if>>Masculino</option>
                        <option value="Feminino" <c:if test="${administrador.sexo == 'Feminino'}"> selected </c:if>>Feminino</option>
                        <option value="Outros" <c:if test="${administrador.sexo == 'Outros'}"> selected </c:if>>Outros</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Data de Nascimento: </td> <td><input type="date" name="txtDataNascimento" value="${administrador.dataNascimento}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>Telefone: </td> <td><input type="text" name="txtTelefone" maxlength="14" value="${administrador.telefone}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>

                <tr>
                    <th>Endereço</th>
                </tr>
                <tr>
                    <td>Logradouro: </td> <td><input type="text" name="txtLogradouro" maxlength="32" value="${administrador.endereco.logradouro}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>Número: </td> <td><input type="text" name="txtNumero" maxlength="10" value="${administrador.endereco.numero}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>Complemento: </td> <td><input type="text" name="txtComplemento" maxlength="10" value="${administrador.endereco.complemento}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>Bairro: </td> <td><input type="text" name="txtBairro" maxlength="32" value="${administrador.endereco.bairro}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>Cidade:</td> <td> <input type="text" name="txtCidade" maxlength="32" value="${administrador.endereco.cidade}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>UF: </td> <td><input type="text" name="txtUf" maxlength="2" value="${administrador.endereco.uf}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>CEP:</td> <td> <input type="text" name="txtCep" maxlength="8" value="${administrador.endereco.cep}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <th>Outros</th>
                </tr>
                <tr>
                    <td>Status: </td>
                    <td>
                        <select name="optStatus" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                        <option value="Ativo" <c:if test="${administrador.status == 'Ativo'}"> selected </c:if>>Ativo</option>
                        <option value="Inativo" <c:if test="${administrador.status == 'Inativo'}"> selected </c:if>>Inativo</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Data de Admissão: </td> <td><input type="date" name="txtDataAdmissao" value="${administrador.dataAdmissao}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
                <tr>
                    <td>Senha: </td> <td><input type="text" name="txtSenha" maxlength="20" value="${administrador.senha}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
    </body>
</html>