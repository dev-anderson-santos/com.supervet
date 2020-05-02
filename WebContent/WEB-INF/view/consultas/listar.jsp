<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Super Vet - Consultas</title>
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
    			
				<h1>Consultas</h1>
				<table class="table table-striped table-hover">
					<thead>
			            <tr>			                			                
			                <th>Nome do pet</th>			                
			                <th>Data</th>
			                <th>Hora</th>
			                <th>Tipo</th>
			                <th>Status</th>
			                <c:if test="${cargo_logado != 'medico'}">
			                <th>Veterinário</th>
			                </c:if>
			                <th class="text-center">Ações</th>			                			                
			            </tr>
		            </thead>
		            <tbody>
		            	<c:choose>
		            		<c:when test="${ not empty consultas }">
		            			<c:forEach var="con" items="${consultas}">		            		
					                <tr>					                    					                    
					                    <td>${con.animal.nome}</td>
					                    <td>${con.data}</td>
					                    <td>${con.hora}</td>
					                    <td>${con.tipo}</td>
					                    <td>${con.status == 0 ? 'Agendada' : 'Atendida'}</td>
					                    <c:if test="${cargo_logado != 'medico' }">
					                    <td>${con.funcionario.nome}</td>					  
					                    </c:if>                  		                    
					                    <td class="text-center">
					                    	<c:if test="${funcionario_logado.cargo == 'medico' }">
			                					<button url-ajax="controlador?xpto=AtenderConsulta&id=${con.id_consulta}" ${con.status == 1 ? 'disabled' : ''} class="btn btn-success btn-done-consulta">Marcar como atendida</button>
			                				</c:if>			                				
					                    	<button url-ajax="controlador?xpto=RemoverConsulta&id=${con.id_consulta}" class="btn btn-danger btn-cancel-consulta" ${con.status == 1 ? 'disabled' : ''}>Cancelar consulta</button>				                    		
					                    </td>		                    	
					                </tr>
					            </c:forEach>	
		            		</c:when>
		            		<c:otherwise>
		            			<tr>
		            				<td>Não há consultas marcadas</td>
		            			</tr>
		            		</c:otherwise>
		            	</c:choose>		            	
		            </tbody>
				</table>				
				
				<div class="clearfix">&nbsp;<hr></div>
	        	<div class="text-right">
		        	 <a href="${cliente_logado != null ? 'controlador?xpto=HomeCliente' : 'controlador?xpto=Home'}" class="btn btn-secondary">Voltar</a>
		        	 <c:if test="${cliente_logado != null}">
		        	 	<a href="controlador?xpto=CriarConsulta" class="btn btn-primary">Nova</a>
		        	 </c:if>
	        	</div>
			</div>
		</div>
	</body>
	<script>	
	
	$('.btn-done-consulta').on('click', function() {
		var url = $(this).attr('url-ajax');
		
		Swal.fire({
		  title: 'Consulta.',
		  text: "Deseja alterar para atendida?",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  cancelButtonText: 'Não',
		  confirmButtonText: 'Sim'
		}).then(function (result) {
		  if (result.value) {
		    Swal.fire(
		      'Feito!',
		      'A consulta foi atendida.',
		      'success'
		    ).then(function() {
			    window.location.replace(url);		    	
		    })
		  }
		})	
	})
	
	$('.btn-cancel-consulta').on('click', function() {
		
		var url = $(this).attr('url-ajax');
		
		Swal.fire({
		  title: 'Desmarcar consulta?',
		  text: "Você não poderá cancelar esta ação!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  cancelButtonText: 'Não',
		  confirmButtonText: 'Sim'
		}).then(function (result) {
		  if (result.value) {
		    Swal.fire(
		      'Feito!',
		      'A consulta foi desmarcada.',
		      'success'
		    ).then(function() {
			    window.location.replace(url);		    	
		    })
		  }
		})
	})
	
	</script>
</html>