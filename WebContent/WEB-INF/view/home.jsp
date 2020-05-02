<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Super Vet</title>
        
        <title>SuperVet - Login</title>
    	<link rel="stylesheet" href="css/bootstrap.min.css" />
    	<link rel="stylesheet" href="css/styles.css" />
    	<link rel="stylesheet" href="css/fontawesome.min.css" />
    	
    	<script type="text/javascript" src="js/jquery.min.js"></script>
    </head>
    <body>
    	<div class="container">
    		<div class="content">
    			<c:import url="componentes/header.jsp"></c:import>    		                       
            
	            <h2>Olá, ${funcionario_logado.nome}!</h2>
	            <hr>
	            
	            <div class="home-cards">
	            <c:if test="${ funcionario_logado.cargo == 'admin'}">
	            <div class="card" style="width: 18rem;">
				  <img src="img/funcionarios.png" class="card-img-top" alt="Consultas">
				  <div class="card-body">
				    <h5 class="card-title">Funcionários</h5>
				    <p class="card-text">Acessar relação de funcionários.</p>
				    <a href="controlador?xpto=MostrarFuncionarios" class="btn btn-primary">Funcionários</a>
				  </div>
				</div>            	            
	            
	            </c:if>
	            <c:if test="${ funcionario_logado.cargo == 'admin' ||  funcionario_logado.cargo == 'secretaria'}">
	            <div class="card" style="width: 18rem;">
				  <img src="img/clientes.png" class="card-img-top" alt="Consultas">
				  <div class="card-body">
				    <h5 class="card-title">Clientes</h5>
				    <p class="card-text">Acessar a lista de clientes.</p>
				    <a href="controlador?xpto=MostrarClientes" class="btn btn-primary">Clientes</a>
				  </div>
				</div>
	            </c:if>
	            
	            <div class="card" style="width: 18rem;">
				  <img src="img/consulta.jpg" class="card-img-top" alt="Consultas">
				  <div class="card-body">
				    <h5 class="card-title">Consultas</h5>
				    <p class="card-text">Acessar as consultas marcadas.</p>
				    <a href="controlador?xpto=MostrarConsultas&id=${funcionario_logado.id_funcionario }" class="btn btn-primary">Consultas</a>
				  </div>
				</div>
	            
	            </div>            
	            
	            <%
		            String checar_sessao = (String) session.getAttribute("nome_logado");
	                if (checar_sessao == null) {
	                	request.setAttribute("mensagem", "É necessário realizar o login para continuar acessando.");
	                    response.sendRedirect("controlador?xpto=Index");
	                } else if (request.getParameter("acao") != null && request.getParameter("acao").equals("logout")) {
	                    session.invalidate();
	                    response.sendRedirect("controlador?xpto=Index");
	                }
	            %>
	            
	            
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