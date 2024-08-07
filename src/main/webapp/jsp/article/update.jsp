<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>

	<h2><%=articleRow.get("id")%>번 게시물 수정</h2>

	<form action="http://localhost:8080/JSP_AM_2024_08/article/update?id=<%=articleRow.get("id")%>" method="post">
<!-- 		<input type="hidden" /> -->
	
		<div>
			<div>기존 제목 : <%=articleRow.get("title")%></div>
			<label>새 제목 : </label>
			<input type="text" name="title">
		</div>
	
		<div>
			<div>기존 내용 : <%=articleRow.get("body")%></div>
			<label>새 내용 : </label>
			<input type="text" name="body">
		</div>
		
		<br>
	
		<div>
			<a style="color: green" href="list">리스트로 돌아가기</a>
		</div>
	
		<br>
	
		<div>
			<input type='submit' value='수정'>
		</div>
	</form>
</body>
</html>