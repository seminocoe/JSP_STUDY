<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
%>

	<jsp:useBean id="dao" class="com.memberone.StudentDAO"/>
	<jsp:useBean id="vo" class="com.memberone.StudentVO"/>
    <jsp:setProperty property="*" name="vo"/><!-- 이클립스 vo에 있는 모든 세터 메소드를 활용가능? -->

<%
	boolean flag = dao.memberInsert(vo);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 확인</title>
<link rel="sttlesheet" type="text/css" href="style.css">
</head>
<body bgcolor="#FFFFCC">
<br><Br>
<div align="center">

<%
if(flag){
	out.println("<b>회원가입을 진심으로 축하 드립니다.</b><br>");
	out.println("<a href=login.jsp>로그인</a>");
}else{
	out.println("<b>회원가입에 실패하였습니다.</b><br>");
	out.println("<a href=regForm.jsp>다시가입</a>");
}
%>

</div>
</body>
</html>