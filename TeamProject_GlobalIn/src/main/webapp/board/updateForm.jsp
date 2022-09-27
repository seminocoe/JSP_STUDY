<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="view/color.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="style.css?after">
<script type="text/javascript" src="script.js"></script>
</head>

<body bgcolor="${bodyback_c }">

<div align="center"><b>글 수정</b><br><br>

	<form action="/TeamProject/board/updatePro.bdo?pageNum=${pageNum }"
	 method="post" name="writeForm" onsubmit="return writeSave()" encType = "multipart/form-data">
	
		<table width="400" border="1" cellpadding="0" cellspacing="0" 
		align="center" bgcolor="${bodyback_c }">
		
		<tr>
			<td width="70" bgcolor="${value_c }" align="center">이름</td>
			<td width="330" align="left">
				<input type="text" size="12" maxlength="12" 
				name="writer" value="${article.writer }" readonly="readonly">
				<input type="hidden" name ="num" value="${article.num }">
				<input type="hidden" name ="imageUID" value="${article.imageUID }">
			</td>
		</tr>
		
		<tr>
			<td width="70" bgcolor="${value_c }" align="center">제목</td>
			<td width="330" align="left">
				<input type="text" size="50" maxlength="50" name="subject" value="${article.subject }">
			</td>
		</tr>

		<tr>
			<td width="70" bgcolor="${value_c }" align="center">이메일</td>
			<td width="330" align="left">
				<input type="text" size="30" maxlength="30" name="email" value="${article.email }">
			</td>
		</tr>
		
		<tr>
			<td width="70" bgcolor="${value_c }" align="center">내용</td>
			<td width="330" align="left">
				<textarea rows="13" cols="50" name="content">${article.content }</textarea>
				
			</td>
		</tr>
		
		<tr>
			<td width="70" bgcolor="${value_c }" align="center">비밀번호</td>
			<td width="330" align="left">
				<input type="password" size="10" maxlength="10" name="pass">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="file" name="fileName">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" bgcolor="${value_c }" align="center">
				<input type="submit" value="글수정">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" 
				onclick="window.location.href='/TeamProject/board/list.bdo?pageNum=${pageNum }'">
			</td>
		</tr>
		</table>
	</form>
</div>

</body>
</html>