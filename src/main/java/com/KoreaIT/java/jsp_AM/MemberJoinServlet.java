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
			
			if(LoginSession.getMemberId() != -1) {
				response.getWriter().append(String.format("<script>alert('로그인 중에는 이용할 수 없습니다.'); location.replace('http://localhost:8080/JSP_AM_2024_08/home/main'); </script>"));
				return;
			}
			
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			//String loginPwConfirm = request.getParameter("loginPwConfirm");
			String nickName = request.getParameter("nickName");
			
			if(loginId == null || loginPw == null || nickName == null) {
				request.getRequestDispatcher("/jsp/member/join.jsp").forward(request, response);
			} else {
				// 주소 칠 때 ../ << 경로 하나 빠져 나가서 가라는 뜻
				
				//중복체크부터
				SecSql confirmId = new SecSql();
				confirmId.append("SELECT * FROM member");
				confirmId.append("WHERE loginId = ?", loginId);
			
				Map<String, Object> memberCheck = DBUtil.selectRow(conn, confirmId);
				
				//이거 이전에 했던 거 : join.jsp의 js에서 처리가 끝났기 때문에 확인 안해도 됨
				//else if(!loginPw.equals(loginPwConfirm)) {
				//response.getWriter().append(String.format("<script>alert('비밀번호 확인이 틀립니다. 다시 입력해주세요'); location.href = location.href; </script>"));
			
				if(memberCheck.get("id") != null) {
					response.getWriter().append(String.format("<script>alert('중복된 아이디입니다. 다시 입력해주세요'); location.href = location.href; </script>"));
				} else {				
					//중복체크 및 비번확인 문제없으면 삽입
					SecSql sql = new SecSql();
					sql.append("INSERT INTO `member`");
					sql.append("SET regDate = NOW(),");
					sql.append("loginId = ?,", loginId);
					sql.append("loginPw = ?,", loginPw);
					sql.append("nickName = ?;", nickName);

					int insertedId = DBUtil.insert(conn, sql);
			
					response.getWriter().append(String.format("<script>alert('%d번 회원 %s님 회원가입 완료'); location.replace('http://localhost:8080/JSP_AM_2024_08/home/main');</script>", insertedId, nickName));
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
