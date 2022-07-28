package com.bbs;

import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/VisitInsert")
public class VisitInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
		
	
	//이렇게 따로 만들면 어디서든 호출하게 가능 (위에 처럼) 작성은 여기다만 하면 됌
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 처리(한글 안깨지게)
		request.setCharacterEncoding("utf-8");
		
		//클라이언트가 http요청으로 전송한 파라미터 값을 읽어옴
		String writer = request.getParameter("writer");
		String memo = request.getParameter("memo");
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into visit(no, writer, memo, regdate) ");
		sql.append("values(visit_seq.nextval, ?, ?, sysdate)");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, writer);
			pstmt.setString(2, memo);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {if (pstmt != null) pstmt.close(); }catch(SQLException ee) {}
			try {if (con != null) con.close(); }catch(SQLException ee) {}
		}
		
		response.sendRedirect("VisitList");
		
		
	}


}
