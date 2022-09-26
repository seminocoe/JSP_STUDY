<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="com.comment.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title></title>
</head>
<body>
	<%
	int num = 0;
	if (request.getParameter("num") != null){
		num = Integer.parseInt(request.getParameter("num"));
	}
	int commentID = 0;
	if (request.getParameter("commentID") != null){
		commentID = Integer.parseInt(request.getParameter("commentID"));
	}
	CommentDAO commentDAO = new CommentDAO();
	String commentText = commentDAO.getUpdateComment(commentID);
	%>
	<div align="center">
		<div>
			<div style="padding-top: 1px;">				
				<h3><br>댓글수정창</h3>
				<form method="post" name = c_commentUpdate action="commentUpdate.jsp?num=<%= num %>&commentID=<%=commentID %>&commentText=<%=commentText %>">
					<input type="text" id="update" style="width:400px;height:50px;" maxlength=1024 value="<%= commentText %>">
					<input type="button" onclick="send(<%=num %>,<%=commentID %>)" value="수정">
					<br><br>
					<input type="file" id="updatePicture" name="updatePicture">		
				</form>
			</div>
		</div>
	</div>
</body>
<script>
	/* var upload = document.querySelector('#updatePicture');
	updatePicture.addEvent */
	function send(num,commentID){
		var sb;
		var commentText = document.c_commentUpdate.update.value;
		
		sb = "commentUpdateAction.jsp?num="+num+"&commentID="+commentID+"&commentText="+commentText;
		window.opener.location.href= sb;
		window.close();
	}
</script>
</html>