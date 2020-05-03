<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
        <meta charset="ISO-8859-1">
        
        <title>SuperVet - Consultas</title>
    	<link rel="stylesheet" href="css/bootstrap.min.css" />
    	<link rel="stylesheet" href="css/styles.css" />
    	<link rel="stylesheet" href="css/fontawesome.min.css" />
    	
    	<script type="text/javascript" src="js/jquery.min.js"></script>
    </head>
<body>
	<div class="container">
   		<div class="content">
   			<c:import url="../componentes/header.jsp"></c:import>   		                       
           <%
                if (request.getParameter("acao") != null && request.getParameter("acao").equals("logout")) {
                    session.invalidate();
                    response.sendRedirect("controlador?xpto=Index");
                }
            %>
            <h2>Ol�, ${cliente_logado.nome}!</h2>
            <hr>
            <h3>Marcar consulta</h3>
            <form method="POST" action="controlador?xpto=CriarConsulta&acao=cadastrar">
            	<input type="hidden" value="${ cliente_logado.id_cliente }" name="id_cliente" />
            	<input type="hidden" value="${ animal_cliente.id_animal }" name="id_animal" />
			    <input type="hidden" value="0" name="status" />
			    			    
				<div class="form-group">			    			
			    	<label>Pet a ser atendido</label>
			    	<input type="text" class="form-control" value="${animal_cliente.nome}" disabled />			    
			    </div>
				<div class="form-row">
					<div class="form-group col-md-2">
						<label for="data">Data</label>
						<input type="date" class="form-control" id="data" name="data" required="required">
					</div>
					<div class="form-group col-md-2">
						<label for="hora">Hor�rio</label>	
						<select class="form-control" id="hora" name="hora" required="required">
							<option value="07:30">07:30</option>
							<option value="08:00">08:00</option>
							<option value="08:30">08:30</option>
							<option value="09:00">09:00</option>
						</select>
					</div>
					<div class="form-group col-md-4">
						<label for="tipo">Tipo da consulta</label>
						<select class="form-control" name="tipo" id="tipo" required="required">
							<option value="Rotina">Rotina</option>
							<option value="Vacina��o">Vacina��o</option>
							<option value="Desparasita��o">Desparasita��o</option>
							<option value="Aplica��o de medicamento">Aplica��o de medicamento</option>
						</select>
					</div>
					<div class="form-group col-md-4">
						<label for="tipo">Veterin�rio</label>
						<select class="form-control" name="id_funcionario" id="id_funcionario" required="required">
							<c:forEach var="f" items="${ funcionarios_medicos }">
								<option value="${ f.id_funcionario }">${ f.nome }</option>
							</c:forEach>							
						</select>
					</div>
				</div>				
				<div class="text-right">
	        	 	<a href="controlador?xpto=HomeCliente" class="btn btn-secondary">Voltar</a>
					<button type="submit" class="btn btn-primary">Marcar consulta</button>
	        	</div>
			</form>			
		</div>
	</div>
</body>
</html>