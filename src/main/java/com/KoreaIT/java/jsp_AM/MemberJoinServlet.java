package com.KoreaIT.java.jsp_AM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/member/join")
public class MemberJoinServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// DB 연결
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 x");
			e.printStackTrace();
		}

		String url = "jdbc:mysql://127.0.0.1:3306/AM_JDBC_2024_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			response.getWriter().append("연결 성공!<br>");
			
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			String nickName = request.getParameter("nickName");
			
			if(loginId == null || loginPw == null || nickName == null) {
				request.getRequestDispatcher("/jsp/member/join.jsp").forward(request, response);
			} else {
				
			//중복체크부터
			SecSql confirmId = new SecSql();
			confirmId.append("SELECT * FROM member");
			confirmId.append("WHERE loginId = ?", loginId);
			
			Map<String, Object> memberCheck = DBUtil.selectRow(conn, confirmId);
			
			if(memberCheck.get("id") != null) {
				response.getWriter().append(String.format("<script>alert('중복된 아이디입니다. 다시 입력해주세요'); location.href = location.href; </script>"));
			}
				
			//중복체크 문제없으면 삽입
			SecSql sql = new SecSql();
	        sql.append("INSERT INTO member");
	        sql.append("SET regDate = NOW(),");
	        sql.append("loginId = ?,", loginId);
	        sql.append("loginPw = ?,", loginPw);
	        sql.append("nickName = ?;", nickName);

			int insertedId = DBUtil.insert(conn, sql);
			
			response.getWriter().append(String.format("<script>alert('%d번 회원 %s님 회원가입 완료'); location.replace('http://localhost:8080/JSP_AM_2024_08/home/main');</script>", insertedId, nickName));
			
			}
			
		} catch (SQLException e) {
			System.out.println("에러 1 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
