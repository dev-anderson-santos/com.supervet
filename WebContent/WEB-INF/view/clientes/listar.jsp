<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Super Vet - Clientes</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/styles.css" />
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        
        <script src="js/jquery.min.js"></script>
        <script src="js/sweetalert2.js"></script>
        <script src="js/promise-polyfill.js"></script>
    </head>
	<body>
		<div class="container">
			<div class="content">
				<c:import url="../componentes/header.jsp"></c:import>
    			
				<h1>Clientes</h1>
				<table class="table table-striped table-hover">
					<thead>
			            <tr>			                			                
			                <th>Matrícula</th>
			                <th>Nome</th>			                
			                <th>E-mail</th>
			                <th>CPF</th>
			                <th>Telefone</th>
			                <th class="text-center">Ações</th>			                			                
			            </tr>
		            </thead>
		            <tbody>
		            	<c:choose>
		            		<c:when test="${ not empty clientes }">
		            			<c:forEach var="cli" items="${clientes}">		            		
					                <tr>					                    					                    
					                    <td>${cli.matricula}</td>
					                    <td>${cli.nome}</td>
					                    <td>${cli.email}</td>
					                    <td>${cli.cpf}</td>
					                    <td>${cli.telefone}</td>
					                    <td class="text-center">					                    			                				
					                    	<a href="controlador?xpto=AlterarCliente&id=${cli.id_cliente}" class="btn btn-primary" id="btn-cancel-consulta">Editar</a>
					                    	<button url-ajax="controlador?xpto=RemoverCliente&id=${cli.id_cliente}" class="btn btn-danger btn-remover-cli">Remover</button>				                    		
					                    	
					                    </td>		                    	
					                </tr>
					            </c:forEach>	
		            		</c:when>
		            		<c:otherwise>
		            			<tr>
		            				<td>Não há clientes cadastrados</td>
		            			</tr>
		            		</c:otherwise>
		            	</c:choose>		            	
		            </tbody>
				</table>
				<div class="clearfix">&nbsp;<hr></div>
				<div class="text-right">
		        	 <a href="controlador?xpto=Home" class="btn btn-secondary">Voltar</a>
		        	 <a href="controlador?xpto=CriarCliente" class="btn btn-primary">Novo</a>
	        	</div>
			</div>
		</div>
	</body>
	<script>
	$('.btn-remover-cli').on('click', function() {
		
		var url = $(this).attr('url-ajax');
		
		Swal.fire({
		  title: 'Excluir cliente?',
		  text: "Você não poderá reverter esta ação!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  cancelButtonText: 'Não',
		  confirmButtonText: 'Sim'
		}).then(function (result) {
		  if (result.value) {
		    Swal.fire(
		      'Feito',
		      'Cliente excluído com sucesso.',
		      'success'
		    ).then(function() {
			    window.location.replace(url);		    	
		    })
		  }
		})
	})
	
	</script>
</html>