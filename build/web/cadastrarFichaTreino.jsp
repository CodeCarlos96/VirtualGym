
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/fichaTreino.js" charset="utf-8"></script>
        <title>Virtual Gym</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaFichaTreinoController"><button>Voltar</button></a>
        <h1>${operacao} Ficha de Treino</h1>
        <form action="ManterFichaTreinoController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="formManterFichaTreino" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Código: </td><td><input type="number" name="txtIdFichaTreino" min="1" value="${fichaTreino.idFichaTreino}" <c:if test="${operacao != 'Incluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Aluno: </td>
                        <td>
                            <select name="optAluno" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${fichaTreino.aluno.idAluno == null}" > selected </c:if> > </option>
                            <c:forEach items="${alunos}" var="aluno">
                                <option value="${aluno.idAluno}" <c:if test="${fichaTreino.aluno.idAluno == aluno.idAluno}"> selected </c:if>>${aluno.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Professor: </td>
                    <td>
                        <select name="optProfessor" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${fichaTreino.professor.idProfessor == null}" > selected </c:if> > </option>
                            <c:forEach items="${professores}" var="professor">
                                <option value="${professor.idProfessor}" <c:if test="${fichaTreino.professor.idProfessor == professor.idProfessor}"> selected </c:if>>${professor.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Data de Inicio: </td>
                    <td><input type="date" name="txtDataInicio" value="${fichaTreino.dataInicio}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Data de Reavaliação: </td>
                        <td><input type="date" name="txtDataReavaliacao" value="${fichaTreino.dataReavaliacao}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Dias: </td>
                        <td><input type="checkbox" name="optDia1" value="1" <c:if test="${fn:contains(fichaTreino.dias, '1')}"> checked </c:if><c:if test="${operacao == 'Excluir'}"> disabled </c:if>>Segunda
                        <input type="checkbox" name="optDia2" value="2" <c:if test="${fn:contains(fichaTreino.dias, '2')}"> checked </c:if><c:if test="${operacao == 'Excluir'}"> disabled </c:if>>Terça
                        <input type="checkbox" name="optDia3" value="3" <c:if test="${fn:contains(fichaTreino.dias, '3')}"> checked </c:if><c:if test="${operacao == 'Excluir'}"> disabled </c:if>>Quarta
                        <input type="checkbox" name="optDia4" value="4" <c:if test="${fn:contains(fichaTreino.dias, '4')}"> checked </c:if><c:if test="${operacao == 'Excluir'}"> disabled </c:if>>Quinta
                        <input type="checkbox" name="optDia5" value="5" <c:if test="${fn:contains(fichaTreino.dias, '5')}"> checked </c:if><c:if test="${operacao == 'Excluir'}"> disabled </c:if>>Sexta
                        <input type="checkbox" name="optDia6" value="6" <c:if test="${fn:contains(fichaTreino.dias, '6')}"> checked </c:if><c:if test="${operacao == 'Excluir'}"> disabled </c:if>>Sabado
                        <input type="checkbox" name="optDia7" value="7" <c:if test="${fn:contains(fichaTreino.dias, '7')}"> checked </c:if><c:if test="${operacao == 'Excluir'}"> disabled </c:if>>Domingo</td>
                    </tr>
                    <tr>
                        <td>Observação: </td>
                            <td><textarea name="txtObservacao" maxlength="100" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>>${fichaTreino.observacao}</textarea></td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
    </body>
</html>
