<%-- 
    Document   : jaulas
    Created on : 19/04/2019, 18:51:50
    Author     : Chiquito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualização de jaulas</title>
    </head>
    <body>
        <%
            if (session.getAttribute("cargo_logado").equals("Financeiro")) {
                session.setAttribute("mensagem_home", "Seu cargo de " + session.getAttribute("cargo_logado") + " não tem acesso a essa função.");
                response.sendRedirect("controllerServlet?command=Home");
            }
        %>
        <h1>Visualização de jaulas</h1>
        <h2><font color="red">${mensagem}</font></h2>
        <h4>Jaulas cadastradas</h4>
        <table border="1">
            <tr>
                <th>Código</th>
                <th>Tipo</th>
                <th>Animais contidos</th>
                <th>Excluir</th>
            </tr>
            <c:forEach var="j" items="${jaulas}">
                <tr>
                    <td>${j.id_jaula}</td>
                    <td><a href="controllerServlet?command=EditarJ&codigo=${j.id_jaula}">${j.tipo}</a></td>
                    <td><a href="controllerServlet?command=DetalhesJ&codigo=${j.id_jaula}">Animais contidos</a>
                    <td><a href="controllerServlet?command=ExcluirJ&codigo=${j.id_jaula}">Excluir</a>
                        <%--<c:forEach var="aj" items="${j.animais}">(${aj.id_animal}, ${aj.tipo}, ${aj.especie})</c:forEach>--%>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="controllerServlet?command=AdicionarJ">Adicionar</a> | <a href="controllerServlet?command=Home">Voltar</a>
    </body>
</html>
