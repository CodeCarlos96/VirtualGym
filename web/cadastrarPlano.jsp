
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Cadastrar Plano</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaPlanoController"><button>Voltar</button></a>
        <h1>${operacao} Plano</h1>
        <form action="ManterPlanoController?acao=confirmarOperacao&operacao=${operacao}&idPlano=${plano.idPlano}" method="post" name="formManterPlano" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Nome: </td> <td><input type="text" name="txtNome" maxlength="40" value="${plano.nome}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Tipo: </td>
                        <td>
                            <select name="optTipoPlano" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="Diario" <c:if test="${plano.tipo == 'Diario'}"> selected </c:if>>Diario</option>
                            <option value="Semanal" <c:if test="${plano.tipo == 'Semanal'}"> selected </c:if>>Semanal</option>
                            <option value="Mensal" <c:if test="${plano.tipo == 'Mensal'}"> selected </c:if>>Mensal</option>
                            <option value="Trimestral" <c:if test="${plano.tipo == 'Trimestral'}"> selected </c:if>>Trimestral</option>
                            <option value="Semestral" <c:if test="${plano.tipo == 'Semestral'}"> selected </c:if>>Semestral</option>
                            <option value="Anual" <c:if test="${plano.tipo == 'Anual'}"> selected </c:if>>Anual</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Taxa de Adesão: </td> <td><input type="number" step="0.01" name="txtTaxaAdesao" min="0" value="${plano.taxaAdesao}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Parcelas: </td> <td><input type="number" name="txtParcelas" min="1" max="48" value="${plano.parcelas}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Valor Total: </td> <td><input type="number" step="0.01" name="txtValor" min="0" value="${plano.valor}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Taxa de Juros (%): </td> <td><input type="number" step="0.01" name="txtTaxaJuros" min="0" max="100" value="${plano.taxaJuros}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
        <script src="scripts/plano.js" charset="utf-8"></script>
    </body>
</html>
