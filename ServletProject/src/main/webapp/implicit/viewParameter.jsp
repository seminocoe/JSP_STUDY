<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.Enumeration" %>
    <%@ page import="java.util.Map" %>
    
    <%
    	request.setCharacterEncoding("utf-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요청 파라미터 출력</title>
</head>
<body>
<b>request.getParameter()메소드 사용</b><br><br>
name 파라미터 : <%= request.getParameter("name") %> <br><br>
address 파라미터 : <%= request.getParameter("address") %> <br><br>
<b>request.getParameterValues()메소드 사용</b><br><br>

<%
	String[] values = request.getParameterValues("pet");
	
	if(values != null){
		for(int i = 0; i<values.length; i++){
%>
		<%= values[i] %>
<%
		}//end for
	}//end if
%>

<br><br><br><br>




<b>request.getParameterNames()메소드 사용</b><br><br>
<%
Enumeration enumData = request.getParameterNames();
	while(enumData.hasMoreElements()){
		String name = (String)enumData.nextElement();
%>
	<%= name  %>
<%
	}//while end
%>

<br><br><br><br>
<b>reguest.getParameterMap() 메소드 사용</b><br><br>
<%
Map parameterMap = request.getParameterMap();

String[] nameParam = (String[])parameterMap.get("name");
if(nameParam != null){
	
%>
	<%= nameParam[0] %>
<%
}//if end
%>
</body>
</html>