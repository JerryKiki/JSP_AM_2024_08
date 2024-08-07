<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int pageNum = (int) request.getAttribute("page");
int maxPage = (int) request.getAttribute("maxpage");
int totalCount = (int) request.getAttribute("totalCount");
Map<String, Object> loginedMember = (Map<String, Object>) request.getAttribute("loginedMember");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>

	<div style="font-size: 4rem; font-weight: bold; margin: 10px; margin-bottom:20px; color: #FF4E88">게시물 목록</div>

<!-- 	<a href="https://www.naver.com" target="_blank">네이버</a> -->
<!-- 	<!-- "http://localhost:8080/JSP_AM_2024_08/article/list" -->
<!-- 	<a href="http://localhost:8080/JSP_AM_2024_08/article/list" -->
<!-- 		target="_blank">리스트 새 창</a> -->
<!-- 	<a href="detail" target="_blank">디테일 새 창</a> -->

	<div style="font-size: 1.4rem" class="menus">

		<div><a href="http://localhost:8080/JSP_AM_2024_08/home/main">메인 페이지로</a></div>
		
		<br>
	
		<%if(loginedMember !=  null) { %>

		<div>로그인 정보</div>
		<div class="login-info">아이디=<%=loginedMember.get("loginId")%> <br> 닉네임=<%=loginedMember.get("nickName")%></div>
		<div><a href="http://localhost:8080/JSP_AM_2024_08/member/logout">로그아웃</a></div>
	
		<%} else {%>
	
		<div><a href="http://localhost:8080/JSP_AM_2024_08/member/login">로그인</a></div>
	
		<%} %>
	</div>
	
	<br>
	
	<div style="font-size: 2rem; font-weight: bold; margin: 10px; margin-bottom:20px; color: #FF4E88">총 게시글 수 : <%=totalCount%></div>
	
	<div style="font-size: 1.4rem; font-weight: bold; margin: 10px; margin-bottom:20px; color: #FF4E88"><%=pageNum%>페이지</div>

<!-- 	<ul> -->
<%-- 		<% --%>
<!-- // 		for (Map<String, Object> articleRow : articleRows) {
<%-- 		%> --%>
<%-- 		<li><a href="detail?id=<%=articleRow.get("id")%>"> --%>
<%-- 			[<%=articleRow.get("id")%>번] 생성일시 : <%=articleRow.get("regDate")%> 제목 : <%=articleRow.get("title")%> 내용 : <%=articleRow.get("body")%> --%>
<!-- 			</a></li> -->
<%-- 		<% --%>
<!--  // 		}
<%-- 		%> --%>
<!-- 	</ul> -->
	
	<table style="border-collapse: collapse; border-color: #36BA98;" border="1px">
		<thead>
			<tr style="text-align: center;">
				<th>번호</th>
				<th>날짜</th>
				<th>제목</th>
				<th>내용</th>
				<th>수정</th>
				<th>삭제</th>	
			</tr>		
		</thead>
		<tbody>
			<%
			for (Map<String, Object> articleRow : articleRows) {
			%>
			<tr style="text-align: center;">
				<td><%=articleRow.get("id")%>번</td>
				<td><%=articleRow.get("regDate")%></td>
				<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></td>
				<td><%=articleRow.get("body")%></td>
				<td><a href="update?id=<%=articleRow.get("id")%>">수정</a></td>
				<td><a href="delete?id=<%=articleRow.get("id")%>">삭제</a></td>
			</tr>
			<%
			}
			%>		
		</tbody>
	</table>
	
	<br>
	
	<%if(pageNum!=1) {%>
	
	<div>
		<a style="color: #36BA98; text-decoration: none" href="list?page=<%=pageNum-1%>">이전 페이지</a>	
	</div>
	
	<br>
	
	<%}%>
	
	<div class="page">

	<%
	int startNum = 1;
	int endNum = startNum + 9;
	
	if(pageNum > 4) {
		startNum = pageNum - 4;
		if(startNum + 9 <= maxPage) {
			endNum = startNum + 9;
		} else {
			endNum = maxPage;
		}
	}
	
	
	for(int i = startNum; i <= endNum; i++) {
		String displayPgN = Integer.toString(i);
	
		if(i<10) {
			displayPgN = "0" + displayPgN;	
		}
	%>
	
	<a class="<%=pageNum == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=displayPgN%></a>

	<%
	}
	%>
	</div>
	
	<br>
	
	<%if (pageNum != maxPage) { %>
	
	<div>
		<a style="color: #36BA98; text-decoration: none" href="list?page=<%=pageNum+1%>">다음 페이지</a>	
	</div>
	
	<%} %>
	
	<br>
	
	<%if(loginedMember !=  null) { %>
	
	<div><a style="color: #FF4E88; background-color: #36BA98; text-decoration: none" href="write">글 작성</a></div>
	
	<%} %>
	
	
	
	<!-- CSS -->
	
	<style type="text/css">
	
	@font-face {
    	font-family: 'MaplestoryOTFBold';
    	src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFBold.woff') format('woff');
    	font-weight: normal;
    	font-style: normal;
	}
	
	@font-face {
    	font-family: 'Pretendard-Regular';
    	src: url('https://fastly.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    	font-weight: 400;
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
	
	a {
		color: black;
		text-decoration: none;
	}
	
	a:hover {
		color: red;
	}
	
	.page > a.cPage {
		color: red;
		text-decoration: underline;	
	}
	
	td, th {
		font-family: 'Pretendard-Regular';
		color: black;
		padding: 5px;
	}
	
	.menus > .login-info {
		color: #36BA98;
	}
	
	</style>


</body>
</html>