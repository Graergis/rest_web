<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta charset="utf-8">
<style>
th {
	cursor: pointer;
}

th:hover {
	background: yellow;
}
</style>
<title>File Download</title>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<script src="static/js/jquery-3.0.0.min.js" type="text/javascript"></script>
<script src="static/js/popup.js" type="text/javascript"></script>
<script type="text/javascript">
			function openFileDialog(id) {
				document.getElementById(id).click();
			}
</script>
</head>
<body onload="digitalWatch()">
	<div >
		<sec:authorize access="isAuthenticated()">
			<nav>
				<a class="dropdown-toggle" href="#" title="Menu"><sec:authentication property="principal.username" /></a>
				<ul class="dropdown">
				  <li style="width:50px"><a href="<c:url value='/logout'/> ">Выйти</a></li>
				</ul>
			</nav>
		</sec:authorize>
	</div>	
	<h1 align="center">Welcome to FileDownloader Example</h1>
	<sec:authorize access="!isAuthenticated()">
		<p><a href="#" id="go2" class="button24">Войти</a></p>
	</sec:authorize>
	<div class="form-container">
		<sec:authorize access="hasRole('USER')">
			<p>
				<a href="#" id="go" class="button24">Создать документ</a>
			</p>
			<p>
				<a href="#" id="go3" class="button24">Поиск</a>
			</p>
		</sec:authorize>
		<div style="width: 2000px; height: 600px; overflow: auto;">
			<table border="1" cellspacing="0" cellpadding="15" width="60%"
				height="130" id="grid">
				<thead>
					<tr>
						<th data-type="name">Name</th>
						<th data-type="date">Date</th>
						<th data-type="author">Author</th>
						<th data-type="comment">Comment</th>
						<th data-type="files">Files</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${lists}">
						<tr>
							<td>${item.name}</td>
							<td>${item.date}</td>
							<td>${item.author}</td>
							<td>${item.comment}</td>
							<td>
								<ul>
									<c:forEach var="file" items="${item.files}">
										<li><sec:authorize access="isAuthenticated()">
												<a href="<c:url value='/download/${file.id}'/>">${file.name}</a>
											</sec:authorize> <sec:authorize access="!isAuthenticated()">
												${file.name}
											</sec:authorize></li>
									</c:forEach>
								</ul>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<script>
			    var grid = document.getElementById('grid');
			
			    grid.onclick = function(e) {
			      if (e.target.tagName != 'TH') return;
			      sortGrid(e.target.cellIndex, e.target.getAttribute('data-type'));
			    };
			
			    function sortGrid(colNum, type) {
			      var tbody = grid.getElementsByTagName('tbody')[0];
			      var rowsArray = [].slice.call(tbody.rows);
			      var compare;
			
			      switch (type) {
			      case 'id':
			        compare = function(rowA, rowB) {
			          return rowA.cells[colNum].innerHTML - rowB.cells[colNum].innerHTML;
			        };
			        break;
			      case 'name':
			        compare = function(rowA, rowB) {
			          return rowA.cells[colNum].innerHTML > rowB.cells[colNum].innerHTML ? 1 : -1;
			        };
			        break;
			      case 'date':
			          compare = function(rowA, rowB) {
			            return rowA.cells[colNum].innerHTML > rowB.cells[colNum].innerHTML ? 1 : -1;
			          };
			          break;
			      case 'author':
			          compare = function(rowA, rowB) {
			            return rowA.cells[colNum].innerHTML > rowB.cells[colNum].innerHTML ? 1 : -1;
			          };
			          break;
			      case 'comment':
			          compare = function(rowA, rowB) {
			            return rowA.cells[colNum].innerHTML > rowB.cells[colNum].innerHTML ? 1 : -1;
			          };
			          break;
			      }
			      rowsArray.sort(compare);
			      grid.removeChild(tbody);
			      for (var i = 0; i < rowsArray.length; i++) {
			        tbody.appendChild(rowsArray[i]);
			      }
			      grid.appendChild(tbody);
			
			    }
  			</script>
		</div>
	</div>
	<div id="modal_form" style="display: none; top: 45%; opacity: 0;">
		<span id="modal_close">X</span>
		<form enctype="multipart/form-data"
			action="<c:url value = '/' />" method="post"
			id="othdetphotoform">
			<h3>Данные загрузки</h3>
			<p>
				Название документа<br> <input type="text" name="name" value=""
					size="15" required>
			</p>
			<p>
				<input type="file" name="file" required />
			</p>
			<p>
				<input type="file" name="file" />
			</p>
			<p>
				<input type="file" name="file" />
			</p>
			<p>
				Комментарий<br> <input type="text" name="comment" value=""
					size="40">
			</p>
			<p style="text-align: center; padding-bottom: 10px">
			<table>
				<tr>
					<th><small> <input type="submit" value="Загрузить">
					</small>
			</table>
			</p>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
	<div id="modal_form2" style="display: none; opacity: 0;">
		<span id="modal_close2">X</span>
		<form action="<c:url value='/login' />" method='post'>
			<h3>Песрональные данные</h3>
			<p>
				Пользователь<br>
			<p>
				<input type="text" name="username" value="" size="10">
			</p>
			<p>
				Пароль<br> <input type="password" name="password" value=""
					size="10">
			</p>
			<p style="text-align: center; padding-bottom: 10px">
				<input type="submit" value="Войти" />
			</p>
			<input type="hidden" name="Submit" value="login" /> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
	<div id="modal_form3" style="display: none; opacity: 0;">
		<span id="modal_close3">X</span>
		<form action="<c:url value='/search' />" method='get'>
			<h3>Данные поиска</h3>
			Искать..
			<p>
				документ<br> <input type="text" name="name" value=""
					size="10">
			</p>
			<p>
				автор<br> <input type="text" name="author" value=""
					size="10">
			</p>
			<p>
				комментарий<br> <input type="text" name="comment" value=""
					size="10">
			</p>
			<p style="text-align: center; padding-bottom: 10px">
				<input type="submit" value="Поиск" />
			</p>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
	<div id="overlay"></div>
</body>
</html>