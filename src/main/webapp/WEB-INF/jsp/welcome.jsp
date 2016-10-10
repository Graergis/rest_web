<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="form-container">
        <h1>Welcome to FileDownloader Example</h1>
        <p><a href="#" id="go"class="button24">Загрузить файл</a></p>
        <div style=" width:2000px; height:350px; overflow: auto;">
			<table border="1" cellspacing="0" cellpadding="15" width="60%" height="130" id="grid">
				<thead>
					<tr>
						<th data-type="id">Id</th>
						<th data-type="name">Name Document</th>
						<th data-type="date">Date</th>
						<th data-type="author">Author</th>
						<th data-type="comment">Comment</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${lists}">
						<tr>
							<td><a href="<c:url value='/download/${item.id}'/>">${item.id}</a></td><td>${item.name}</td><td>${item.date}</td><td>${item.author}</td><td>${item.comment}</td>
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
			<form enctype="multipart/form-data" action="<c:url value = '/' />" method="post" id="othdetphotoform">
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