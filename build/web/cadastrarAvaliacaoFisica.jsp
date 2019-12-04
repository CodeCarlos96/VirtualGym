
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/avaliacaoFisica.js" charset="utf-8"></script>
        <title>Virtual Gym</title>
    </head>
    <body>
        <a href="index.jsp"><button>Menu</button></a>
        <a href="PesquisaAvaliacaoFisicaController"><button>Voltar</button></a>
        <h1>${operacao} Avaliação Fisica</h1>
        <form action="ManterAvaliacaoFisicaController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="formManterAvaliacaoFisica" onsubmit="return validarFormulario(this)">
            <span id="erro" style="color: red">${erro}</span>
            <table>
                <tr>
                    <td>Código: </td> <td><input type="number" name="txtIdAvaliacaoFisica" min="1" value="${avaliacaoFisica.idAvaliacaoFisica}" <c:if test="${operacao != 'Incluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Professor:</td>
                        <td>
                            <select name="optProfessor" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${avaliacaoFisica.professor.idProfessor == null}" > selected </c:if> > </option>
                            <c:forEach items="${professores}" var="professor">
                                <option value="${professor.idProfessor}" <c:if test="${avaliacaoFisica.professor.idProfessor == professor.idProfessor}"> selected </c:if>>${professor.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Aluno:</td>
                    <td>
                        <select name="optAluno" <c:if test="${operacao == 'Excluir'}"> disabled </c:if>>
                            <option value="0" <c:if test="${avaliacaoFisica.aluno.idAluno == null}" > selected </c:if> > </option>
                            <c:forEach items="${alunos}" var="aluno">
                                <option value="${aluno.idAluno}" <c:if test="${avaliacaoFisica.aluno.idAluno == aluno.idAluno}"> selected </c:if>>${aluno.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Data de Avaliação: </td> <td><input type="date" name="txtDataAvaliacao" value="${avaliacaoFisica.dataAvaliacao}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Data de Reavaliação: </td> <td><input type="date" name="txtDataReavaliacao" value="${avaliacaoFisica.dataReavaliacao}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Altura (cm): </td> <td><input type="number" step="0.01" name="txtAltura" min="0" value="${avaliacaoFisica.altura}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Peso (kg): </td> <td><input type="number" step="0.01" name="txtPeso" min="0" value="${avaliacaoFisica.peso}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro do Torax (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroTorax" min="0" value="${avaliacaoFisica.perimetroTorax}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro do Quadril (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroQuadril" min="0" value="${avaliacaoFisica.perimetroQuadril}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro do Abdomen (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroAbdomen" min="0" value="${avaliacaoFisica.perimetroAbdomen}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro da Cintura (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroCintura" min="0" value="${avaliacaoFisica.perimetroCintura}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro do Antebraço Direito (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroAntebracoDireito" min="0" value="${avaliacaoFisica.perimetroAntebracoDireito}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro do  Antebraço Esquerdo (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroAntebracoEsquerdo" min="0" value="${avaliacaoFisica.perimetroAntebracoEsquerdo}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro do Braço Direito (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroBracoDireito" min="0" value="${avaliacaoFisica.perimetroBracoDireito}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro do Braço Esquerdo (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroBracoEsquerdo" min="0" value="${avaliacaoFisica.perimetroBracoEsquerdo}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro da Coxa Direita (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroCoxaDireita" min="0" value="${avaliacaoFisica.perimetroCoxaDireita}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro da  Coxa Esquerda (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroCoxaEsquerda" min="0" value="${avaliacaoFisica.perimetroCoxaEsquerda}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro da Panturrilha Direita (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroPanturrilhaDireita" min="0" value="${avaliacaoFisica.perimetroPanturrilhaDireita}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Perimetro da Panturrilha Esquerda (cm): </td> <td><input type="number" step="0.01" name="txtPerimetroPanturrilhaEsquerda" min="0" value="${avaliacaoFisica.perimetroPanturrilhaEsquerda}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Dobra Subescapular (cm):</td> <td> <input type="number" step="0.01" name="txtDobraSubescapular" min="0" value="${avaliacaoFisica.dobraSubescapular}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Dobra Tricipital (cm): </td> <td><input type="number" step="0.01" name="txtDobraTricipital" min="0" value="${avaliacaoFisica.dobraTricipital}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Dobra Peitoral (cm):</td> <td> <input type="number" step="0.01" name="txtDobraPeitoral" min="0" value="${avaliacaoFisica.dobraPeitoral}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Dobra Abdominal (cm):</td> <td> <input type="number" step="0.01" name="txtDobraAbdominal" min="0" value="${avaliacaoFisica.dobraAbdominal}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Dobra Supra-ilíaca (cm): </td> <td><input type="number" step="0.01" name="txtDobraSuprailiaca" min="0" value="${avaliacaoFisica.dobraSuprailiaca}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Dobra da Coxa Direita (cm):</td> <td> <input type="number" step="0.01" name="txtDobraCoxaDireita" min="0" value="${avaliacaoFisica.dobraCoxaDireita}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                    </tr>
                    <tr>
                        <td>Dobra da Coxa Esquerda (cm):</td> <td> <input type="number" step="0.01" name="txtDobraCoxaEsquerda" min="0" value="${avaliacaoFisica.dobraCoxaEsquerda}" <c:if test="${operacao == 'Excluir'}"> readonly </c:if>></td>
                </tr>
            </table>
            <input type="submit" name="btnConfirmar" value="Confirmar">
        </form>
    </body>
</html>