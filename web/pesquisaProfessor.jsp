
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Professores</title>
    </head>
    <body>
        <form>
            <button type="submit" formaction="index.jsp">Menu</button>
        </form>
        <h1>Professores</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Data de Admissão</th>
                <th>Status</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${professores}" var="professor">
                <tr>
                    <td><c:out value="${professor.idProfessor}" /></td>
                    <td><c:out value="${professor.usuario.nome}" /></td>
                    <td><c:out value="${professor.dataAdmissao}" /></td>
                    <td><c:out value="${professor.usuario.status}" /></td>
                    <td><a href="ManterProfessorController?acao=prepararOperacao&operacao=Editar&idProfessor=<c:out value="${professor.idProfessor}"/>">Editar</a></td>
                    <td><a href="ManterProfessorController?acao=prepararOperacao&operacao=Excluir&idProfessor=<c:out value="${professor.idProfessor}"/>">Excluir</a></td>
                </tr>
            </c:forEach>

        </table>
        <form action="ManterProfessorController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>
