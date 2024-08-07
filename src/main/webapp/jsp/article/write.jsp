<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>

	<h2>게시물 작성</h2>

	<form action="http://localhost:8080/JSP_AM_2024_08/article/write" method="post">
		<div>
			<label>제목 : </label>
			<input type="text" name="title">
		</div>
	
		<div>
			<label>내용 : </label>
			<input type="text" name="body">
		</div>
		
		<br>
	
		<div>
			<a style="color: green" href="list">리스트로 돌아가기</a>
		</div>
	
		<br>
	
		<div>
			<input type='submit' value='작성'>
		</div>
	</form>
</body>
</html>