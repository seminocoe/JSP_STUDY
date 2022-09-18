<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.evaluation.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="evaluation" class="com.evaluation.EvaluationVO" scope="page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	 <%
	 
	 	String userID = null;
	 	if(session.getAttribute("loginID") != null){
	 		userID = (String) session.getAttribute("loginID");
	 	}
	 	if(userID == null){
	 		PrintWriter script = response.getWriter();
	 		script.println("<script>");
			script.println("alert('로그인을 해주세요.')");
	 		script.println("location.href = '../memberone/login.jsp'");
	 		script.println("</script>");
	 	} 
	 	else{
		 	int num = 0; 
		 	if (request.getParameter("num") != null){
		 		num = Integer.parseInt(request.getParameter("num"));
		 	}
		 	if (num == 0){
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("alert('유효하지 않은 글입니다.')");
		 		script.println("location.href = '../memberone/login.jsp'");
		 		script.println("</script>");
		 	}
		 	int gechu = 0;
			int bichu = 0;
			
			if (request.getParameter("gechu") != null){
				gechu = Integer.parseInt(request.getParameter("gechu"));
			}
			if (request.getParameter("bichu") != null){
				bichu = Integer.parseInt(request.getParameter("bichu"));
			}
			
			EvaluationDAO evaluationDAO = new EvaluationDAO();
			ArrayList<EvaluationVO> list = evaluationDAO.whereList(num, userID);
			int result = 0;
			if (list.isEmpty()){
				result = evaluationDAO.write(num, userID, gechu, bichu);
			}
			else if (gechu == list.get(0).getGechu() && bichu == list.get(0).getBichu()){
				result = evaluationDAO.delete(num, userID);
			}
			else{
				result = evaluationDAO.update(num, userID, gechu, bichu);
			}
			
	 		if (result == -1){
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("alert('평가를 실패했습니다.')");
		 		script.println("history.back()");
		 		script.println("</script>");
		 	}
	 		else{
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("location.href=document.referrer;");
		 		script.println("</script>");
		 	}
	 	}
	 %>
</body>
</html>