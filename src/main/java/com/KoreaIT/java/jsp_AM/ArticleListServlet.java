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


@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

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

		response.getWriter().append("123");

		String url = "jdbc:mysql://127.0.0.1:3306/AM_JDBC_2024_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			response.getWriter().append("연결 성공!");
		
			String inputPage = request.getParameter("page");
			int pageNum = 1;
			
			if (inputPage!=null) {
				pageNum = Integer.parseInt(inputPage);
			}
			
			//필요한 변수 선언
			int itemsInPage = 10;
			int limitFrom = (pageNum - 1) * itemsInPage;
			int limitTake = itemsInPage;
			
			//최대 페이지수 구하기
			SecSql getCounts = SecSql.from("SELECT COUNT(*) AS PC");
			getCounts.append("FROM article");
			
			int totalCount = DBUtil.selectRowIntValue(conn, getCounts);
			int maxPage = (int) Math.ceil(totalCount / (double)itemsInPage);
//			if (pageCount%itemsInPage==0) {
//				maxPage = pageCount/itemsInPage;
//			} else {
//				maxPage = pageCount/itemsInPage + 1;
//			}
			
			//실제로 표시될 페이지 보내기
			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM article");
			sql.append("ORDER BY id DESC");
			sql.append("LIMIT ?, ?", limitFrom, limitTake);
			
//			String sql = String.format("SELECT * FROM article ORDER BY id DESC LIMIT %d, %d", limitFrom, limitTake);

			List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

			request.setAttribute("articleRows", articleRows);
			request.setAttribute("page", pageNum);
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("maxpage", maxPage);
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
			

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

}
