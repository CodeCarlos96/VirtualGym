
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/turma.js" charset="utf-8"></script>
        <title>Virtual Gym</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaTurmaController"><button>Voltar</button></a>
        <h1>${operacao} Turma</h1>
        <form action="ManterTurmaController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="formManterTurma" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Código:</td> <td><input type="number" name="txtIdTurma" min="1" value="${turma.idTurma}" <c:if test="${operacao != 'Incluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Sala:</td>
                        <td>
                            <select name="optSala" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${turma.sala.idSala == null}" > selected </c:if> > </option>
                            <c:forEach items="${salas}" var="sala">
                                <option value="${sala.idSala}" <c:if test="${turma.sala.idSala == sala.idSala}"> selected </c:if>>${sala.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Aula:</td>
                    <td>
                        <select name="optAula" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${turma.aula.idAula == null}" > selected </c:if> > </option>
                            <c:forEach items="${aulas}" var="aula">
                                <option value="${aula.idAula}" <c:if test="${turma.aula.idAula == aula.idAula}"> selected </c:if>>${aula.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Professor:</td>
                    <td>
                        <select name="optProfessor" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${turma.professor.idProfessor == null}" > selected </c:if> > </option>
                            <c:forEach items="${professores}" var="professor">
                                <option value="${professor.idProfessor}" <c:if test="${turma.professor.idProfessor == professor.idProfessor}"> selected </c:if>>${professor.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
    </body>
</html>
