<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

	<h2>로그인</h2>
	
	<script type="text/javascript">
	
		function LoginForm__submit(form) {
			console.log("form.loginId.value : " + form.loginId.value);
			console.log("form.loginPw.value : " + form.loginPw.value);
			
			let loginId = form.loginId.value.trim();
			let loginPw = form.loginPw.value.trim();

			if(loginId.length == 0) {
				alert('아이디를 입력해주세요');
				return;
			}
			if(loginPw.length == 0) {
				alert('비밀번호를 입력해주세요');
				return;
			}
			
			//다 확인했으면 그냥 여기서 submit
			form.submit();
		}
	
	</script>

	<form onsubmit="LoginForm__submit(this); return false;" action="http://localhost:8080/JSP_AM_2024_08/member/login" method="post">
		<div>
			<label>아이디 : </label>
			<input type="text" name="loginId">
		</div>
	
		<div>
			<label>패스워드 : </label>
			<input autocomplete="off" type="text" name="loginPw">
		</div>
		
		<br>
	
		<div>
			<a style="color: green" href="http://localhost:8080/JSP_AM_2024_08/home/main">메인페이지로 돌아가기</a>
		</div>
	
		<br>
	
		<div>
			<input type='submit' value='로그인'>
		</div>
	</form>
</body>
</html>