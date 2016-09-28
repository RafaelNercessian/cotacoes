<%@include file="/WEB-INF/pages/cabecalho.jsp" %>
<div class="container center_div">
	<form id="formularioLogin" class="form-signin" name='loginForm'
		action="<c:url value='/j_spring_security_check' />" method='POST'>
		<h2 class="form-signin-heading">Login</h2>
		<c:if test="${not empty error}">
			<div class="erro">${error}</div>
		</c:if>
		<label class="sr-only">Login</label> 
			<input type='text' name='username' class="form-control login"
			placeholder="Login" required autofocus> 
		<label class="sr-only">Senha</label> 
			<input type='password' name='password' class="form-control login"
			placeholder="Senha" required>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
	</form>
</div>
<%@include file="/WEB-INF/pages/rodape.jsp" %>