
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form>
            <button type="submit" formaction="index.jsp">Menu</button>
        </form>
        <h1>Pesquisa de Turma</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Aula</th>
                <th>Sala</th>
                <th>Professor</th>
                <th>Capacidade</th>
                <th>Matriculados</th>
                <th colspan="3">Ação</th>
            </tr>
            <c:forEach items="${turmas}" var="turma">
                <tr>
                    <td><c:out value="${turma.idTurma}" /></td>
                    <td><c:out value="${turma.aula.nome}" /></td>
                    <td><c:out value="${turma.sala.nome}" /></td>
                    <td><c:out value="${turma.professor.nome}" /></td>
                    <td><c:out value="${turma.sala.capacidade}" /></td>
                    <td><c:out value="${turma.matriculados}" /></td>
                    <td><a href="ManterTurmaController?acao=prepararOperacao&operacao=Editar&idTurma=<c:out value="${turma.idTurma}"/>">Editar</a></td>
                    <td><a href="ManterTurmaController?acao=prepararOperacao&operacao=Excluir&idTurma=<c:out value="${turma.idTurma}"/>">Excluir</a></td>
                    <td><a href="PesquisaHorarioController?idTurma=<c:out value="${turma.idTurma}"/>">Horários</a></td>
                </tr>
            </c:forEach>

        </table>
        <form action="ManterTurmaController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>