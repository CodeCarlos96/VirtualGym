
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
        <h1>Pesquisa de Exercicios</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Tipo de Treino</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${exercicios}" var="exercicio">
                <tr>
                    <td><c:out value="${exercicio.idExercicio}" /></td>
                    <td><c:out value="${exercicio.nome}" /></td>
                    <td><c:out value="${exercicio.tipoTreino}" /></td>
                    <td><a href="ManterExercicioController?acao=prepararOperacao&operacao=Editar&idExercicio=<c:out value="${exercicio.idExercicio}"/>">Editar</a></td>
                    <td><a href="ManterExercicioController?acao=prepararOperacao&operacao=Excluir&idExercicio=<c:out value="${exercicio.idExercicio}"/>">Excluir</a></td>
                </tr>
            </c:forEach>

        </table>
        <form action="ManterExercicioController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>
