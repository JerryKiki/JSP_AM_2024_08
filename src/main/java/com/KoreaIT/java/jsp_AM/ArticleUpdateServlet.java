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
import java.util.List;
import java.util.Map;


@WebServlet("/article/update")
public class ArticleUpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			response.getWriter().append("연결 성공!");
			
			//필요 변수 확인
			int id = -1;
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			String inputId = request.getParameter("id");
			
			//id 체크
			if (inputId == null) {
				response.getWriter().append(String.format("<script>alert('수정할 article의 id를 입력하세요'); location.replace('list');</script>"));
			} else {
				id = Integer.parseInt(inputId);
			}
			
			//title, body 체크
			if (title == null || body == null) { //없으면 받아옴
			
				SecSql getOldData = SecSql.from("SELECT * FROM article");
				getOldData.append("WHERE id = ?", id);
			
				Map<String, Object> articleRow = DBUtil.selectRow(conn, getOldData);
				
				if(articleRow.get("id") == null) {
					response.getWriter().append(String.format("<script>alert('%d번 글은 없습니다'); location.replace('list');</script>", id));
				} else {
					request.setAttribute("articleRow", articleRow);
					request.getRequestDispatcher("/jsp/article/update.jsp").forward(request, response);
				}
				
			} else { //있으면 업데이트			
				SecSql sql = SecSql.from("UPDATE article SET");
				sql.append("updateDate = NOW(),");
				sql.append("title = ?,", title);
				sql.append("`body` = ?", body);
				sql.append("WHERE id = ?", id);

				int affectedRow = DBUtil.update(conn, sql);
			
				if (affectedRow >= 1) {
					response.getWriter().append(String.format("<script>alert('%d번 글이 수정되었습니다'); location.replace('list');</script>", id));
				} else {
					response.getWriter().append("수정 실패");
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