<%@page import="com.supervet.model.Funcionario"%>
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
           
            <h2>Olï¿½, ${funcionario_logado.nome}!</h2>
            <hr>
	            <h1>Dashboard</h1>
	            <div class="row">
			        <div class="col-lg-3 col-xs-6">
			          <!-- small box -->
			          <div class="small-box bg-aqua">
			            <div class="inner">
			              <h3>${ count_fun }</h3>
			
			              <p>Funcionï¿½rios</p>
			            </div>
			            <div class="icon">
			              <i class="ion ion-bag"></i>
			            </div>
			            <a href="controlador?xpto=MostrarFuncionarios" class="small-box-footer">Mais informaï¿½ï¿½es</a>
			          </div>
			        </div>
			        <!-- ./col -->
			        <div class="col-lg-3 col-xs-6">
			          <!-- small box -->
			          <div class="small-box bg-green">
			            <div class="inner">
			              <h3>${ count_cliente }</h3>
			
			              <p>Clientes</p>
			            </div>
			            <div class="icon">
			              <i class="ion ion-stats-bars"></i>
			            </div>
			            <a href="controlador?xpto=MostrarClientes" class="small-box-footer">Mais informaï¿½ï¿½es</a>
			          </div>
			        </div>
			        <!-- ./col -->
			        <div class="col-lg-3 col-xs-6">
			          <!-- small box -->
			          <div class="small-box bg-yellow">
			            <div class="inner">
			              <h3>${ count_consulta }</h3>
			              <p>Consultas registradas</p>
			            </div>
			            <div class="icon">
			              <i class="ion ion-person-add"></i>
			            </div>
			            <a href="controlador?xpto=MostrarConsultas" class="small-box-footer">Mais informaï¿½ï¿½es</a>
			          </div>
			        </div>
			        <!-- ./col -->
			        <div class="col-lg-3 col-xs-6">
			          <!-- small box -->
			          <div class="small-box bg-red">
			            <div class="inner">
			              <h3>${ count_animal }</h3>
			
			              <p>Animais cadastrados</p>
			            </div>
			            <div class="icon">
			              <i class="ion ion-pie-graph"></i>
			            </div>
			            <a href="controlador?xpto=MostrarAnimais" class="small-box-footer">Mais informaï¿½ï¿½es</a>
			          </div>
			        </div>
			        <!-- ./col -->
			      </div>          
	            
	            <%
					Funcionario func_logado = (Funcionario) session.getAttribute("funcionario_logado");
					if (func_logado == null) {
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