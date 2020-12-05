
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Cadastrar Entrada</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="RegistrarEntradaController?acao=entradas&idAluno=${aluno.idAluno}"><button>Voltar</button></a>

        <h1>${operacao} Entrada (${aluno.usuario.nome})</h1>

        <form action="RegistrarEntradaController?acao=confirmarOperacao&operacao=${operacao}&idAluno=${aluno.idAluno}&idEntrada=${entrada.idEntrada}" method="post" name="formManterEntrada" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Data / Hora: </td>
                    <td><input type="datetime-local" name="txtDataEntrada" value="${data}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
        <script src="scripts/entrada.js" charset="utf-8"></script>
    </body>
</html>