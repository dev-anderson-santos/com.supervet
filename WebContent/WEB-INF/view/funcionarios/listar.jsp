<%@page import="com.supervet.model.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Super Vet - Funcionários</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/styles.css" />
        
        <script src="js/jquery.min.js"></script>
        <script src="js/sweetalert2.js"></script>
        <script src="js/promise-polyfill.js"></script>
    </head>
    <body>
    	<div class="container">
		        <div class="content">
	    			<c:import url="../componentes/header.jsp"></c:import>    		                       
					
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

		            <h2>Olá, ${funcionario_logado.nome}!</h2>
		            <hr>
		            <c:if test="${mensagem != null}">
				        <div class="alert alert-danger" role="alert">
						  ${mensagem}
						</div>
					</c:if>
			        
			        <h4>Funcionários cadastrados</h4>
			        <table class="table table-striped table-hover">
			            <thead>
				            <tr>
				                <th>Código</th>
				                <th>Nome</th>
				                <th>E-mail</th>
				                <th>CPF</th>
				                <th>Telefone</th>
				                <th>Cargo</th>				                
			                	<th class="text-center">Ações</th>
				            </tr>
			            </thead>
			            <tbody>
			            <c:forEach var="f" items="${funcionarios}">
			                <tr>
			                    <td>${f.id_funcionario}</td>
			                    <td>${f.nome}</td>
			                    <td>${f.email}</td>
			                    <td>${f.cpf}</td>
			                    <td>${f.telefone}</td>
			                    <td>${f.cargo}</td>
			                    <td class="text-center">
				                    <c:if test="${f.cargo != 'admin'}">
				                    	<a href="controlador?xpto=AlterarFuncionario&id=${f.id_funcionario}" class="btn btn-primary">Editar</a>
			                    	</c:if>
		                    	
			                    	<c:if test="${f.id_funcionario != funcionario_logado.id_funcionario}">
				                    	<button url-ajax="controlador?xpto=RemoverFuncionario&id=${f.id_funcionario}" class="btn-remover-fun btn btn-danger">Excluir</button>
				                    </c:if>
			                    </td>
			                </tr>
			            </c:forEach>
			            </tbody>
			        </table>
			        <div class="clearfix">&nbsp;<hr></div>
			        <div class="text-right">
			        	<a href="controlador?xpto=Home" class="btn btn-secondary">Voltar</a>
			        	<a href="controlador?xpto=CriarFuncionario" class="btn btn-primary">Novo</a>
			        </div>
			</div>
        </div>
        <script>
		$('.btn-remover-fun').on('click', function() {
			
			var url = $(this).attr('url-ajax');
			
			Swal.fire({
			  title: 'Excluir funcionário?',
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
			      'funcionario excluído com sucesso.',
			      'success'
			    ).then(function() {
				    window.location.replace(url);		    	
			    })
			  }
			})
		})
		
		</script>
    </body>
</html>
