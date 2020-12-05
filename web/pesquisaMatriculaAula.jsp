
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Matriculas</title>
    </head>
    <body>
        <form>
            <button type="submit" formaction="index.jsp">Menu</button>
        </form>
        <h1>Matriculas em Aulas</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Data</th>
                <th>Aluno</th>
                <th>Aula</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${matriculasAula}" var="matriculaAula">
                <tr>
                    <td><c:out value="${matriculaAula.idMatriculaAula}" /></td>
                    <td><c:out value="${matriculaAula.dataMatricula}" /></td>
                    <td><c:out value="${matriculaAula.aluno.usuario.nome}" /></td>
                    <td><c:out value="${matriculaAula.turma.aula.nome}" /></td>
                    <td><a href="ManterMatriculaAulaController?acao=prepararOperacao&operacao=Editar&idMatriculaAula=<c:out value="${matriculaAula.idMatriculaAula}"/>">Editar</a></td>
                    <td><a href="ManterMatriculaAulaController?acao=prepararOperacao&operacao=Excluir&idMatriculaAula=<c:out value="${matriculaAula.idMatriculaAula}"/>">Excluir</a></td>
                </tr>
            </c:forEach>

        </table>
        <form action="ManterMatriculaAulaController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>
