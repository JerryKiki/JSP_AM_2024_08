package com.KoreaIT.java.jsp_AM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//오류와 결과를 chrome(내가 설정한 기본웹)에서도 확인 가능해진다
@WebServlet("/printDan")
public class printDan extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		// 주소 뒤에 printDan?dan=8이면 8이 파라미터 (인자값)
		// 브라우저에 주소를 치는 행위는 요청을 보내는 행위
		// 파라미터 여러개일 땐 요청보낼 때 &로 엮어준다
		String inputDan = request.getParameter("dan");
		String inputLimit = request.getParameter("limit");
		String inputColor = request.getParameter("color");
		
		//null이 int에 못들어가기 때문에 파라미터값을 유저가 입력하지 않았을 때를 대비해 널체크를 함
		if (inputDan == null) {
			inputDan = "1";
		}
		
		if (inputLimit == null) {
			inputLimit = "1";
		}
		
		
		int dan = Integer.parseInt(inputDan);
		int limit = Integer.parseInt(inputLimit);
		
		//웹에서 킬 거니까 html태그를 활용 가능하다! ==> CSS, JS도 활용가능하다
		response.getWriter().append(String.format("<div style=\"color: %s\">", inputColor));
		
		response.getWriter().append(String.format("==%d단 출력 * %d까지, 글자 색상은 %s==<br>", dan, limit, inputColor));
	
		
		for (int i = 1; i<=limit; i++) {
			response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, dan * i));
		}
		
		response.getWriter().append("</div>");
	}

}
