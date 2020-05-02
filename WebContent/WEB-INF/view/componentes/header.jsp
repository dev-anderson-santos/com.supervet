<div class="header">	    				
	<img src="img/logo.png" alt="Super Vet"/>    				
	<p>    					
         E-mail: ${cliente_logado != null ? cliente_logado.email : funcionario_logado.email}<br>			            
         ${cliente_logado != null ? 'Matrícula' : 'Cargo'}: ${cliente_logado != null ? cliente_logado.matricula : funcionario_logado.cargo}<br><br>
		<a href="${cliente_logado != null ? 'controlador?xpto=HomeCliente&acao=logout' : 'controlador?xpto=Home&acao=logout'}">Sair</a>
	</p>
</div>