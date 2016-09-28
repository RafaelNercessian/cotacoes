<%@include file="/WEB-INF/pages/cabecalho.jsp"%>
<div class="container">
	<h2>Olá, ${pageContext.request.userPrincipal.name}</h2>
	<h3>Tarefas:</h3>
	<ul>
		<li><a href="<c:url value="/admin/produto"/>">Adicionar um novo produto</a></li>
		<li><a href="<c:url value="/admin/editarProduto"/>">Editar um produto</a></li>
		<li><a href="<c:url value="/admin/editarTudo"/>">Atualizar preço de todos os produtos</a></li>
	</ul>
</div>
<%@include file="/WEB-INF/pages/rodape.jsp"%>