<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cotação Cabolider</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/mask.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/funcao.js"/>"></script>
</head>
<body>
	<div id="cabecalho">
		<div id=container>
			<a href="<c:url value="/"/>"><img src="<c:url value="/resources/imagens/logo.png"/>"
				alt="logo_cabolider" id="logoCompany"/></a>
		</div>
		<div class="container">
				<a href="<c:url value="/admin"/>" class="pull-right administrador">Administrador</a>
		</div>
	</div>