
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/relatorioFrequenciaBusca.js" charset="utf-8"></script>
        <title>Virtual Gym - Relatório de Frequência</title>
    </head>
    <body>
        <div></div>
        <a href="index.jsp"><button>Menu</button></a>
        <h1>Relatório de Frequência</h1>
        <form action="RelatorioFrequenciaController?acao=buscar" method="post" name="formRelatorioFrequenciaBusca" onsubmit="return validarFormulario(this)">
            <table>
                    <tr>
                        <td>Nome do Aluno: </td> <td><input type="text" name="txtNome"></td>
                    </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Buscar Aluno">
        </form>
    </body>
</html>
