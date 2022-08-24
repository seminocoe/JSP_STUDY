<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int score = 85;
	request.setAttribute("score", score);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	<c:choose>
		<c:when test="${score ge 90}">
			당신의 학점은 Aです
		</c:when>
		<c:when test="${score ge 80}">
			당신의 학점은 Bです
		</c:when>
		<c:when test="${score ge 70}">
			당신의 학점은 Cです
		</c:when>
		<c:when test="${score ge 60}">
			당신의 학점은 Dです
		</c:when>
		<c:otherwise>당신의 학점은 Fです</c:otherwise>
	</c:choose>

</body>
</html>