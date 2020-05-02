<%-- 
    Document   : editar
    Created on : 22/04/2019, 15:28:34
    Author     : Chiquito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar funcionário</title>
    </head>
    <body>
        <%
            if (!session.getAttribute("cargo_logado").equals("Administrador")) {
                session.setAttribute("mensagem_home", "Apenas administradores tem acesso a essa função.");
                response.sendRedirect("controllerServlet?command=Home");
            }
        %>
        <h2><font color="red">${mensagem}</font></h2>
        <h2>Editar funcionário</h2>
        Dados atuais:
        <br>
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
        <br>
        <form method="POST" action="controllerServlet?command=EditarF&acao=editar&id=${funcionario.id_func}">
            Nome: <input type="text" name="nome" value="${funcionario.nome}"/> <br>
            Sexo:<br><input type="radio" name="sexo" value="Masculino">Masculino<br><input type="radio" name="sexo" value="Feminino">Feminino<br><input type="radio" name="sexo" value="Outro">Outro<br>
            Idade: <input type="text" name="idade" value="${funcionario.idade}"/> <br>
            Email: <input type="text" name="email" value="${funcionario.email}"/> <br>
            CPF: <input type="text" name="cpf" value="${funcionario.cpf}"/> <br>
            Senha: <input type="password" name="senha" value="${funcionario.senha}"/> <br>
            Cargo:<br><input type="radio" name="cargo" value="Administrador">Administrador<br><input type="radio" name="cargo" value="Financeiro">Financeiro<br><input type="radio" name="cargo" value="Treinador">Treinador<br><input type="radio" name="cargo" value="Limpeza">Limpeza
            <br><br>
            <input type="submit" value="Concluir">
            <a href="controllerServlet?command=ExibeF">Cancelar</a>
        </form>
    </body>
</html>