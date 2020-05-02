$(function() {
	$('.acoes .cli').on('click', function() {
		
		$('#form-login .funcionario').css('display', 'none');
		$('#form-login .cliente').css('display', 'block');
		$('#login-email').val("");
		$('#login-senha').val("");
		$(this).css('display', 'none');
		$('.acoes .func').css('display', 'block');
	});
	
	$('.acoes .func').on('click', function() {
		
		$('#form-login .cliente').css('display', 'none');
		$('#form-login .funcionario').css('display', 'block');
		
		$('#login-email').val("mario@email.com");
		$('#login-senha').val("1234");
		
		$(this).css('display', 'none');
		$('.acoes .cli').css('display', 'block');
	});
})