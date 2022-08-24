<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.boardone.BoardDAO" %>
<%@ page import="com.boardone.BoardVO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="view/color.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="style.css?after">
</head>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	try{
		BoardDAO dbPro = BoardDAO.getInstance();
		BoardVO article = dbPro.getArticle(num);
		
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		
%>
<body bgcolor="<%=bodyback_c%>">
<div align="center"><b>글 내용 상세 보기</b><br><br>
	<form>
	
		<table width="500" border="1" cellpadding="0" cellspacing="0" 
		bgcolor="<%=bodyback_c%>" align="center">
			<tr height="30">
				<td align="center" width="125" bgcolor="<%=value_c%>">글번호</td>
				<td align="center" width="125">
					<%=article.getNum() %>
				</td>
				<td align="center" width="125" bgcolor="<%=value_c%>">조회수</td>
				<td align="center" width="125">
					<%=article.getReadcount() %>
				</td>
			</tr>
			
			<tr height="30">
				<td align="center" width="125" bgcolor="<%=value_c%>">작성자</td>
				<td align="center" width="125">
					<%=article.getWriter() %>
				</td>
				<td align="center" width="125" bgcolor="<%=value_c%>">작성일</td>
				<td align="center" width="125">
					<%=sdf.format(article.getRegdate()) %>
				</td>
			</tr>
			
			<tr height="30">
				<td align="center" width="125" bgcolor="<%=value_c%>">글제목</td>
				<td align="center" width="375" colspan="3">
					<%=article.getSubject() %>
				</td>
			</tr>
			<tr>
				<td align="center" width="125" bgcolor="<%=value_c%>">글내용</td>
				<td align="left" width="375" colspan="3">
					<pre><%=article.getContent()%></pre>
				</td>
			</tr>
			
			<tr height="30">
				<td align="right" bgcolor="<%=value_c%>" colspan="4">
					<input type="button" value="글수정"
					onclick="document.location.href='updateForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum %>'">
					&nbsp;&nbsp;&nbsp;&nbsp;

					<input type="button" value="글삭제"
					onclick="document.location.href='deleteForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum %>'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					
					<input type="button" value="답글쓰기"
					onclick="document.location.href='writeForm.jsp?num=<%=num%>&pageNum=<%=pageNum %>&ref=<%=ref%>&step=<%=step%>&depth=<%=depth%>'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					
					<input type="button" value="글목록"
					onclick="document.location.href='list.jsp?pageNum=<%=pageNum %>'">
					&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			
		</table>
<%}catch(Exception e){} %>
	</form>
	<br><br><b>댓글</b><br><br>
	<form action="content.jsp?num=<%= num%>&pageNum=<%=pageNum%>">
		<textarea rows="5" cols="50" style="resize: none;"></textarea>
		<input type="submit" value="댓글 작성" style="WIDTH: 40pt; HEIGHT: 40pt">
	</form>
	
</div>
</body>
</html>