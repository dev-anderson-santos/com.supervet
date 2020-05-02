<%-- 
    Document   : animais
    Created on : 19/04/2019, 18:52:01
    Author     : Chiquito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualização de animais</title>
    </head>
    <body>
        <%
            if (session.getAttribute("cargo_logado").equals("Financeiro")) {
                session.setAttribute("mensagem_home", "Seu cargo de " + session.getAttribute("cargo_logado") + " não tem acesso a essa função.");
                response.sendRedirect("controllerServlet?command=Home");
            }
        %>
        <h1>Visualização de animais</h1>
        <h2><font color="red">${mensagem}</font></h2>
        <h4>Animais cadastrados</h4>
        <table border="1">
            <tr>
                <th>Código</th>
                <th>Idade</th>
                <th>Tipo</th>
                <th>Espécie</th>
                <th>Sexo</th>
                <th>Peso</th>
                <th>Jaula</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
            <c:forEach var="a" items="${animais}">
                <tr>
                    <td>${a.id_animal}</td>
                    <td>${a.idade}</td>
                    <td>${a.tipo}</td>
                    <td>${a.especie}</td>
                    <td>${a.sexo}</td>
                    <td>${a.peso}</td>
                    <td>
                        <a href="controllerServlet?command=EnjaularA&codigo=${a.id_animal}">
                            <c:if test = "${a.id_jaula == 0}">
                                <c:out value = "Nenhuma"/>
                            </c:if>
                            <c:if test = "${a.id_jaula != 0}">
                                <c:out value = "${a.id_jaula}"/>
                            </c:if>
                        </a>
                    </td>
                    <td><a href=controllerServlet?command=EditarA&codigo=${a.id_animal}>Editar</a>
                    <td><a href=controllerServlet?command=ExcluirA&codigo=${a.id_animal}>Excluir</a>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="controllerServlet?command=AdicionarA">Adicionar</a> | <a href="controllerServlet?command=Home">Voltar</a>
    </body>
</html>