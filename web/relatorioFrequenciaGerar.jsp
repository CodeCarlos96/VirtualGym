
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Gym - Relatório de Frequência</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="relatorioFrequenciaBusca.jsp"><button>Voltar</button></a>
        <h1>Relatório de Frequência</h1>
        <form action="RelatorioFrequenciaController?acao=gerar&idAluno=${idAluno}" method="post" name="formRelatorioFrequenciaGerar" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Data de inicio: </td> <td><input type="date" name="txtDataInicio" value="${dataInicio}"></td>
                </tr>
                <tr>
                    <td>Data de fim: </td> <td><input type="date" name="txtDataFim" value="${dataFim}"></td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Gerar Relatório">
        </form>
    </body>
</html>
