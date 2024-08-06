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
<title>게시물 작성</title>
</head>
<body>

	<h2>게시물 작성</h2>

	<div>
		<label>제목 : </label>
		<input type="text">
	</div>
	
	<div>
		<label>내용 : </label>
		<input type="text">
	</div>
		
	<br>
	
	<div>
		<a style="color: green" href="list">리스트로 돌아가기</a>
	</div>
	
	<br>
	
	<div>
		<input type='submit' value='작성'>
<%-- 		<a style="color: red" href="http://localhost:8080/JSP_AM_2024_08/article/delete?id=<%=articleRow.get("id")%>">삭제</a> --%>
	</div>

</body>
</html>