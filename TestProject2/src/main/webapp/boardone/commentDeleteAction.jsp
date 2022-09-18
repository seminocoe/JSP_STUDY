<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.comment.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.File" %>
<% request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<%
		String userID = null;
		if (session.getAttribute("loginID") != null) {//유저아이디이름으로 세션이 존재하는 회원들은 
			userID = (String) session.getAttribute("loginID");//유저아이디에 해당 세션값을 넣어준다.
		}
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = '../memberone/login.jsp'");
			script.println("</script>");
		} 
		int num = 0;
		if (request.getParameter("num") != null){
			num = Integer.parseInt(request.getParameter("num"));
		}
		//댓글이 유효한지 판별
		int commentID = 0;
		if (request.getParameter("commentID") != null) {
			commentID = Integer.parseInt(request.getParameter("commentID"));
		}
		if (commentID == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 댓글 입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		CommentVO comment = new CommentDAO().getComment(commentID);
		if(comment == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 삭제된 댓글입니다.')");
			script.println("location.href=document.referrer;");
			script.println("</script>");	
		}
		if (!userID.equals(comment.getUserID())) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("history.back()");
			script.println("</script>");				
		} else {
			CommentDAO commentDAO = new CommentDAO();
			int result = commentDAO.delete(commentID);
			if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('댓글 삭제에 실패했습니다')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				PrintWriter script = response.getWriter();
				String real = "jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject_GlobalIn\\boardone\\img\\uploadImg";
				/* String commentReal = "C:\\Users\\SUCCU\\Desktop\\JSP_STUDY\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject\\boardone\\img\\uploadImg"; */
				File delFile = new File(real+"\\"+num+"사진"+commentID+".jpg");
				if(delFile.exists()){
					delFile.delete();
				}
				script.println("<script>");
				script.println("location.href=document.referrer;");
				script.println("</script>");
			}
		}
		%>
</body>
</html>