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

@WebServlet("/article/write")
public class ArticleWriteServlet extends HttpServlet {

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
			
			if(LoginSession.getMemberId() == -1) {
				response.getWriter().append(String.format("<script>alert('로그인 후에 이용해주세요.'); location.replace('list');</script>"));
			} else {
			
				String title = request.getParameter("title");
				String body = request.getParameter("body");
			
				if(title == null || body == null) {
					request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
				} else {
			
					SecSql sql = new SecSql();
					sql.append("INSERT INTO article");
					sql.append("SET regDate = NOW(),");
					sql.append("updateDate = NOW(),");
					sql.append("title = ?,", title);
					sql.append("`body` = ?,", body);
					sql.append("author = ?;", 1);

					int insertedId = DBUtil.insert(conn, sql);
			
					response.getWriter().append(String.format("<script>alert('%d번 글이 생성되었습니다'); location.replace('list');</script>", insertedId));
			
				}
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
