<%@include file="/WEB-INF/pages/cabecalho.jsp"%>
<div class="container">
	<h2>Editar produto</h2>
	<form:form action="${s:mvcUrl('EC#buscaEdicao').build()}" method="POST"
		commandName="produto">
		<p class="erroEdicao">${msg}</p>
		<div class="form-group div">
			<label>Código: </label> <input type="text" name="codigo"
				class="codigo">
		</div>
		<button class="btn btn-primary botao_adicionar" style="margin-bottom:10px;">
			<span class="glyphicon glyphicon-plus"></span> Adicionar
		</button>
	</form:form>

	<form:form action="${s:mvcUrl('EC#alteraProduto').build()}"
		method="POST" commandName="produto" enctype="multipart/form-data">
		<div>
			<span id="mensagem">${mensagem}</span>
		</div>
		<div class="form-group">
			<label>Código: </label>
			<form:input path="codigo" id="codigo" class="form-control codigo"
				value="${produto.codigo}" />
			<form:errors path="codigo" class="control-label erro" />
		</div>
		<div class="form-group">
			<label>Descrição: </label>
			<form:input path="descricao" class="form-control"
				value="${produto.descricao}" />
			<form:errors path="descricao" class="control-label erro" />
		</div>
		<div class="form-group">
			<label>Cor: </label>
			<form:input path="cor" class="form-control" value="${produto.cor}" />
			<form:errors path="cor" class="control-label erro" />
		</div>
		<div class="form-group">
			<label>Preço até 1.000m: </label>
			<form:input path="precoAte1000" class="form-control preco"
				value="${produto.precoAte1000}" />
			<form:errors path="precoAte1000" class="control-label erro" />
		</div>
		<div class="form-group">
			<label>Preço de 1.100 a 3.000m: </label>
			<form:input path="precoAte3000" class="form-control preco"
				value="${produto.precoAte3000}" />
			<form:errors path="precoAte3000" class="control-label erro" />
		</div>
		<div class="form-group">
			<label>Preço acima de 3.100m: </label>
			<form:input path="precoAcima3000" class="form-control preco"
				value="${produto.precoAcima3000}" />
			<form:errors path="precoAcima3000" class="control-label erro" />
		</div>
		<div class="form-group">
			<label>IPI: </label>
			<form:input path="ipi" class="form-control porcentagem"
				value="${produto.ipi}" />
			<form:errors path="ipi" class="control-label erro" />
		</div>
		<input type="submit" value="Gravar" class="btn btn-primary pull-right"
			id="gravar">
		<input type="hidden" name="id" value="${produto.id}">
	</form:form>
</div>
<script>
	$(document).ready(function() {
		$('.codigo').mask('9.99.999.999');
		$('.preco').mask('R$ 9,999');
		$('.porcentagem').mask('9 %');
	});
</script>
<%@include file="/WEB-INF/pages/rodape.jsp"%>