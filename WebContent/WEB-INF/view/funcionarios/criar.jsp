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
            
	            <h2>Olá, ${funcionario_logado.nome}!</h2>
	            <hr>
		        <h2><font color="red">${mensagem}</font></h2>
		        <h2>Adicionar novo funcionário</h2>
		        <form method="POST" action="controlador?xpto=CriarFuncionario&acao=cadastrar">
		            <div class="form-group">
			      	<label for="inputEmail4">Nome do cliente:</label>
			      	<input type="text" class="form-control" name="nome" placeholder="Nome">
			      </div>
				  <div class="form-row">
				  	<div class="form-group col-md-4">
				      <label for="inputPassword4">E-mail</label>
				      <input type="email" class="form-control" name="email" placeholder="E-mail">
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputPassword4">CPF</label>
				      <input type="text" class="form-control" name="cpf" maxlength="14" placeholder="CPF">
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputPassword4">Telefone</label>
				      <input type="text" class="form-control" name="telefone" maxlength="9" placeholder="0000-0000">
				    </div>
				  </div>
				  <fieldset>
					<legend>Endereço</legend>
					  <div class="form-row">
						  <div class="form-group col-md-2">
						    <label for="cep">CEP</label>
						    <input type="text" class="form-control" id="cep" maxlength="9" name="cep" placeholder="00000-000">
						  </div>
					  </div>
					  <div class="form-group">
					    <label for="inputAddress2">Logradouro</label>
					    <input type="text" class="form-control" id="rua" name="logradouro" placeholder="Logradouro">
					  </div>
					  <div class="form-row">
					  	<div class="form-group col-md-6">
						    <label for="inputAddress2">Bairro</label>
						    <input type="text" class="form-control" id="bairro" name="bairro" placeholder="Bairro">
					    </div>
					    <div class="form-group col-md-6">
					      <label for="inputCity">Cidade</label>
					      <input type="text" class="form-control" id="cidade" name="cidade" placeholder="Cidade">
					    </div>
					  </div>
					  <div class="form-row">
					  	<div class="form-group col-md-4">
						    <label for="complemento">Complemento</label>
						    <input type="text" class="form-control" name="complemento" id="complemento" placeholder="Apto, casa, etc">
					    </div>
					    <div class="form-group col-md-2">
					      <label for="numero">Número</label>
					      <input type="text" class="form-control" name="numero" id="numero" placeholder="Número">
					    </div>
					    <div class="form-group col-md-2">
					      <label for="uf">UF</label>
					      <input type="text" class="form-control" id="uf" name="uf" placeholder="UF">
					    </div>
					  </div>
				  </fieldset>
				  	<fieldset>
						<legend>Cargo e dados de acesso</legend>
					  	<div class="form-check">
						  <input class="form-check-input" type="radio" name="cargo" id="medico" value="medico" checked>
						  <label class="form-check-label" for="exampleRadios1">
						    Médico
						  </label>
						</div>
						<div class="form-check">
						  <input class="form-check-input" type="radio" name="cargo" id="secretaria" value="secretaria">
						  <label class="form-check-label" for="exampleRadios2">
						    Secretaria
						  </label>
						</div>					
						<div class="form-check">
						  <input class="form-check-input" type="radio" name="cargo" id="admin" value="admin" disabled>
						  <label class="form-check-label" for="exampleRadios3">
						    Administrador
						  </label>
						</div>					  
							  
						  <div class="form-group col-md-4">
						    <label for="senha">Password</label>
						    <input type="password" class="form-control" id="senha" name=senha placeholder="Password">
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
