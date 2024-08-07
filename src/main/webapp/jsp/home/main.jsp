<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
    
<% Map<String, Object> loginedMember = (Map<String, Object>) request.getAttribute("nowMember");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
</head>
<body>
	<div style="font-size: 4rem; font-weight: bold; margin: 10px; margin-bottom:20px; color: #FF4E88">MAIN</div>
	<div class="menus">
	
	<%if(loginedMember == null) {%>
		<div><a href="http://localhost:8080/JSP_AM_2024_08/member/login">로그인</a></div>
		<div><a href="http://localhost:8080/JSP_AM_2024_08/member/join">회원가입</a></div>
	<%} else if(loginedMember != null){ %>
		<div>로그인 정보</div>
		<div class="login-info">아이디=<%=loginedMember.get("loginId")%> <br> 닉네임=<%=loginedMember.get("nickName")%></div>
		<br>
		<div><a href="http://localhost:8080/JSP_AM_2024_08/member/logout">로그아웃</a></div>
	<%} %>
	
		<br>
	
		<div><a href="http://localhost:8080/JSP_AM_2024_08/article/list">게시글 목록 보기</a></div>
		<div><a href="http://localhost:8080/JSP_AM_2024_08/article/write">게시글 작성</a></div>		
	</div>
	
	<style type="text/css">
	
	@font-face {
    	font-family: 'MaplestoryOTFBold';
    	src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFBold.woff') format('woff');
    	font-weight: normal;
    	font-style: normal;
	}
	
	html {
		margin: 0;
		padding: 0;
		background-color: white;
	
	}
	
	body {
		font-family: 'MaplestoryOTFBold';
		margin: 0;
		padding: 20px;
		width: 60vw;
		height: 100vh;
		margin-left: auto;
		margin-right: auto;
		text-align: center;
		display: flex;
		flex-direction: column;
		align-items: center;
		background-color: #FFDA76;
		color: #FF8C9E;
		box-sizing: border-box;
	}
	
	.menus {
		font-size: 2rem;
	}
	
	.menus > div {
		text-align: center;
	}
	
	.menus > .login-info {
		font-size: 1.5rem;
		color: #36BA98;
	}
	
	a {
		color: inherit;
		text-decoration: none;
	}
	
	.menus a:hover {
		color: #FF4E88;
	}
	
	</style>
</body>
</html>

