<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.boardone.BoardDAO" %>
<%@ page import="com.boardone.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="view/color.jsp" %>

<%!
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%
List<BoardVO> articleList = null;
BoardDAO dbPro = BoardDAO.getInstance();
request.setCharacterEncoding("UTF-8");//이거 없으면 파라미터값 깨짐
articleList = dbPro.getSearch(request.getParameter("searchField"), request.getParameter("searchText"));
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="style.css?after">
</head>
<body bgcolor="<%=bodyback_c%>">

<div align="center"><b>글 목록(검색된 글:<%=articleList.size()%>)</b>
<table width="700">
   <tr>
      <td align="right" bgcolor="<%=value_c%>">
         <a href="writeForm.jsp">글쓰기</a>
      </td>
   </tr>
</table>

<% if(articleList.size() == 0){ %>

<table width="700" border="1" cellpadding="0" cellspacing="0">
   <tr>
      <td align="center"">
         검색 결과가 없습니다.
      </td>
   </tr>
</table>

<%}else{ %>

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
         <%=article.getNum()-1 %>
      </td>
      
      <td align="center" width="250">
         <a href="content.jsp?num=<%= article.getNum()%>&pageNum=1">
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


</div>


<!-- 검색기능 창ex 지금 post인데 get으로 바꿔보기 츄라이 해도 될 듯 -->
<div align="center">
         <form method="post" name="search" action="searchbbs.jsp">
            <table>
               <tr>
                  <td><select name="searchField">
                        <!-- <option value="0">선택</option> -->
                        <!-- <option value="all">전체</option> 일단 무리 -->
                        <option value="subject">제목</option>
                        <option value="writer">작성자</option>
                        <option value="content">내용</option>
                  </select></td>
                  <td><input type="text" class="form-control"
                     placeholder="검색어 입력" name="searchText" maxlength="100"></td>
                  <td><button type="submit" class="btn btn-success">검색</button></td>
               </tr>
            </table>
            <table width="700">
	            <tr>
	                <td colspan="3" bgcolor="<%=value_c%>" align="center">
	                <input type="button" value="전체목록" onclick="window.location='list.jsp'">
	                </td>
	            </tr>
            </table>
         </form>
</div>
</body>
</html>