<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 
<head>
	<meta charset="utf-8">
    <title>File Download</title>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    	<script src="static/js/jquery-3.0.0.min.js" type="text/javascript"></script>
		<script src="static/js/popup.js" type="text/javascript"></script>
</head>
<body onload="digitalWatch()">
		<div style="position: absolute; left:550px; top: 120px;">
			<p id="digital_watch" style="color: #000; font-size: 120%; font-weight: bold;"></p>
		</div>
    <div class="form-container">
        <h1><span class="uniform-bg">Welcome to FileDownloader</span></h1><br/>
        <p><a href="#" id="go"class="button24">Авторизация</a></p>
        <p><a href="<c:url value='/load'/>" class="button24">Загрузка файлов</a></p>
        <p><a href="<c:url value='/download'/>" class="button24">Поиск и скачивание</a></p>
    </div> 
    <div id="modal_form" style="display: none; top: 45%; opacity: 0;">
			<span id="modal_close">X</span>
			<form name='loginForm'
			action="<c:url value='/login' />" method='POST'>
				<h3>Персональные данные</h3>
				<p>Пользователь<br>
					<input type="text" name="login" value="" size="10">
				</p>
				<p>Пароль<br>
					<input type="password" name="password" value="" size="10">
				</p>
				<p style="text-align: center; padding-bottom: 10px">
					<table>
						<tr>
						<th><small>
							<input type="submit" value="Войти в систему">
						</small>
					</table>
				</p>
				<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
			</form>
		</div>
		<div id="overlay"></div>
</body>
</html>