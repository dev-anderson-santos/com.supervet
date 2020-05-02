<%-- 
    Document   : editar
    Created on : 24/04/2019, 17:25:34
    Author     : Chiquito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar animal</title>
    </head>
    <body>
        <%
            if (!session.getAttribute("cargo_logado").equals("Administrador") && !session.getAttribute("cargo_logado").equals("Treinador")) {
                session.setAttribute("mensagem_home", "Seu cargo de " + session.getAttribute("cargo_logado") + " não tem acesso a essa função.");
                response.sendRedirect("controllerServlet?command=Home");
            }
        %>
        <h2><font color="red">${mensagem}</font></h2>
        <h2>Editar animal</h2>
        <br>
        Dados atuais do animal:
        <br>
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
                <td>
                    <c:if test = "${animal.id_jaula == 0}">
                        <c:out value = "Nenhuma"/>
                    </c:if>
                    <c:if test = "${animal.id_jaula != 0}">
                        <c:out value = "${animal.id_jaula}"/>
                    </c:if>
                </td>
            </tr>
        </table>
        <br><br>
        <form method="POST" action="controllerServlet?command=EditarA&tarefa=editar&codigo=${animal.id_animal}">
            Idade: <input type="text" name="idade" value="${animal.idade}"/> <br>
            Sexo:<br><input type="radio" name="sexo" value="Masculino" checked="checked">Masculino<br><input type="radio" name="sexo" value="Feminino">Feminino<br><input type="radio" name="sexo" value="Outro">Outro<br>
            Tipo:<br><input type="radio" name="tipo" value="Terrestre" checked="checked">Terrestre<br><input type="radio" name="tipo" value="Aquatico">Aquático<br><input type="radio" name="tipo" value="Voador">Voador<br>OBS: Se o tipo for alterado, o animal será desalocado da jaula em que estiver.<br>
            Espécie: <input type="input" name="especie" value="${animal.especie}"/> <br>
            Peso: <input type="input" name="peso" value="${animal.peso}"/> <br>
            <br><br>
            <input type="submit" value="Concluir">
            <a href="controllerServlet?command=ExibeA">Cancelar</a>
        </form>
    </body>
</html>

