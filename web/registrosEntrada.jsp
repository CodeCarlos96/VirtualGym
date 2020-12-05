
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Entradas</title>
    </head>
    <body>
        <form>
            <button type="submit" formaction="index.jsp">Menu</button>
        </form>
        <h1>Entradas</h1>
        
        <span id="erro" style="color: red">${erro}</span>

        <table border=1>
            <tr>
                <th>Código</th>
                <th>Data/Hora</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${entradas}" var="entrada">
                <tr>
                    <td><c:out value="${entrada.idEntrada}" /></td>
                    <td><c:out value="${entrada.dataEntrada}" /></td>
                    <td><a href="RegistrarEntradaController?acao=prepararOperacao&operacao=Editar&idEntrada=${entrada.idEntrada}&idAluno=${aluno.idAluno}">Editar</a></td>
                    <td><a href="RegistrarEntradaController?acao=prepararOperacao&operacao=Excluir&idEntrada=${entrada.idEntrada}&idAluno=${aluno.idAluno}">Excluir</a></td>
                </tr>
            </c:forEach>

        </table>
        <form action="RegistrarEntradaController?acao=prepararOperacao&operacao=Incluir&idAluno=${aluno.idAluno}" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>