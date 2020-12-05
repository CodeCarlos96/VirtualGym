
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Alunos</title>
    </head>
    <body>
        <form>
            <button type="submit" formaction="index.jsp">Menu</button>
        </form>
        <h1>Alunos</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Status</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${alunos}" var="aluno">
                <tr>
                    <td><c:out value="${aluno.idAluno}" /></td>
                    <td><c:out value="${aluno.usuario.nome}" /></td>
                    <td><c:out value="${aluno.usuario.status}" /></td>
                    <td><a href="ManterAlunoController?acao=prepararOperacao&operacao=Editar&idAluno=<c:out value="${aluno.idAluno}"/>">Editar</a></td>
                    <td><a href="ManterAlunoController?acao=prepararOperacao&operacao=Excluir&idAluno=<c:out value="${aluno.idAluno}"/>">Excluir</a></td>
                </tr>
            </c:forEach>

        </table>
        <form action="ManterAlunoController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>
