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

	<h2>게시물 목록</h2>

<!-- 	<a href="https://www.naver.com" target="_blank">네이버</a> -->
<!-- 	<!-- "http://localhost:8080/JSP_AM_2024_08/article/list" --> -->
<!-- 	<a href="http://localhost:8080/JSP_AM_2024_08/article/list" -->
<!-- 		target="_blank">리스트 새 창</a> -->
<!-- 	<a href="detail" target="_blank">디테일 새 창</a> -->

	<a href="http://localhost:8080/JSP_AM_2024_08/home/main">메인 페이지로</a>
	
	<%if(loginedMember !=  null) { %>
	
	<div>현재 로그인 멤버 : 고유번호=<%=loginedMember.get("id")%>, 아이디=<%=loginedMember.get("loginId")%>, 닉네임=<%=loginedMember.get("nickName")%></div>
	<div><a href="http://localhost:8080/JSP_AM_2024_08/member/logout">로그아웃</a></div>
	
	<%} else {%>
	
	<div><a href="http://localhost:8080/JSP_AM_2024_08/member/login">로그인</a></div>
	
	<%} %>
	
	<h3>총 게시글 수 : <%=totalCount%></h3>
	
	<h4><%=pageNum%>페이지</h4>

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
	
	<table style="border-collapse: collapse; border-color: green;" border="1px">
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
		<a style="color: green; text-decoration: none" href="list?page=<%=pageNum-1%>">이전 페이지</a>	
	</div>
	
	<br>
	
	<%}%>
	
	<div class="page">
	<%
	for(int i = 1; i <= maxPage; i++) {
	%>
	
	<%
	
	String displayPgN = Integer.toString(i);
	
	if(i<10) {
	
		displayPgN = "0" + displayPgN;
	
	} %>
	
	<a class="<%=pageNum == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=displayPgN%></a>
	
	<%
	if(i%10==0) {
	%>
	<br>
	<%}%>

	<%
	}
	%>
	</div>
	
	<br>
	
	<%if (pageNum != maxPage) { %>
	
	<div>
		<a style="color: green; text-decoration: none" href="list?page=<%=pageNum+1%>">다음 페이지</a>	
	</div>
	
	<%} %>
	
	<br>
	
	<%if(loginedMember !=  null) { %>
	
	<div><a style="color: white; background-color: black; text-decoration: none" href="write">글 작성</a></div>
	
	<%} %>
	
	<!-- CSS -->
	
	
	<style type="text/css">
	
	.page > a {
		color: black;
		text-decoration: none;
	}
	
	.page > a.cPage {
		color: red;
		text-decoration: underline;	
	}
	
	td, th {
		padding: 5px;
	}
	
	</style>


</body>
</html>