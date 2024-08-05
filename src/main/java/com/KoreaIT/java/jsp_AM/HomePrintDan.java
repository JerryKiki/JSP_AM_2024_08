package com.KoreaIT.java.jsp_AM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home/printDan")
public class HomePrintDan extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//요청은 이 서브렛이 받아서... jsp에 전달
		//응답을 바로 하지 않고 다시 리퀘스트 하기!
		//아래처럼 활용하면 괄호 안쪽의 .jsp 파일을 사용할 수 있다
		//.jsp파일은 .java파일과 별개다... 안쪽이 html느낌으로 이루어져있음
		//이 jsp파일을 가져와라...!! (간접적으로 연다)
		request.getRequestDispatcher("/jsp/home/printDan.jsp").forward(request, response);
		//나중에 / 등 개선법... 상대경로 절대경로 / jsp 상대경로 절대경로
	}

}
