<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage="/error/error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
name 파라미터 값 :
<%=request.getParameter("name").toUpperCase() %><!-- 에러만들려고 일부러 파라미터 값 없는 걸 불러올려 함 -->

</body>
</html>