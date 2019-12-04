
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/matriculaAula.js" charset="utf-8"></script>
        <title>Virtual Gym</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaMatriculaAulaController"><button>Voltar</button></a>
        <h1>${operacao} MatriculaAula</h1>
        <form action="ManterMatriculaAulaController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="formManterMatriculaAula" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Código: </td> <td><input type="number" name="txtIdMatriculaAula" min="1" value="${matriculaAula.idMatriculaAula}" <c:if test="${operacao != 'Incluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Aluno: </td>
                        <td>
                            <select name="optAluno" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${matriculaAula.aluno.idAluno == null}" > selected </c:if> > </option>
                            <c:forEach items="${alunos}" var="aluno">
                                <option value="${aluno.idAluno}" <c:if test="${matriculaAula.aluno.idAluno == aluno.idAluno}"> selected </c:if>>${aluno.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Turma: </td>
                    <td>
                        <select name="optTurma" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${matriculaAula.turma.idTurma == null}" > selected </c:if>> </option>
                            <c:forEach items="${turmas}" var="turma">
                                <option value="${turma.idTurma}" <c:if test="${matriculaAula.turma.idTurma == turma.idTurma}"> selected </c:if>>
                                    ${turma.aula.nome} // 
                                    <c:forEach items="${horarios}" var="horario">
                                        <c:if test="${horario.turma.idTurma == turma.idTurma}">
                                            <c:if test="${horario.dia == 'Segunda'}">Segunda / </c:if>
                                            <c:if test="${horario.dia == 'Terça'}">Terça / </c:if>
                                            <c:if test="${horario.dia == 'Quarta'}">Quarta / </c:if>
                                            <c:if test="${horario.dia == 'Quinta'}">Quinta / </c:if>
                                            <c:if test="${horario.dia == 'Sexta'}">Sexta / </c:if>
                                            <c:if test="${horario.dia == 'Sabado'}">Sabado / </c:if>
                                            <c:if test="${horario.dia == 'Domingo'}">Domingo / </c:if>
                                        </c:if>
                                    </c:forEach>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                    <tr>
                        <td>Data de Matricula: </td> <td><input type="date" name="txtDataMatricula" value="${matriculaAula.dataMatricula}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Dia de Vencimento das Parcelas: </td> <td><input type="number" name="txtDiaVencimento" min="1" max="28" value="${matriculaAula.diaVencimento}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
    </body>
</html>
