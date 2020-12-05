
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Avaliações</title>
    </head>
    <body>
        <form>
            <button type="submit" formaction="index.jsp">Menu</button>
        </form>
        <h1>Avaliações Fisica</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Data de Avaliação</th>
                <th>Data de Reavaliação</th>
                <th>Aluno</th>
                <th>Professor</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${avaliacoesFisicas}" var="avaliacaoFisica">
                <tr>
                    <td><c:out value="${avaliacaoFisica.idAvaliacaoFisica}" /></td>
                    <td><c:out value="${avaliacaoFisica.dataAvaliacao}" /></td>
                    <td><c:out value="${avaliacaoFisica.dataReavaliacao}" /></td>
                    <td><c:out value="${avaliacaoFisica.aluno.usuario.nome}" /></td>
                    <td><c:out value="${avaliacaoFisica.professor.usuario.nome}" /></td>
                    <td><a href="ManterAvaliacaoFisicaController?acao=prepararOperacao&operacao=Editar&idAvaliacaoFisica=<c:out value="${avaliacaoFisica.idAvaliacaoFisica}"/>">Editar</a></td>
                    <td><a href="ManterAvaliacaoFisicaController?acao=prepararOperacao&operacao=Excluir&idAvaliacaoFisica=<c:out value="${avaliacaoFisica.idAvaliacaoFisica}"/>">Excluir</a></td>

                </tr>
            </c:forEach>

        </table>
        <form action="ManterAvaliacaoFisicaController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>
