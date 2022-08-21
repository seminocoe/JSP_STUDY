<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import='java.net.URLEncoder' %>
    
    <%
	Cookie[] cookies = request.getCookies();
	
	if(cookies != null && cookies.length > 0){
		for(int i = 0 ; i < cookies.length ; i++){
			if(cookies[i].getName().equals("name")){
				cookies[i].setValue(URLEncoder.encode("강감찬 귀주대첩", "utf-8"));
				response.addCookie(cookies[i]);
			}
		}
	}
	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 값 변경</title>
</head>
<body>
name쿠기 값이 변경되었습니다.
</body>
</html>