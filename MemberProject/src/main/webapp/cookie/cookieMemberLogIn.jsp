<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키를 활용한 회원인증 프로그램</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">
	
<form action="cookieMemberLogInOK.jsp" method="post">
<table width="300" border="1" align ="center">
	<tr>
		<td colspan="2" bgcolor="<%=title_c%>">
		<div align="center">회원 로그인</div></td>
	</tr>
	
	<tr>
		<td width="100" bgcolor="<%=title_c%>">아이디</td>
		<td width="200" bgcolor="<%=value_c%>">
			<input type="text" name="id">
		</td>
	</tr>
	
	<tr>
		<td width="100" bgcolor="<%=title_c%>">비밀번호</td>
		<td width="200" bgcolor="<%=value_c%>">
			<input type="password" name="passwd">
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그인">&nbsp;&nbsp;
			<input type="reset" value="다시작성" >
		</td>
	</tr>
</table>
</form>
	
</body>
</html>