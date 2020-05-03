<%@page import="com.supervet.model.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Super Vet - Novo funcionário</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/styles.css" />
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        
        <script src="js/jquery.min.js"></script>
        <script src="js/sweetalert2.js"></script>
        <script src="js/promise-polyfill.js.js"></script>
    </head>
    <body>    	
        <%
            if (!session.getAttribute("cargo_logado").equals("admin")) {
                session.setAttribute("mensagem_home", "Somente administradores podem acessar esta área.");
                response.sendRedirect("controlador?xpto=Home");
            }
        %>
        <div class="container">
    		<div class="content">
    			<div class="header">    				
 					<img src="img/logo.png" alt="Super Vet"/>    				
    				<p>    					
			            E-mail: ${funcionario_logado.email}<br>			            
			            Cargo: ${funcionario_logado.cargo}<br><br>
    				<a href="controlador?xpto=Home&acao=logout">Sair</a>
    				</p>
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
            
	            <h2>Olá, ${funcionario_logado.nome}!</h2>
	            <hr>
		        <h2><font color="red">${mensagem}</font></h2>
		        <h2>Editar dados do funcionário</h2>
		        <form method="POST" action="controlador?xpto=AlterarFuncionario&acao=alterar&id=${funcionario.id_funcionario}">
		            <div class="form-group">
			      	<label for="inputEmail4">Nome do funcionário:</label>
			      	<input type="text" class="form-control" name="nome" placeholder="Nome" value="${ funcionario.nome }">
			      </div>
				  <div class="form-row">
				  	<div class="form-group col-md-4">
				      <label for="inputPassword4">E-mail</label>
				      <input type="email" class="form-control" name="email" placeholder="E-mail" value="${ funcionario.email }">
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputPassword4">CPF</label>
				      <input type="text" class="form-control" name="cpf" placeholder="CPF" value="${ funcionario.cpf }">
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputPassword4">Telefone</label>
				      <input type="text" class="form-control" name="telefone" placeholder="0000-0000" value="${ funcionario.telefone }">
				    </div>
				  </div>
				  <fieldset>
					<legend>Endereço</legend>
					  <div class="form-row">
						  <div class="form-group col-md-2">
						    <label for="cep">CEP</label>
						    <input type="text" class="form-control" id="cep" name="cep" placeholder="00000-000" value="${ funcionario_endereco.cep }">
						  </div>
					  </div>
					  <div class="form-group">
					    <label for="inputAddress2">Logradouro</label>
					    <input type="text" class="form-control" id="rua" name="logradouro" placeholder="Logradouro" value="${ funcionario_endereco.logradouro }">
					  </div>
					  <div class="form-row">
					  	<div class="form-group col-md-6">
						    <label for="inputAddress2">Bairro</label>
						    <input type="text" class="form-control" id="bairro" name="bairro" placeholder="Bairro" value="${ funcionario_endereco.bairro }">
					    </div>
					    <div class="form-group col-md-6">
					      <label for="inputCity">Cidade</label>
					      <input type="text" class="form-control" id="cidade" name="cidade" placeholder="Cidade" value="${ funcionario_endereco.cidade }">
					    </div>
					  </div>
					  <div class="form-row">
					  	<div class="form-group col-md-4">
						    <label for="complemento">Complemento</label>
						    <input type="text" class="form-control" name="complemento" id="complemento" placeholder="Apto, casa, etc" value="${ funcionario_endereco.complemento }">
					    </div>
					    <div class="form-group col-md-2">
					      <label for="numero">Número</label>
					      <input type="text" class="form-control" name="numero" id="numero" placeholder="Número" value="${ funcionario_endereco.numero }">
					    </div>
					    <div class="form-group col-md-2">
					      <label for="uf">UF</label>
					      <input type="text" class="form-control" id="uf" name="uf" placeholder="UF" value="${ funcionario_endereco.uf }">
					    </div>
					  </div>
				  </fieldset>
				  	<fieldset>
						<legend>Cargo e dados de acesso</legend>
					  	<div class="form-check">
						  <input class="form-check-input" type="radio" name="cargo" id="medico" value="medico" ${ funcionario.cargo == 'medico' ? 'checked' : '' }>
						  <label class="form-check-label" for="medico">
						    Médico
						  </label>
						</div>
						<div class="form-check">
						  <input class="form-check-input" type="radio" name="cargo" id="secretaria" value="secretaria" ${ funcionario.cargo == 'secretaria' ? 'checked' : '' }>
						  <label class="form-check-label" for="secretaria">
						    Secretaria
						  </label>
						</div>					
						<div class="form-check">
						  <input class="form-check-input" type="radio" name="cargo" id="admin" value="admin" ${ funcionario.cargo == 'admin' ? 'checked' : 'disabled' }>
						  <label class="form-check-label" for="admin">
						    Administrador
						  </label>
						</div>					  
							  
						  <div class="form-group col-md-4">
						    <label for="senha">Password</label>
						    <input type="password" class="form-control" id="senha" name=senha placeholder="Password" value="${ funcionario.senha }">
				  		  </div>
				  		  
					</fieldset>
				  	<hr>
				  	<div class="text-right">
			            <a href="controlador?xpto=Home" class="btn btn-secondary">Cancelar</a>
			            <button type="submit" class="btn btn-primary">Salvar</button>
		            </div>
		        </form>
		        <div class="clearfix">&nbsp;</div>
	        </div>
        </div>
        <script src="js/viacep.js"></script>
    </body>
</html>
