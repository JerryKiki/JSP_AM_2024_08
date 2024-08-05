package com.KoreaIT.java.jsp_AM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//오류와 결과를 chrome(내가 설정한 기본웹)에서도 확인 가능해진다
//주소에서 http://localhost:8081/JSP_AM_2024_08(이 자리)
//(이 자리)에 WebServlet 내의 주소텍스트가 들어간다면 이 스크립트가 동작하는 것
//여태 쓴 것은 서브렛! ==> response 가지고 응답한다
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
		
		//null이 int에 못들어가기 때문에 파라미터값을 유저가 입력하지 않았을 때를 대비해 널체크를 해서 널일 시 기본값 부여
		if (inputDan == null) {
			inputDan = "1";
		}
		
		if (inputLimit == null) {
			inputLimit = "1";
		}
		
		
		int dan = Integer.parseInt(inputDan);
		int limit = Integer.parseInt(inputLimit);
		
		//웹에서 킬 거니까 html태그를 활용 가능하다! ==> CSS, JS도 활용가능하다
		//아예 줄바꿈 자체를 br 사용하는 대신 div로 감쌈으로서 해결하는 것도 가능하다
		//아래처럼 코드 안에 써주는 걸 inline CSS라고 함
		response.getWriter().append(String.format("<div style=\"color: %s\">", inputColor));
		response.getWriter().append(String.format("==%d단 출력, * %d까지, 글자 색상은 %s==<br>", dan, limit, inputColor));
		//response.getWriter().append(String.format("<div style='color: %s;'>==%d단 출력, * %d까지, 글자 색상은 %s==</div>", inputColor, dan, limit));
	
		
		for (int i = 1; i<=limit; i++) {
			response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, dan * i));
			//response.getWriter().append(String.format("<div style=\"color: %s\">%d * %d = %d</div>", inputColor, dan, i, dan * i));
		}
		
		response.getWriter().append("</div>");
	}

}
