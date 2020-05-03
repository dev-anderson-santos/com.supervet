<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="ISO-8859-1">
        <title>Super Vet</title>
        
    	<link rel="stylesheet" href="css/bootstrap.min.css" />
    	<link rel="stylesheet" href="css/styles.css" />
    	<link rel="stylesheet" href="css/fontawesome.min.css" />
    	
    	<script type="text/javascript" src="js/jquery.min.js"></script>    	
    </head>
<body>
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
		<form method="POST" action="controlador?xpto=AlterarCliente&acao=alterar&id=${ cliente.id_cliente }">		  
	      <fieldset>
		  	<legend>Dados do cliente</legend>
		      <div class="form-group">
		      	<label for="inputEmail4">Nome do cliente:</label>
		      	<input type="text" class="form-control" name="nome" placeholder="Nome" value="${ cliente.nome }">
		      </div>
			  <div class="form-row">
			  	<div class="form-group col-md-4">
			      <label for="inputPassword4">E-mail</label>
			      <input type="email" class="form-control" name="email" placeholder="E-mail" value="${ cliente.email }">
			    </div>
			    <div class="form-group col-md-4">
			      <label for="inputPassword4">CPF</label>
			      <input type="text" class="form-control" maxlength="11" name="cpf" placeholder="CPF" value="${ cliente.cpf }">
			    </div>
			    <div class="form-group col-md-4">
			      <label for="inputPassword4">Telefone</label>
			      <input type="text" class="form-control" maxlength="9" name="telefone" placeholder="0000-0000" value="${ cliente.telefone }">
			    </div>
			  </div>
			  <div class="clearfix">&nbsp;</div>
		  </fieldset>
		  <fieldset>
		  	<legend>Endere�o</legend>
			  <div class="form-row">
				  <div class="form-group col-md-2">
				    <label for="cep">CEP</label>
				    <input type="text" class="form-control" maxlength="9" id="cep" name="cep" placeholder="00000-000" value="${ cliente_endereco.cep }">
				  </div>
			  </div>
			  <div class="form-group">
			    <label for="inputAddress2">Logradouro</label>
			    <input type="text" class="form-control" id="rua" name="logradouro" placeholder="Logradouro" value="${ cliente_endereco.logradouro }">
			  </div>
			  <div class="form-row">
			  	<div class="form-group col-md-6">
				    <label for="inputAddress2">Bairro</label>
				    <input type="text" class="form-control" id="bairro" name="bairro" placeholder="Bairro" value="${ cliente_endereco.bairro }">
			    </div>
			    <div class="form-group col-md-6">
			      <label for="inputCity">Cidade</label>
			      <input type="text" class="form-control" id="cidade" name="cidade" placeholder="Cidade" value="${ cliente_endereco.cidade }">
			    </div>
			  </div>
			  <div class="form-row">
			  	<div class="form-group col-md-4">
				    <label for="inputAddress2">Complemento</label>
				    <input type="text" class="form-control" name="complemento" placeholder="Apto, casa, etc" value="${ cliente_endereco.complemento }">
			    </div>
			    <div class="form-group col-md-2">
			      <label for="inputCity">N�mero</label>
			      <input type="text" class="form-control" name="numero" placeholder="N�mero" value="${ cliente_endereco.numero }">
			    </div>
			    <div class="form-group col-md-2">
			      <label for="inputZip">UF</label>
			      <input type="text" class="form-control" maxlength="2" id="uf" name="uf" placeholder="UF" value="${ cliente_endereco.uf }">
			    </div>
			  </div>
		  </fieldset>
		  
		  <fieldset>
		  	<legend>Dados do pet</legend>
			  <div class="form-group">
			    <label for="inputAddress2">Nome do pet</label>
			    <input type="text" class="form-control" name="nome_pet" placeholder="Nome do pet" value="${ cliente_animal.nome }">
			  </div>
			  <div class="form-row">
			  	<div class="form-group col-md-6">
				    <label for="inputAddress2">Ra�a</label>
				    <input type="text" class="form-control" name="raca" placeholder="Ra�a" value="${ cliente_animal.raca }">
			    </div>
			    <div class="form-group col-md-6">
			      <label for="inputCity">Tipo</label>
			      <select class="form-control" name="tipo">
			      	<option value="1" ${ cliente_animal.tipo == 1 ? 'selected' : '' }>Cachorro</option>
			      	<option value="2" ${ cliente_animal.tipo == 2 ? 'selected' : '' }>Gato</option>
			      </select>
			    </div>
			  </div>
			  <div class="form-row">
			  	<div class="form-group col-md-4">
				    <label for="inputAddress2">Idade</label>
				    <input type="number" class="form-control" name="idade" placeholder="Idade" value="${ cliente_animal.idade }">
			    </div>
			    <div class="form-group col-md-2">
			      <label for="inputCity">Sexo</label>
			      <select class="form-control" name="sexo">
			      	<option value="1" ${ cliente_animal.sexo == 1 ? 'selected' : '' }>Macho</option>
			      	<option value="2" ${ cliente_animal.sexo == 2 ? 'selected' : '' }>F�mea</option>
			      </select>
			    </div>
			  </div>
			  <div class="form-row">		    
			    <div class="form-group col-md-4">
			      <label for="inputState">Peso</label>
			      <input type="number" class="form-control" name="peso" placeholder="Peso" value="${ cliente_animal.peso }">
			    </div>
			  </div>
		  </fieldset>
		  <div class="text-right">
		  	<a href="controlador?xpto=Home" class="btn btn-secondary">Cancelar</a>
		  	<button type="submit" class="btn btn-primary">Salvar</button>
		  </div>
		</form>
		<div class="clearfix">&nbsp;</div>
	</div>
</div>
<script type="text/javascript" src="js/viacep.js"></script>
</body>
</html>