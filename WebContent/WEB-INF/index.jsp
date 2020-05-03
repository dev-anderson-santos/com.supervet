<%@page import="com.supervet.model.Cliente"%>
<%@page import="com.supervet.dao.ClienteDAO"%>
<%@page import="com.supervet.model.Funcionario"%>
<%@page import="com.supervet.dao.FuncionariosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SuperVet - Login</title>
    	<link rel="stylesheet" href="css/bootstrap.min.css" />
    	<link rel="stylesheet" href="css/styles.css" />
    	<link rel="stylesheet" href="css/fontawesome.min.css" />
    	
    	<script type="text/javascript" src="js/jquery.min.js"></script>
    	<script type="text/javascript" src="js/scripts.js"></script>
    </head>
    <body>
        <div class="container">
	        <div class="content">	        	
	        	<div class="login">
		        	<div class="img-logo">
		            	<img src="img/logo.png" alt=""/>
		            </div>	            
		            
		            <form id="form-login" method="POST" action="controlador?xpto=Index">
		            	<div class="funcionario">
			            	<div class="form-group">
				                <label>Email:</label>
				                <input type="email" class="form-control" name="email" id="login-email" placeholder="admin@email.com" value="admin@email.com">
			                </div>
			               	<div class="form-group">          
				                <label>Senha:</label>
				                <input type="password" class="form-control" name="senha" id="login-senha" placeholder="admin" value="admin">
			                </div>
		                </div>		                
		                
	                	<div class="cliente" style="display: none;">          
			                <div class="form-group">
				                <label>Matrícula:</label>
				                <input type="text" class="form-control" name="matricula" placeholder="1010">
			                </div>
		                </div>
		                
		                <div class="acoes">
			                <button class="btn btn-primary" type="submit">Entrar</button>
			                <a href="#" class="cli">Sou cliente</a>
			                <a href="#" class="func" style="display: none;">Sou funcionário</a>			                
		                </div>
		                
		            </form>
		            
		            <%		            	
		                String func_logado = (String) session.getAttribute("funcionario_logado");
		                String cli_logado = (String) session.getAttribute("cliente_logado");
		                
		                if (func_logado != null) {
		                    response.sendRedirect("controlador?xpto=Home");
						} 
						if (cli_logado != null) {
							response.sendRedirect("controlador?xpto=HomeCliente");
						}
		                
		                String senha = request.getParameter("senha");
		                String email = request.getParameter("email");
		                FuncionariosDAO funcDAO = new FuncionariosDAO();
		           		             
		                if (senha != null && !senha.isEmpty()) {
		                    if (funcDAO.validarLogin(email, senha)) {
		                        Funcionario func = funcDAO.getFuncionario(email, senha);
		                        
		                        session.setAttribute("funcionario_logado", func);		                        
		                        session.setAttribute("cargo_logado", func.getCargo());
		                        response.sendRedirect("controlador?xpto=Home");
		                    } else {
		                        request.setAttribute("mensagem", "Login inválido.");
		                    }
		                } else {
		                	String matricula = request.getParameter("matricula");		                	
		                	ClienteDAO clienteDAO = new ClienteDAO();
		                			                	
		                	if(matricula != null && !matricula.isEmpty()) {
		                		if(clienteDAO.checaLoginCliente(matricula)) {
		                			Cliente cli = clienteDAO.getClienteByMatricula(matricula);
			                        
			                        session.setAttribute("cliente_logado", cli);
			                        response.sendRedirect("controlador?xpto=HomeCliente");
		                		} else {
			                        request.setAttribute("mensagem", "Login inválido.");
			                    }
		                	}
		                }
		            %>	
	            </div>
	            <c:if test="${mensagem != null}">
		            <div class="alert alert-info" role="alert">
					  	${mensagem}
					</div>
				</c:if>	            
	        </div>
        </div>
    </body>
</html>
