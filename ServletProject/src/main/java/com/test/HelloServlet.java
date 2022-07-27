package com.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name ="MyServletDESU", urlPatterns = {"/aa","/bb"})
// : 어노테이션을 이용한 방법  : 맵핑명
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//시작할때 한 번만
	@Override
	public void init() throws ServletException {
		System.out.println("init 요청");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("HelloServlet 요청");
		
	}

	//얜 종료할때 나옴 근데 보기가 어려움
	@Override
	public void destroy() {
		System.out.println("destroy 요청");
	}
}
