package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login/Sport")
public class SportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String[] sports = request.getParameterValues("sports");
		String gender = request.getParameter("gender");
		
//		for(String sport : sports) {
//			System.out.print("취미 :"+sport+",");
//		}
//		System.out.println();
//		System.out.println("성별 : "+gender);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		for(String sport : sports) {
			out.println("취미 :"+sport+"<br>");
		}
		out.println("성별 : "+gender+"<br>");
		out.println("</body>");
		out.println("</html>");
	}

}
