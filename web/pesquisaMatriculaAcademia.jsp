
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
        <h1>Matriculas na Academia</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Data</th>
                <th>Aluno</th>
                <th>Plano</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${matriculasAcademia}" var="matriculaAcademia">
                <tr>
                    <td><c:out value="${matriculaAcademia.idMatriculaAcademia}" /></td>
                    <td><c:out value="${matriculaAcademia.dataMatricula}" /></td>
                    <td><c:out value="${matriculaAcademia.aluno.usuario.nome}" /></td>
                    <td><c:out value="${matriculaAcademia.plano.nome}" /></td>
                    <td><a href="ManterMatriculaAcademiaController?acao=prepararOperacao&operacao=Editar&idMatriculaAcademia=<c:out value="${matriculaAcademia.idMatriculaAcademia}"/>">Editar</a></td>
                    <td><a href="ManterMatriculaAcademiaController?acao=prepararOperacao&operacao=Excluir&idMatriculaAcademia=<c:out value="${matriculaAcademia.idMatriculaAcademia}"/>">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="ManterMatriculaAcademiaController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>
