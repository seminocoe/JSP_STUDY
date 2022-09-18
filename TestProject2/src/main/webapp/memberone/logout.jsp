<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
session.invalidate();//세션 무효화
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<body>
<font size="5" face="궁서체">
성공적으로 로그아웃 되었습니다.<br><br>
<a href="login.jsp">로그인 페이지로 이동</a>
</font>
</body>
</html>