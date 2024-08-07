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

@WebServlet("/member/logout")
public class MemberLogoutServlet extends HttpServlet {

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
				response.getWriter().append(String.format("<script>alert('이미 로그아웃 상태입니다.'); location.replace('http://localhost:8080/JSP_AM_2024_08/home/main');</script>"));
			} else {
			
				Map<String, Object> loginedMember = LoginSession.getMember();
			
				String nowNickName = (String) loginedMember.get("nickName");
			
				response.getWriter().append(String.format("<script>alert('%s님 로그아웃'); location.replace('http://localhost:8080/JSP_AM_2024_08/home/main');</script>", nowNickName));
			
				LoginSession.logout();
				/* HttpSession 사용하기
				session.removeAttribute("loginedMemberId", memberCheck.get("id"));
				session.removeAttribute("loginedMemberLoginId", memberCheck.get("loginId"));
				session.removeAttribute("loginedMemberNickName", memberCheck.get("nickName"));
				==> 이렇게 하면 세션에 로그인한 멤버를 남길 수 있다
				==> 전역으로 저장되므로 활용하기 편하다
				 */
				
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


}
