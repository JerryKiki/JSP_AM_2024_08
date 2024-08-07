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


@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {

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
			
			if(LoginSession.getMemberId() != -1) {
				response.getWriter().append(String.format("<script>alert('이미 로그인 중입니다.'); location.replace('http://localhost:8080/JSP_AM_2024_08/home/main');</script>"));
			} else {

				String loginId = request.getParameter("loginId");
				String loginPw = request.getParameter("loginPw");
			
				if(loginId == null || loginPw == null) {
					request.getRequestDispatcher("/jsp/member/login.jsp").forward(request, response);
				} else {
					//로그인 전 체크
					SecSql checkId = SecSql.from("SELECT * FROM `member`");
					checkId.append("WHERE loginId = ?", loginId);
			
					Map<String, Object> memberCheck = DBUtil.selectRow(conn, checkId);
			
					if(memberCheck.get("id") == null) {	//id 있는지 확인
						response.getWriter().append(String.format("<script>alert('존재하지 않는 아이디입니다.'); location.href = location.href; </script>"));
					} else if(!memberCheck.get("loginPw").equals(loginPw)) {  //비밀번호 일치하는지 확인
						response.getWriter().append(String.format("<script>alert('비밀번호가 일치하지 않습니다.'); location.href = location.href; </script>"));
					} else { //전부 통과하면
						int nowId = (int) memberCheck.get("id");
						String nowNickName = (String) memberCheck.get("nickName");
				
						LoginSession.login(memberCheck, nowId);
						
						/* HttpSession 사용하기
						HttpSession session = request.getSession();
						session.setAttribute("loginedMemberId", memberCheck.get("id"));
						session.setAttribute("loginedMemberLoginId", memberCheck.get("loginId"));
						session.setAttribute("loginedMemberNickName", memberCheck.get("nickName"));
						==> 이렇게 하면 세션에 로그인한 멤버를 남길 수 있다
						==> 전역으로 저장되므로 활용하기 편하다
						 */
				
						response.getWriter().append(String.format("<script>alert('%d번 회원 %s님 환영합니다.'); location.replace('http://localhost:8080/JSP_AM_2024_08/home/main');</script>", nowId, nowNickName));			
					}
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