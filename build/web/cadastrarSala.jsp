
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/sala.js" charset="utf-8"></script>
        <title>Virtual Gym</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaSalaController"><button>Voltar</button></a>
        <h1>${operacao} Sala</h1>
        <form action="ManterSalaController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="formManterSala" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Código: </td>
                    <td><input type="number" name="txtIdSala" min="1" value="${sala.idSala}" <c:if test="${operacao != 'Incluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Nome: </td>
                        <td><input type="text" name="txtNome" maxlength="40" value="${sala.nome}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Capacidade: </td>
                        <td><input type="number" name="txtCapacidadeSala" min="0" value="${sala.capacidade}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Descrição: </td>
                        <td><textarea rows="3" cols="20" maxlength="100" name="txtDescricaoSala" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>>${sala.descricao}</textarea></td>
                    </tr>
                </table>
                <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
    </body>
</html>