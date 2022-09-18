<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.boardone.BoardDAO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.io.File" %>

<% request.setCharacterEncoding("utf-8");%>
    
<%
    
    int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	String pass = request.getParameter("pass");
	String imageUID = request.getParameter("imageUID");
	BoardDAO dbPro = BoardDAO.getInstance();
	
	int check = dbPro.deleteArticle(num, pass);
	
	if(check == 1){
		if(imageUID != null){
			String real = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject_GlobalIn\\boardone\\img\\uploadImg";
			File delFile = new File(real+"\\"+imageUID+".jpg");
			if(delFile.exists()){
				delFile.delete();
			}
		}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<meta http-equiv="Refresh" content="0;url=list.jsp?pageNum=<%=pageNum%>">
</head>
<body>
<%}else{%>
	<script type="text/javascript">
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);//이전페이지로 가는건데 or history.back();
	</script>
<%}%>

</body>
</html>