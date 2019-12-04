
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
        <h1>Pesquisa de Salas</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Capacidade</th>
                <th>Descrição</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${salas}" var="sala">
                <tr>
                    <td><c:out value="${sala.idSala}" /></td>
                    <td><c:out value="${sala.nome}" /></td>
                    <td><c:out value="${sala.capacidade}" /></td>
                    <td><c:out value="${sala.descricao}" /></td>
                    <td><a href="ManterSalaController?acao=prepararOperacao&operacao=Editar&idSala=<c:out value="${sala.idSala}"/>">Editar</a></td>
                    <td><a href="ManterSalaController?acao=prepararOperacao&operacao=Excluir&idSala=<c:out value="${sala.idSala}"/>">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="ManterSalaController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>
