<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.comment.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="java.io.File" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="comment" class="com.comment.CommentVO" scope="page" />
<jsp:setProperty name="comment" property="commentText" />
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
	 	
		String realFolder="";
		String saveFolder = "boardone/img/uploadImg";
		String encType = "utf-8";
		int maxSize=5*1024*1024;
		
		ServletContext context = this.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = null;
		
		multi = new MultipartRequest(request,realFolder,maxSize,encType,new DefaultFileRenamePolicy());		
		String fileName = multi.getFilesystemName("fileName");
		String commentText = multi.getParameter("commentText");
		comment.setCommentText(commentText);
		
		
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
	 		if (comment.getCommentText() == null){
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("alert('입력이 안된 사항이 있습니다.')");
		 		script.println("history.back()");
		 		script.println("</script>");
		 	} else {
		 		CommentDAO commentDAO = new CommentDAO();
		 		int commentID = commentDAO.write(num, userID, comment.getCommentText());
		 		if (commentID == -1){
			 		PrintWriter script = response.getWriter();
			 		script.println("<script>");
			 		script.println("alert('댓글 쓰기에 실패했습니다.')");
			 		script.println("history.back()");
			 		script.println("</script>");
			 	}
		 		else{
			 		PrintWriter script = response.getWriter();
			 		if(fileName != null){
						File oldFile = new File(realFolder+"\\"+fileName);
						File newFile = new File(realFolder+"\\"+num+"사진"+(commentID-1)+".jpg");
						oldFile.renameTo(newFile);
					}
			 		script.println("<script>");
			 		script.println("location.href=document.referrer;");
			 		script.println("</script>");
			 	}
		 	}
		 }
	 %>
</body>
</html>