<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% //이렇게 하면 이제 이 안쪽은 자바의 영역이 된다
//이 문법 연습하기!!!
int dan = Integer.parseInt(request.getParameter("dan"));
int limit = Integer.parseInt(request.getParameter("limit"));
String color = request.getParameter("color");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 출력</title>
</head>
<body>
	<h1>
	<div style="color:<%=color%>">==<%=dan%>단==</div>
	</h1>
	<%for(int i = 0; i <= limit; i++) {%>
	<div style="color:<%=color%>">
		<%=dan%> * <%=i %> = <%=dan * i%></div>
	<%}%>
</body>
</html>