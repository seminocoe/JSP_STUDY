<%@page import="com.sample.SimpleData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("utf-8");
    	/* String message = request.getParameter("message"); */
    %>
    
    <jsp:useBean id="msg" class="com.sample.SimpleData"/> 
    <%-- <% SimpleData msg = new SimpleData();//기존 자바에선 이렇게 쓰던게 빈즈로는 위에처럼 사용함 %> --%>
    <jsp:setProperty property="message" name="msg"/>
    <%-- <%msg.setMessage(""); %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h1>자바 빈즈 프로그램 결과</h1>
<hr color="red"><br><br>

<font size="5">
메시지 : <jsp:getProperty property="message" name="msg"/>
<%-- <%= message %> --%><!-- 이렇게 해도 출력 가능 -->
</font>
</body>
</html>