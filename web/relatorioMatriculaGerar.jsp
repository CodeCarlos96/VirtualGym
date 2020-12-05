
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/relatorioMatricula.js" charset="utf-8"></script>
        <title>Virtual Gym - Relatório de Matricula</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <h1>Relatório de Matricula</h1>
        <form action="RelatorioMatriculaController" method="post" name="formRelatorioMatriculaGerar" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Status: </td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="txtStatus1"> Ativo</td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="txtStatus2"> Inativo</td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="txtStatus3"> Inadimplente</td>
                </tr>
                
                <tr>
                    <td>Período de Matrícula: </td>
                </tr>
                <tr>
                    <td><input type="date" name="txtDataInicio" value="${dataInicio}"></td>
                </tr>
                <tr>
                    <td><input type="date" name="txtDataFim" value="${dataFim}"></td>
                </tr>
                
                <tr>
                    <td>Período de Vencimento: </td>
                </tr>
                <tr>
                    <td><input type="number" min="1" max="28" name="txtDiaInicio" value="${diaInicio}"></td>
                </tr>
                <tr>
                    <td><input type="number" min="1" max="28" name="txtDiaFim" value="${diaFim}"></td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Gerar Relatório">
        </form>
    </body>
</html>
