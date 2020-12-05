
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Relatório de Frequência</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="relatorioFrequenciaBusca.jsp"><button>Voltar</button></a>
        <h1>Pesquisa de Alunos</h1>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Status</th>
                <th colspan="1">Ação</th>
            </tr>
            <c:forEach items="${alunos}" var="aluno">
                <tr>
                    <td><c:out value="${aluno.idAluno}" /></td>
                    <td><c:out value="${aluno.usuario.nome}" /></td>
                    <td><c:out value="${aluno.usuario.status}" /></td>
                    <td><a href="RelatorioFrequenciaController?acao=escolher&idAluno=<c:out value="${aluno.idAluno}"/>">Gerar relatório</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
