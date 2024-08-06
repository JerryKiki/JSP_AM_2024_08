<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int pageNum = (int) request.getAttribute("page");
int maxPage = (int) request.getAttribute("maxpage");
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

	<a href="../home/main">메인 페이지로</a>
	
	<h3><%=pageNum%>페이지</h3>

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
				<td><a href="delete?id=<%=articleRow.get("id")%>">삭제</a></td>
			</tr>
			<%
			}
			%>
		
		</tbody>
	</table>
	
	<br>
	
	<%
	for(int i = 1; i <= maxPage; i++) {
	%>
	
	<a href="http://localhost:8080/JSP_AM_2024_08/article/list?page=<%=i%>"><%=i%></a>

	<%
	}
	%>


</body>
</html>