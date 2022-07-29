package com.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ContextSet")
public class ContextSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "홍길동";
		int age = 20;
		
		//속성값을 setAttribute로 저장함
		//저장된 속성값은 웹 어플리케이션이 제거(서버가 꺼지기전이라는 의미)되기 전까지 계속 사용할 수 있다.(전역)
		getServletContext().setAttribute("name", name);
		getServletContext().setAttribute("age", age);
	}

}
