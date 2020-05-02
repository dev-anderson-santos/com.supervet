<%-- 
    Document   : excluir
    Created on : 21/04/2019, 16:41:02
    Author     : Chiquito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Excluir ${funcionario.nome}</title>
    </head>
    <body>
        <%
            if (!session.getAttribute("cargo_logado").equals("Administrador")) {
                session.setAttribute("mensagem_home", "Apenas administradores tem acesso a essa função.");
                response.sendRedirect("controllerServlet?command=Home");
            }
        %>
        <h2>Tem certeza que deseja excluir a entrada abaixo?</h2>
        <table border="1">
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Sexo</th>
                <th>Idade</th>
                <th>Email</th>
                <th>CPF</th>
                <th>Cargo</th>
            </tr>
            <tr>
                <td>${funcionario.id_func}</td>
                <td>${funcionario.nome}</td>
                <td>${funcionario.sexo}</td>
                <td>${funcionario.idade}</td>
                <td>${funcionario.email}</td>
                <td>${funcionario.cpf}</td>
                <td>${funcionario.cargo}</td>
            </tr>
        </table>
        <br>
        Sim, <a href="controllerServlet?command=ExcluirF&resposta=confirma&codigo=${funcionario.id_func}">EXCLUIR</a>.
        <br>
        Não, <a href="controllerServlet?command=ExibeF">MANTER</a>.
    </body>
</html>
