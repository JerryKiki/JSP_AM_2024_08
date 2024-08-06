<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- 아까워서 남겨둔 더미... -->

<%int id = (int) request.getAttribute("id");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>

	<h2><%=id%>번 게시글이 삭제되었습니다.</h2>
	
	<div>
		<a style="color: green" href="list">리스트로 돌아가기</a>
	</div>


</body>
</html>