<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Super Vet</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/styles.css" />
    </head>
    <body>
        <div class="container">
    		<div class="content">
    			<div class="header">
    				
 					<img src="img/logo.png" alt="Super Vet"/>
    				
    				<p>    					
			            E-mail: ${cliente_logado.email}<br>
			            
			            Matrícula: ${cliente_logado.matricula}<br><br>
    				<a href="controlador?xpto=HomeCliente&acao=logout">Sair</a>
    				</p>
    			</div>  
            <h2>Olá, ${cliente_logado.nome}!</h2>
            <hr>
            <a href="controlador?xpto=CriarConsulta">Marcar consulta</a>
            <br>
            <a href="controlador?xpto=MostrarConsultas&id=${cliente_logado.id_cliente}">Consultas</a>
            <br>
            <h2><font color="blue">${mensagem_home}</font></h2>
            
            <%
                if (request.getParameter("acao") != null && request.getParameter("acao").equals("logout")) {
                    session.invalidate();
                    response.sendRedirect("controlador?xpto=Index");
                }
            %>
            <hr>
            <br>
            <c:if test="${ mensagem != null }">	            	
            	<div class="alert alert-success" role="alert">
				  ${ mensagem }
				</div>
            </c:if>
            </div>            
        </div>
    </body>
</html>