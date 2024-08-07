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
	
	<script type="text/javascript">
	
		function JoinForm__submit(form) {
			//console.log(form.loginId.value) 등으로 콘솔 한번씩 확인해보자
			//로깅 주도 개발 한번 공부해보기 ==> '로그 잘 찍자!!'
			//form(서브밋 하려고 한 폼)'의' loginId(name으로 구분)'의' value(입력된 값)'의' '트림된'(공백 제거된) 길이가 0이면(아무것도 입력X)
			console.log("form.loginId.value : " + form.loginId.value);
			console.log("form.loginPw.value : " + form.loginPw.value);
			console.log("form.loginPwConfirm.value : " + form.loginPwConfirm.value);
			console.log("form.nickName.value : " + form.nickName.value);
			
			//form.loginId.value = form.loginId.value.trim(); 같은 문법도 가능
			let loginId = form.loginId.value.trim();
			let loginPw = form.loginPw.value.trim();
			let loginPwConfirm = form.loginPwConfirm.value.trim();
			let nickName = form.nickName.value.trim();
			
			if(loginId.length == 0) {
				alert('아이디를 입력해주세요');
				return;
			}
			if(loginPw.length == 0) {
				alert('비밀번호를 입력해주세요');
				return;
			}
			if(loginPwConfirm.length == 0) {
				alert('비밀번호 확인을 입력해주세요');
				return;
			}
			
			if(loginPw != loginPwConfirm) {
				alert('비밀번호가 일치하지 않습니다.');
				form.loginPw.focus();
				return;
			}
			
			if(nickName.length == 0) {
				alert('닉네임을 입력해주세요');
				return;
			}
			
			//다 확인했으면 그냥 여기서 submit
			form.submit();
		}
	
	</script>
	
	<!-- js가 우선이기 때문에 onsubmit(제출될때) 안에 함수를 쓰면 이게 먼저 실행된다 ==> 함수 먼저 실행 + return false 활용해 공백 입력 등을 막아보자 -->
	<form onsubmit="JoinForm__submit(this); return false;" action="http://localhost:8080/JSP_AM_2024_08/member/join" method="post">
		<div>
			<label>아이디 : </label>
			<input autocomplete="off" type="text" name="loginId">
		</div>
	
		<div>
			<label>패스워드 : </label>
			<input autocomplete="off" type="text" name="loginPw">
		</div>
		
		<div>
			<label>패스워드 확인 : </label>
			<input autocomplete="off" type="text" name="loginPwConfirm">
		</div>
		
		<div>
			<label>닉네임 : </label>
			<input autocomplete="off" type="text" name="nickName">
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