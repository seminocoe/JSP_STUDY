<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='java.net.URLEncoder' %>

<%
Cookie cookie = new Cookie("name", URLEncoder.encode("홍길동", "utf-8"));
response.addCookie(cookie);
//<!-- 현재 네임에 있는 홍길동이 압축된 형태로 나오기 때문에 볼 수 업음 -->
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키생성</title>
</head>
<body>
	<%=cookie.getName()%> 쿠키의 값 = <%=cookie.getValue()%>
</body>
</html>