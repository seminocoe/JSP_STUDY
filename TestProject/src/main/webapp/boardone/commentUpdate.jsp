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
	String pageNum = request.getParameter("pageNum");
	System.out.println(pageNum);
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
			<div class="jumbotron" style="padding-top: 1px;">				
				<h3><br>댓글수정창</h3>
				<form name = c_commentUpdate>
					<input type="text" id="update" style="width:400px;height:50px;" maxlength=1024 value="<%= commentText %>">
					<input type="button" onclick="send(<%=num %>,<%=commentID %>)" value="수정">
					<input type="hidden" name="pageNum" value="<%=pageNum%>"><!-- 넣어봤는데 작용 ㄴ -->
					<br><br>
					<input type="file" id="updatePicture" name="updatePicture"/>		
				</form>
			</div>
		</div>
		<div class="col-lg-10"></div>
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