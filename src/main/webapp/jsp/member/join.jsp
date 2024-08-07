<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

	<h2>회원가입</h2>

	<form action="http://localhost:8080/JSP_AM_2024_08/member/join" method="post">
		<div>
			<label>아이디 : </label>
			<input type="text" name="loginId">
		</div>
	
		<div>
			<label>패스워드 : </label>
			<input type="text" name="loginPw">
		</div>
		
		<div>
			<label>닉네임 : </label>
			<input type="text" name="nickName">
		</div>
		
		<br>
	
		<div>
			<a style="color: green" href="http://localhost:8080/JSP_AM_2024_08/home/main">메인페이지로 돌아가기</a>
		</div>
	
		<br>
	
		<div>
			<input type='submit' value='가입'>
		</div>
	</form>
</body>
</html>