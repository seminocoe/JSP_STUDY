<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="view/color.jsp" %>

<%
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
String imageUID = request.getParameter("imageUID");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="style.css?after">
<script type="text/javascript">
	function deleteSave() {
		if(document.delForm.pass.value==""){
			alert("비밀번호를 입력해 주세요.");
			document.delForm.pass.focus();
			return false;
		}
	}
</script>

</head>
<body bgcolor="<%=bodyback_c%>">

<div align="center"><b>글 삭제</b>
<br><br>

	<form action="deleteProc.jsp?pageNum=<%=pageNum%>" method="post " name="delForm" onsubmit="return deleteSave()">
	
		<table width="360" border="1" align="center" cellpadding ="0" cellspacing ="0">
		
			<tr height="30">
				<td bgcolor="<%=value_c%>" align="center">
					<b>비밀번호를 입력해 주세요.</b>
				</td>
			</tr>
			
			<tr height="30">
				<td align="center">비밀번호:
					<input type="password" name="pass" size="8" maxlength="12">
					<input type="hidden" name="num" value="<%=num %>">
					<input type="hidden" name="pageNum" value="<%=pageNum %>">
					<input type="hidden" name="imageUID" value="<%=imageUID%>">
				</td>
			</tr>
			
			<tr height="30">
				<td align="center" bgcolor="<%=value_c%>">
					<input type="submit" value="글삭제">
					<input type="button" value="글목록" 
					onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
				</td>
			</tr>
		</table>
	
	</form>
</div>

</body>
</html>