<%@include file="/WEB-INF/pages/cabecalho.jsp"%>
<div class="container">
	<h2 class="titulo_cotacao">Montar cotação</h2>
	<form:form action="${s:mvcUrl('PC#busca').build()}" method="POST">
		<p class="mensagem_produto_nao_encontrado">${msg}</p>
		<div class="form-group div" id="div_codigo">
			<label>Código: </label> <input type="text" name="codigo"
				class="codigo">
		</div>
		<button class="btn btn-primary botao_adicionar">
			<span class="glyphicon glyphicon-plus"></span> Adicionar
		</button>
	</form:form>

	<h3 id="itens_cotacao">Itens da cotação</h3>
	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>Código</th>
				<th>Descrição</th>
				<th>Cor</th>
				<th>Até 1.000m</th>
				<th>De 1.100 a 3.000m</th>
				<th>Acima 3.000m</th>
				<th>IPI</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${lista}" var="produto">
				<tr>
					<td>${produto.codigo}</td>
					<td>${produto.descricao}</td>
					<td>${produto.cor}</td>
					<td>${produto.precoAte1000} / metro</td>
					<td>${produto.precoAte3000} /metro</td>
					<td>${produto.precoAcima3000} / metro</td>
					<td>${produto.ipi}</td>
					<td><a
						href="${s:mvcUrl('PC#deleta').arg(0,produto.codigo).build()}"><span
							class="glyphicon glyphicon-remove"></span> Deletar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${not empty lista}">
		<form:form action="${s:mvcUrl('PC#geraCotacao').build()}">
				<div id="pintura">
					<label>Será necessário pintura?</label> <br> <label
						class="radio-inline"><input type="radio" name="pintura"
						value="Nao" checked="checked">Não</label> <label
						class="radio-inline"><input type="radio" name="pintura"
						value="Sim">Sim</label>
				</div>
				<div>
					<label>Será necessário corte?</label> <br> <label
						class="radio-inline"><input type="radio" name="corte"
						value="Nao" checked="checked">Não</label> <label
						class="radio-inline"><input type="radio" name="corte"
						value="Sim">Sim</label>
				</div>
			<button class="btn btn-success pull-right" style="margin-bottom:50px;">
				<span class="glyphicon glyphicon-list-alt"></span> Gerar cotação
			</button>
		</form:form>
	</c:if>
</div>
<script>
	$(document).ready(function() {
		$('.codigo').mask('9.99.999.999');
	});
</script>
<%@include file="/WEB-INF/pages/rodape.jsp"%>
