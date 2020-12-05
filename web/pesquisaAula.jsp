
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Aulas</title>
    </head>
    <body>
        <form>
            <button type="submit" formaction="index.jsp">Menu</button>
        </form>
        <h1>Aulas</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Valor</th>
                <th>Descrição</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${aulas}" var="aula">
                <tr>
                    <td><c:out value="${aula.idAula}" /></td>
                    <td><c:out value="${aula.nome}" /></td>
                    <td><c:out value="${aula.valor}" /></td>
                    <td><c:out value="${aula.descricao}" /></td>
                    <td><a href="ManterAulaController?acao=prepararOperacao&operacao=Editar&idAula=<c:out value="${aula.idAula}"/>">Editar</a></td>
                    <td><a href="ManterAulaController?acao=prepararOperacao&operacao=Excluir&idAula=<c:out value="${aula.idAula}"/>">Excluir</a></td>

                </tr>
            </c:forEach>

        </table>
        <form action="ManterAulaController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>
