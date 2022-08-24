<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setAttribute("name", "홍길동");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
request의 name속성 : ${requestScope.name }<br>
code 파라미터 : ${param.code }<br><br>
<!-- 사이트 제목 뒤에 ?code=test넣으면 test가 뜸 파람가져오기 간단한 방법 -->
</head>
<body>
</body>
</html>