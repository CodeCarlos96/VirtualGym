
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Cadastrar Aula</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaAulaController"><button>Voltar</button></a>
        <h1>${operacao} Aula</h1>
        <form action="ManterAulaController?acao=confirmarOperacao&operacao=${operacao}&idAula=${aula.idAula}" method="post" name="formManterAula" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Nome: </td> <td><input type="text" name="txtNome" maxlength="20" value="${aula.nome}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Descrição: </td> <td><textarea rows="3" cols="20" name="txtDescricaoAula" maxlength="100" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>>${aula.descricao}</textarea></td>
                    </tr>
                    <tr>
                        <td>Valor Mensal: </td> <td><input type="number" step="0.01" name="txtValorAula" min="0" value="${aula.valor}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Taxa de Juros (%): </td> <td><input type="number" step="0.01" name="txtTaxaJuros" min="0" value="${aula.taxaJuros}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
        <script src="scripts/aula.js" charset="utf-8"></script>
    </body>
</html>
