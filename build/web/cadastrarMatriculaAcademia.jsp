
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/matriculaAcademia.js" charset="utf-8"></script>
        <title>Virtual Gym</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaMatriculaAcademiaController"><button>Voltar</button></a>
        <h1>${operacao} MatriculaAcademia</h1>
        <form action="ManterMatriculaAcademiaController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="formManterMatriculaAcademia" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Código:</td>
                    <td><input type="number" name="txtIdMatriculaAcademia" min="1" value="${matriculaAcademia.idMatriculaAcademia}" <c:if test="${operacao != 'Incluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Aluno:</td>
                        <td>
                            <select name="optAluno" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${matriculaAcademia.aluno.idAluno == null}" > selected </c:if> > </option>
                            <c:forEach items="${alunos}" var="aluno">
                                <option value="${aluno.idAluno}" <c:if test="${matriculaAcademia.aluno.idAluno == aluno.idAluno}"> selected </c:if>>${aluno.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Plano:</td>
                    <td>
                        <select name="optPlano" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${matriculaAcademia.plano.idPlano == null}" > selected </c:if>> </option>
                            <c:forEach items="${planos}" var="plano">
                                <option value="${plano.idPlano}" <c:if test="${matriculaAcademia.plano.idPlano == plano.idPlano}"> selected </c:if>>${plano.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                    <tr>
                        <td>Data de Matricula: </td> <td><input type="date" name="txtDataMatricula" value="${matriculaAcademia.dataMatricula}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Dia de Vencimento das Parcelas: </td> <td><input type="number" name="txtDiaVencimento" min="1" max="28" value="${matriculaAcademia.diaVencimento}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
    </body>
</html>
