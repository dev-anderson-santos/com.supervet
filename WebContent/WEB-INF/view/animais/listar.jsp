<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Super Vet - Animais</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/styles.css" />
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        
        <script src="js/jquery.min.js"></script>
        <script src="js/sweetalert2.js"></script>
        <script src="js/promise-polyfill.js.js"></script>
    </head>
	<body>
		<div class="container">
			<div class="content">
				<c:import url="../componentes/header.jsp"></c:import>
    			
				<h1>Animais cadastrados</h1>
				<table class="table table-striped table-hover">
					<thead>
			            <tr>			                			                
			                <th>Nome do pet</th>			                
			                <th>Raça</th>
			                <th>Tipo</th>
			                <th>Idade</th>
			                <th>Sexo</th>			                
			                <th>Peso</th>               			                
			            </tr>
		            </thead>
		            <tbody>
		            	<c:choose>
		            		<c:when test="${ not empty animais }">
		            			<c:forEach var="animal" items="${animais}">		            		
					                <tr>					                    					                    
					                    <td>${animal.nome}</td>
					                    <td>${animal.raca}</td>
					                    <td>${animal.tipo == 1 ? 'Cachorro' : 'Gato'}</td>
					                    <td>${animal.idade}</td>
					                    <td>${animal.sexo == 1 ? 'Macho' : 'Fêmea'}</td>					                    
					                    <td>${animal.peso}</td>
   					                </tr>
					            </c:forEach>	
		            		</c:when>
		            		<c:otherwise>
		            			<tr>
		            				<td>Não há aniamis cadastrados</td>
		            			</tr>
		            		</c:otherwise>
		            	</c:choose>		            	
		            </tbody>
				</table>				
				
				<div class="clearfix">&nbsp;<hr></div>
	        	<div class="text-right">
		        	 <a href="controlador?xpto=Home" class="btn btn-secondary">Voltar</a>		        	 
	        	</div>
			</div>
		</div>
	</body>	
</html>