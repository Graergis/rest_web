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
	</script>
</head>
<body onload="digitalWatch()">
    <div class="form-container">
        <h1>Welcome to FileDownloader Example</h1>
        <p><a href="#" id="go"class="button24">Загрузить файл</a></p>
        <p><a href="<c:url value='/download'/>" class="button24">Поиск и скачивание</a></p>
    </div> 
    <div id="modal_form" style="display: none; top: 45%; opacity: 0;">
			<span id="modal_close">X</span>
			<form enctype="multipart/form-data" action="<c:url value = '/loadFile' />" method="post" id="othdetphotoform" target="hiddenframe">
				<h3>Данные загрузки</h3>
					<p>Название документа<br>
						<input type="text" name="name" value="" size="15">
					</p>
					<p>Автор<br>
						<input type="text" name="author" value="" size="10">
					</p>
					<p><input type="file" id="file1" name="file1"/></p>
					<p><input type="file" id="file2" name="file2"/></p>
					<p><input type="file" id="file3" name="file3"/></p>
					<p>Комментарий<br>
						<input type="text" name="comment" value="" size="40">
					</p>
				<p style="text-align: center; padding-bottom: 10px">
					<table>
						<tr>
						<th><small>
							<input type="submit" value="Загрузить">
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