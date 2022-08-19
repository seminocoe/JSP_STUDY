<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='java.net.URLEncoder' %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 목록</title>
</head>
<body>
<br>
<%
	Cookie[] cookies = request.getCookies();
	
	if(cookies != null && cookies.length > 0){
		for(int i = 0 ; i < cookies.length ; i++){
%>
	<%=cookies[i].getName() %> = 
	<%=URLDecoder.decode(cookies[i].getValue(), "utf-8") %><br>
	<!-- 이걸로 홍길동의 해독 (아까 홍길동을 키고서 이 화면을 켜야함)-->
<%
		}
	}else{
%>
쿠키가 존재하지 않습니다.
<%} %>
</body>
</html>