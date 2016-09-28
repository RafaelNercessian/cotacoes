<%@include file="/WEB-INF/pages/cabecalho.jsp"%>
<div class="container">
<h2>Editar todos os itens</h2>
<form:form action="${s:mvcUrl('EC#editarTudoPost').build()}" method="POST" commandName="valor">
		<p class="mensagem">${msg}</p>
		<div class="form-group div">
			<label>Fator multiplicação: </label> <input type="text" name="valor"
				class="codigo"> (%)
		</div>
		<button class="btn btn-primary botao_adicionar">
			<span class="glyphicon glyphicon-plus"></span> Inserir
		</button>
</form:form>
</div>
<%@include file="/WEB-INF/pages/rodape.jsp"%>