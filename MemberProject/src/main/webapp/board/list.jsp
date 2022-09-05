<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="view/color.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="style.css?after">
</head>
<body bgcolor="${bodyback_c }">

<div align="center"><b>글 목록(전체 글:${count })</b>
<table width="700">
	<tr>
		<td align="right" bgcolor="${value_c }">
			<a href="/MemberProject/board/writeForm.bdo">글쓰기</a>
		</td>
	</tr>
</table>

<c:if test="${count == 0 }">

<table width="700" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center"">
			게시판에 저장된 글이 없습니다.
		</td>
	</tr>
</table>
</c:if>

<c:if test="${count > 0 }">
<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">

	<tr height="30" bgcolor="<%=value_c%>">
		<td align="center" width="50">번호</td>
		<td align="center" width="250">제목</td>
		<td align="center" width="100">작성자</td>
		<td align="center" width="150">작성일</td>
		<td align="center" width="50">조회</td>
		<td align="center" width="100">IP</td>
	</tr>
	
<%
	for(int i = 0; i < articleList.size(); i++){
		BoardVO article = (BoardVO)articleList.get(i);
		
%>

	<tr height="30">
		<td align="center" width="50">
			<%=number-- %>
		</td>
		<td width="250">
		
		<%
		int wid = 0;
		
		if(article.getDepth() > 0) {
			wid = 5 * (article.getDepth());
		%>
			<img src="img/level.gif" width="<%=wid%>" height="16">
			<img src="img/re.gif">
		<%}else{%>
			<img src="img/level.gif" width="<%=wid%>" height="16">
		<%} %>
				
			<a href="content.jsp?num=<%= article.getNum()%>&pageNum=<%=currentPage%>">
			<%=article.getSubject()%></a>
			<% if(article.getReadcount() >= 20) {%>
			<img src="img/hot.gif" border="0" height="16">
			<%} %>
		</td>
		
		<td align="center" width="100">
			<a href ="mailto:<%=article.getEmail()%>">
			<%=article.getWriter()%></a>
		</td>
		
		<td align="center" width="150">
			<%=sdf.format(article.getRegdate())%>
		</td>
		
		<td align="center" width="50">
			<%=article.getReadcount()%>
		</td>
		
		<td align="center" width="100">
		<%=article.getIp()%>
		</td>
		
	</tr> 

<%}//end for %>

</table>

<%}//end else %>

<%
//페이지 블럭

if(count > 0){
	
	int pageBlock = 4;

	int imsi = count % pageSize == 0? 0:1;
	
	int pageCount = count/pageSize + imsi;
	
	//시작 페이지
	int startPage = (int)((currentPage-1)/pageBlock)*pageBlock+1;	
	
	//마지막 페이지
	int endPage = startPage + pageBlock -1;
	
	//마지막으로 보여줄 페이지
	if(endPage > pageCount)endPage = pageCount;
	
	//페이지 블럭을 이전과 다음 작업
	if(startPage > pageBlock){
		//검색일 경우와 아닐 경우 페이지 처리
		if(searchText == null){
%>
	<a href="list.jsp?pageNum=1">[<<]</a>
	<a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
	<a href="list.jsp?pageNum=<%=(Integer.parseInt(pageNum)-1)%>">[<]</a>
	<%}else{%>
	<a href="list.jsp?pageNum=<%=startPage-pageBlock%>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[이전]</a>
<%
	}//end else
}//end if
for(int i = startPage; i <= endPage; i++){
	if(searchText == null){
%>
<a href="list.jsp?pageNum=<%=i %>">[<%=i%>]</a>
	<%}else{ %>
<a href="list.jsp?pageNum=<%=i %>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[<%=i%>]</a>
<%
		}//end else
	}//end for
	if(endPage < pageCount){
		if(searchText == null){
%>	
	<a href="list.jsp?pageNum=<%=(Integer.parseInt(pageNum)+1)%>">[>]</a>
	<a href="list.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>
	<a href="list.jsp?pageNum=<%=pageCount%>">[>>]</a>
	<%}else{ %>
	<a href="list.jsp?pageNum=<%=startPage+pageBlock%>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[다음]</a>
<%
		}//endelse
	}//end if
}//end 종합 if(count > 0)
%>	
<!-- 검색 창 -->
<form action="list.jsp">
	<select name="searchWhat">
		<option value="writer">작성자</option>
		<option value="subject">제목</option>
		<option value="content">내용</option>
	</select>
	<input type="text" name="searchText">
	<input type="submit" value="검색">
</form>
</div>

</body>
</html>