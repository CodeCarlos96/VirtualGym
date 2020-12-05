
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Cadastrar Musculação</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaExercicioFichaController?idFichaTreino=${fichaTreino.idFichaTreino}"><button>Voltar</button></a>
        <h1>${operacao} Exercicios de Musculação (${fichaTreino.aluno.usuario.nome} / ${fichaTreino.dataInicio})</h1>
        <form action="ManterMusculacaoController?acao=confirmarOperacao&operacao=${operacao}&idFichaTreino=${fichaTreino.idFichaTreino}&idMusculacao=${musculacao.idMusculacao}" method="post" name="formManterMusculacao" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Ordem: </td>
                    <td>
                        <select name="optOrdem" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <c:forEach var="i" begin="1" end="${ordem}">
                                <option value="${i}" <c:if test="${musculacao.ordem == i}"> selected </c:if>>${i}</option>
                            </c:forEach>
                            <c:if test="${operacao == 'Incluir'}">
                                <option value="${ordem+1}" <c:if test="${musculacao.ordem == null}" > selected </c:if>>${ordem+1}</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Nome: </td>
                    <td>
                        <select name="optNome" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${musculacao.exercicio.idExercicio == null}" > selected </c:if> > </option>
                            <c:forEach items="${exercicios}" var="exercicio">
                                <option value="${exercicio.idExercicio}" <c:if test="${musculacao.exercicio.idExercicio == exercicio.idExercicio}"> selected </c:if>>${exercicio.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Series: </td> <td><input type="number" name="txtSeries" min="0" value="${musculacao.series}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Peso (kg): </td> <td><input type="number" name="txtPeso" min="0" value="${musculacao.peso}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Repetições: </td> <td><input type="number" name="txtRepeticoes" min="0" value="${musculacao.repeticoes}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
        <script src="scripts/musculacao.js" charset="utf-8"></script>
    </body>
</html>