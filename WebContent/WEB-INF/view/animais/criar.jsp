<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar novo animal</title>
    </head>
    <body>
    	<div class="container">
	   		<div class="content">
	   			<div class="header">   				
					<img src="img/logo.png" alt="Super Vet"/>   				
	   				<p>    					
			            E-mail: ${cliente_logado.email}<br>		            
			            Matrícula: ${cliente_logado.matricula}<br><br>
	   					<a href="controlador?xpto=Home&acao=logout">Sair</a>
	   				</p>
	   			</div> 
		        <%
		            if (!session.getAttribute("cargo_logado").equals("admin") && !session.getAttribute("cargo_logado").equals("medico")) {
		                session.setAttribute("mensagem_home", "Seu cargo de " + session.getAttribute("cargo_logado") + " não tem acesso a essa função.");
		                response.sendRedirect("controllerServlet?xpto=Home");
		            }
		        %>
		        <h2><font color="red">${mensagem}</font></h2>
		        <h2>Adicionar novo animal</h2>
		        <form method="POST" action="controllerServlet?xpto=CriarAnimal&acao=cadastrar">		            
					<div class="form-row">
						<div class="form-group col-md-6">
						<label for="inputEmail4">Email</label>
						<input type="email" class="form-control" id="inputEmail4" placeholder="Email">
					</div>
					<div class="form-group col-md-6">
						<label for="inputPassword4">Password</label>
						<input type="password" class="form-control" id="inputPassword4" placeholder="Password">
						</div>
					</div>
					<div class="form-group">
						<label for="inputAddress">Address</label>
						<input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St">
					</div>
					<div class="form-group">
						<label for="inputAddress2">Address 2</label>
						<input type="text" class="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor">
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputCity">City</label>
							<input type="text" class="form-control" id="inputCity">
						</div>
						<div class="form-group col-md-4">
							<label for="inputState">State</label>
							<select id="inputState" class="form-control">
							<option selected>Choose...</option>
							<option>...</option>
							</select>
						</div>
						<div class="form-group col-md-2">
							<label for="inputZip">Zip</label>
							<input type="text" class="form-control" id="inputZip">
						</div>
					</div>
					<div class="form-group">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="gridCheck">
						<label class="form-check-label" for="gridCheck">
							Check me out
						</label>
					</div>
					</div>
					<button type="submit" class="btn btn-primary">Cadastrar</button>			
		            
		            <a href="controllerServlet?xpto=ExibeA">Cancelar</a>
		        </form>
	        </div>
        </div>
    </body>
</html>