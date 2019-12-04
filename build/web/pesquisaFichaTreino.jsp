
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
        <h1>Pesquisa de Ficha de Treino</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Data de Inicio</th>
                <th>Data de Reavaliação</th>
                <th>Aluno</th>
                <th>Professor</th>
                <th colspan="3">Ação</th>
            </tr>
            <c:forEach items="${fichaTreinos}" var="fichaTreino">
                <tr>
                    <td><c:out value="${fichaTreino.idFichaTreino}" /></td>
                    <td><c:out value="${fichaTreino.dataInicio}" /></td>
                    <td><c:out value="${fichaTreino.dataReavaliacao}" /></td>
                    <td><c:out value="${fichaTreino.aluno.nome}" /></td>
                    <td><c:out value="${fichaTreino.professor.nome}" /></td>
                    <td><a href="ManterFichaTreinoController?acao=prepararOperacao&operacao=Editar&idFichaTreino=<c:out value="${fichaTreino.idFichaTreino}"/>">Editar</a></td>
                    <td><a href="ManterFichaTreinoController?acao=prepararOperacao&operacao=Excluir&idFichaTreino=<c:out value="${fichaTreino.idFichaTreino}"/>">Excluir</a></td>
                    <td><a href="PesquisaExercicioFichaController?idFichaTreino=<c:out value="${fichaTreino.idFichaTreino}"/>">Exercicios</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="ManterFichaTreinoController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>