
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/horario.js" charset="utf-8"></script>
        <title>Virtual Gym</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaHorarioController?idTurma=<c:out value="${turma.idTurma}"/>"><button>Voltar</button></a>
        <h1>${operacao} Horário (${turma.aula.nome} / ${turma.sala.nome})</h1>
        <form action="ManterHorarioController?acao=confirmarOperacao&operacao=${operacao}&idTurma=<c:out value="${turma.idTurma}"/>&idHorario=${horario.idHorario}" method="post" name="formManterHorario" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Dia: </td>
                    <td>
                        <select name="optDia" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="Segunda" <c:if test="${horario.dia == 'Segunda'}"> selected </c:if>>Segunda</option>
                            <option value="Terça" <c:if test="${horario.dia == 'Terça'}"> selected </c:if>>Terça</option>
                            <option value="Quarta" <c:if test="${horario.dia == 'Quarta'}"> selected </c:if>>Quarta</option>
                            <option value="Quinta" <c:if test="${horario.dia == 'Quinta'}"> selected </c:if>>Quinta</option>
                            <option value="Sexta" <c:if test="${horario.dia == 'Sexta'}"> selected </c:if>>Sexta</option>
                            <option value="Sabado" <c:if test="${horario.dia == 'Sabado'}"> selected </c:if>>Sabado</option>
                            <option value="Domingo" <c:if test="${horario.dia == 'Domingo'}"> selected </c:if>>Domingo</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Horário de Inicio: </td><td><input type="time" name="txtHoraInicio" value="${horario.horaInicio}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Horário de Fim: </td><td><input type="time" name="txtHoraFim" value="${horario.horaFim}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                </table>
                <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
    </body>
</html>