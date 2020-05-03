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
		            <div class="text-center">
	            		<a href="controlador?xpto=Setup" class="btn btn-warning">Gerar e popular base de dados</a>
	            	</div>
	            </div>
	        </div>
	        <c:if test="${mensagem != null}">
	            <div class="alert alert-info" role="alert">
				  	${mensagem}
				</div>
			</c:if>	
        </div>
    </body>
</html>
