
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Horários</title>
    </head>
    <body>
        <form>
            <button type="submit" formaction="index.jsp">Menu</button>
            <button type="submit" formaction="PesquisaTurmaController">Voltar</button>
        </form>
        <h1>Horários da Turma - (${turma.aula.nome} / ${turma.sala.nome})</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Dia</th>
                <th>Hora de Inicio</th>
                <th>Hora de Fim</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${horarios}" var="horario">
                <tr>
                    <td><c:out value="${horario.idHorario}" /></td>
                    <td><c:out value="${horario.dia}" /></td>
                    <td><c:out value="${horario.horaInicio}" /></td>
                    <td><c:out value="${horario.horaFim}" /></td>
                    <td><a href="ManterHorarioController?acao=prepararOperacao&operacao=Editar&idHorario=<c:out value="${horario.idHorario}"/>&idTurma=<c:out value="${turma.idTurma}"/>">Editar</a></td>
                    <td><a href="ManterHorarioController?acao=prepararOperacao&operacao=Excluir&idHorario=<c:out value="${horario.idHorario}"/>&idTurma=<c:out value="${turma.idTurma}"/>">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="ManterHorarioController?acao=prepararOperacao&operacao=Incluir&idTurma=<c:out value="${turma.idTurma}"/>" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>