package com.KoreaIT.java.jsp_AM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/home/main")
public class HomeMainServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Object> nowMember = LoginSession.getMember();
		/* HttpSession으로 넘겨주기
		HttpSession session = request.getSession();
		boolean isLogined = false;
		if(session.getAttribute("loginedMemberId") != null {
			isLogined = true;
		}
		request.setAttribute("isLogined", isLogined);
		 */
		
		request.setAttribute("nowMember", nowMember);
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}
}
