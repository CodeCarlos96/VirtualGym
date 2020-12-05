
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Administradores</title>
    </head>
    <body>
        <form>
            <button type="submit" formaction="index.jsp">Menu</button>
        </form>
        <h1>Administradores</h1>
        <table border=1>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Data de Admissão</th>
                <th>Status</th>
                <th colspan="2">Ação</th>
            </tr>
            <c:forEach items="${administradores}" var="administrador">
                <tr>
                    <td><c:out value="${administrador.idAdministrador}" /></td>
                    <td><c:out value="${administrador.usuario.nome}" /></td>
                    <td><c:out value="${administrador.dataAdmissao}" /></td>
                    <td><c:out value="${administrador.usuario.status}" /></td>
                    <td><a href="ManterAdministradorController?acao=prepararOperacao&operacao=Editar&idAdministrador=<c:out value="${administrador.idAdministrador}"/>">Editar</a></td>
                    <td><a href="ManterAdministradorController?acao=prepararOperacao&operacao=Excluir&idAdministrador=<c:out value="${administrador.idAdministrador}"/>">Excluir</a></td>
                </tr>
            </c:forEach>

        </table>
        <form action="ManterAdministradorController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
    </body>
</html>
