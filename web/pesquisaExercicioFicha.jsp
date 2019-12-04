
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
            <button type="submit" formaction="PesquisaFichaTreinoController">Voltar</button>
        </form>
        <h1>Exercicios Ficha (${fichaTreino.aluno.nome} / ${fichaTreino.dataInicio})</h1>

        <table border=1>
            <h2>Aerobicos</h2>
            <tr>
                <th>Ordem</th>
                <th>Nome</th>
                <th>Tempo</th>
                <th>Distância</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${aerobicos}" var="aerobico">
                <tr>
                    <td><c:out value="${aerobico.ordem}º" /></td>
                    <td><c:out value="${aerobico.exercicio.nome}" /></td>
                    <td><c:out value="${aerobico.tempo} min" /></td>
                    <td><c:out value="${aerobico.distancia} km" /></td>
                    <td><a href="ManterAerobicoController?acao=prepararOperacao&operacao=Editar&idAerobico=<c:out value="${aerobico.idAerobico}"/>&idFichaTreino=<c:out value="${fichaTreino.idFichaTreino}"/>">Editar</a></td>
                    <td><a href="ManterAerobicoController?acao=prepararOperacao&operacao=Excluir&idAerobico=<c:out value="${aerobico.idAerobico}"/>&idFichaTreino=<c:out value="${fichaTreino.idFichaTreino}"/>">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="ManterAerobicoController?acao=prepararOperacao&operacao=Incluir&idFichaTreino=<c:out value="${fichaTreino.idFichaTreino}"/>" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>

        <table border=1>
            <h2>Musculação</h2>
            <tr>
                <th>Ordem</th>
                <th>Nome</th>
                <th>Series</th>
                <th>Peso</th>
                <th>Repetições</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${musculacoes}" var="musculacao">
                <tr>
                    <td><c:out value="${musculacao.ordem}º" /></td>
                    <td><c:out value="${musculacao.exercicio.nome}" /></td>
                    <td><c:out value="${musculacao.series}" /></td>
                    <td><c:out value="${musculacao.peso} kg" /></td>
                    <td><c:out value="${musculacao.repeticoes}" /></td>
                    <td><a href="ManterMusculacaoController?acao=prepararOperacao&operacao=Editar&idMusculacao=<c:out value="${musculacao.idMusculacao}"/>&idFichaTreino=<c:out value="${fichaTreino.idFichaTreino}"/>">Editar</a></td>
                    <td><a href="ManterMusculacaoController?acao=prepararOperacao&operacao=Excluir&idMusculacao=<c:out value="${musculacao.idMusculacao}"/>&idFichaTreino=<c:out value="${fichaTreino.idFichaTreino}"/>">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="ManterMusculacaoController?acao=prepararOperacao&operacao=Incluir&idFichaTreino=<c:out value="${fichaTreino.idFichaTreino}"/>" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>
