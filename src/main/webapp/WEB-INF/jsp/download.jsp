<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>File Download</title>
    <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <script type="text/javascript">
		function openFileDialog(id) {
			document.getElementById(id).click();
		}
	</script>
</head>
<body>
    <div class="form-container">
        <h1>Welcome to FileDownloader Example</h1>
        <p><a href="<c:url value='/download/internal' />" class="button24">Скачать файл (внутренний)</a></p>
        <p><a href="<c:url value='/download/external' />" class="button24">Скачать файл (внешний)</a></p>
        <p><a href="<c:url value='/' />" class="button24">На главную</a></p>
    </div> 
    <div style=" width:2000px; height:350px; overflow: auto;">
			<ul>
				<c:forEach var="listValue" items="${lists}">
					<H1><span class="uniform-bg">${listValue}</span></H1>
				</c:forEach>
			</ul>
		</div>
</body>
</html>