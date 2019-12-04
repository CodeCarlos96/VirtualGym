
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
        <h1>Pesquisa de Planos</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Valor</th>
                <th>Taxa de Adesão</th>
                <th>Parcelas</th>
                <th>Tipo</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${planos}" var="plano">
                <tr>
                    <td><c:out value="${plano.idPlano}" /></td>
                    <td><c:out value="${plano.nome}" /></td>
                    <td><c:out value="${plano.valor}" /></td>
                    <td><c:out value="${plano.taxaAdesao}" /></td>
                    <td><c:out value="${plano.parcelas}" /></td>
                    <td><c:out value="${plano.tipo}" /></td>
                    <td><a href="ManterPlanoController?acao=prepararOperacao&operacao=Editar&idPlano=<c:out value="${plano.idPlano}"/>">Editar</a></td>
                    <td><a href="ManterPlanoController?acao=prepararOperacao&operacao=Excluir&idPlano=<c:out value="${plano.idPlano}"/>">Excluir</a></td>
                </tr>
            </c:forEach>

        </table>
        <form action="ManterPlanoController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>