
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/aerobico.js" charset="utf-8"></script>
        <title>Virtual Gym</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaExercicioFichaController?idFichaTreino=${fichaTreino.idFichaTreino}"><button>Voltar</button></a>
        <h1>${operacao} Exercicios Aerobicos (${fichaTreino.aluno.nome} / ${fichaTreino.dataInicio})</h1>
        <form action="ManterAerobicoController?acao=confirmarOperacao&operacao=${operacao}&idFichaTreino=${fichaTreino.idFichaTreino}&idAerobico=${aerobico.idAerobico}" method="post" name="formManterAerobico" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Ordem: </td>
                    <td>
                        <select name="optOrdem" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <c:forEach var="i" begin="1" end="${ordem}">
                                <option value="${i}" <c:if test="${aerobico.ordem == i}"> selected </c:if>>${i}</option>
                            </c:forEach>
                            <c:if test="${operacao == 'Incluir'}">
                                <option value="${ordem+1}" <c:if test="${aerobico.ordem == null}" > selected </c:if>>${ordem+1}</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Nome: </td>
                    <td>
                        <select name="optNome" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${aerobico.exercicio.idExercicio == null}" > selected </c:if> > </option>
                            <c:forEach items="${exercicios}" var="exercicio">
                                <option value="${exercicio.idExercicio}" <c:if test="${aerobico.exercicio.idExercicio == exercicio.idExercicio}"> selected </c:if>>${exercicio.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Tempo (min): </td> <td><input type="number" name="txtTempo" min="0" value="${aerobico.tempo}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Distância (km): </td> <td><input type="number" name="txtDistancia" min="0" value="${aerobico.distancia}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                </table>
                <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
    </body>
</html>