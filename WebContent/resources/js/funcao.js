$(document).ready(function(){
	var mensagem=document.querySelector('.mensagem');
	if(mensagem.textContent=="Não é possível inserir um número menor que 0 (zero)!"){
		mensagem.style.color='red';
	}	
});

$(document).ready(function(){
	var mensagem=document.querySelector('.erroEdicao');
	if(mensagem.textContent=="O produto a ser editado não existe!"){
		mensagem.style.color='red';
	}	
});