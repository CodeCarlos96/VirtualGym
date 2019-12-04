
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/exercicio.js" charset="utf-8"></script>
        <title>Virtual Gym</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaExercicioController"><button>Voltar</button></a>
        <h1>${operacao} Exercicio</h1>
        <form action="ManterExercicioController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="formManterExercicio" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Código: </td> <td><input type="number" name="txtIdExercicio" min="1" value="${exercicio.idExercicio}" <c:if test="${operacao != 'Incluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Nome: </td><td><input type="text" name="txtNome" maxlength="30" value="${exercicio.nome}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Tipo de Treino: </td>
                        <td>
                            <select name="optTipoTreino" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="Aerobico" <c:if test="${exercicio.tipoTreino == 'Aerobico'}"> selected </c:if>>Aerobico</option>
                            <option value="Musculação" <c:if test="${exercicio.tipoTreino == 'Musculação'}"> selected </c:if>>Musculação</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
    </body>
</html>