<%-- 
    Document   : excluir
    Created on : 23/04/2019, 00:19:50
    Author     : Chiquito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Excluir animal</title>
    </head>
    <body>
        <%
            if (!session.getAttribute("cargo_logado").equals("Administrador") && !session.getAttribute("cargo_logado").equals("Treinador")) {
                session.setAttribute("mensagem_home", "Seu cargo de " + session.getAttribute("cargo_logado") + " não tem acesso a essa função.");
                response.sendRedirect("controllerServlet?command=Home");
            }
        %>
        <h2>Tem certeza que deseja excluir a entrada abaixo?</h2>
        <table border="1">
            <tr>
                <th>Código</th>
                <th>Idade</th>
                <th>Tipo</th>
                <th>Espécie</th>
                <th>Sexo</th>
                <th>Peso</th>
                <th>Jaula</th>
            </tr>
            <tr>
                <td>${animal.id_animal}</td>
                <td>${animal.idade}</td>
                <td>${animal.tipo}</td>
                <td>${animal.especie}</td>
                <td>${animal.sexo}</td>
                <td>${animal.peso}</td>
                <td>${animal.id_jaula}</td>
            </tr>
        </table>
        <br>
        Sim, <a href="controllerServlet?command=ExcluirA&resposta=confirma&codigo=${animal.id_animal}">EXCLUIR</a>.
        <br>
        Não, <a href="controllerServlet?command=ExibeA">MANTER</a>.
    </body>
</html>
