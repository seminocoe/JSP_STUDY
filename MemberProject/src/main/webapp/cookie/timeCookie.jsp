<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Cookie cookie = new Cookie("hour", "1time");
	cookie.setMaxAge(60);//60초 1분을 의미함
	response.addCookie(cookie);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
유효기간이 1분인 hour 쿠키 생성
</body>
</html>