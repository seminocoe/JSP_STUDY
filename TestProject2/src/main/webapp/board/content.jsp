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

<c:set var="loginID" value="${sessionScope.loginID }"/>

<div align="center"><b>글 내용 상세 보기</b><br><br>
	<form>
	
		<table width="500" border="1" cellpadding="0" cellspacing="0" 
		bgcolor="${bodyback_c }" align="center">
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c }">글번호</td>
				<td align="center" width="125">
					${article.num }
				</td>
				<td align="center" width="63" bgcolor="${value_c }">조회수</td>
				<td align="center" width="62">
					${article.readcount }
				</td>
				<td align="center" width="63" bgcolor="${value_c }">추천수</td>
				<td align="center" width="62">
					<c:out value="${evaluationDAO.countGechu(num) }"></c:out>
					<%-- <%=evaluationDAO.countGechu(article.getNum()) %> --%>
				</td>
			</tr>
			
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c }">작성자</td>
				<td align="center" width="125">
					${article.writer }
				</td>
				<td align="center" width="125" bgcolor="${value_c }">작성일</td>
				<td align="center" width="125">
					${article.regdate }
				</td>
			</tr>
			
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c }">글제목</td>
				<td align="center" width="375" colspan="3">
					${article.subject }
				</td>
			</tr>
			<tr>
				<td align="center" width="125" bgcolor="${value_c }">글내용</td>
				<td align="left" width="375" colspan="3">
					<%
					String real = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject_GlobalIn\\boardone\\img\\uploadImg";
					File viewFile = new File(real+"\\"+article.getImageUID()+".jpg");
					if(viewFile.exists()){ %>
						<br><br><img src = "../board/img/uploadImg/<%=article.getImageUID()%>.jpg" border="1px" width="300px" height="300px"><br><br>
					<%} %>
					
					<c:if test="">
						<br><br><img src = "../board/img/uploadImg/${article.imageUID }.jpg" border="1px" width="300px" height="300px"><br><br>
					</c:if>
					<pre>${article.content }</pre>
				</td>
			</tr>
			
			<tr height="30">
				<td align="right" bgcolor="${value_c }" colspan="4">
					<input type="hidden" value="<%=article.getImageUID() %>" name="imageUID">
					<%
					if(loginID != null){ 
						if(article.getWriter().equals(vo.getName())){
					%>
					<input type="button" value="글수정"
					onclick="document.location.href='/MemberProject/board/updateForm.bdo?num=${article.num }&pageNum=${pageNum }'">
					&nbsp;&nbsp;&nbsp;&nbsp;

					<input type="button" value="글삭제"
					onclick="document.location.href='/MemberProject/board/deleteForm.bdo?num=${article.num }&pageNum=${pageNum }'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					
					<input type="button" value="답글쓰기"
					onclick="document.location.href='/MemberProject/board/writeForm.bdo?num=${article.num }&pageNum=${pageNum }&ref=${article.ref }&step=${article.step }&depth=${article.depth }'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					
					<input type="button" value="글목록"
					onclick="document.location.href='/MemberProject/board/list.bdo?pageNum=${pageNum }'">
					&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr align="center">
				<td colspan="2"><input type="button" onclick="location.href='evaluationAction.jsp?gechu=1&bichu=0&num=<%=num%>'" value="좋아요(<%=gechu %>)"></td>		
				<td colspan="4"><input type="button" onclick="location.href='evaluationAction.jsp?gechu=0&bichu=1&num=<%=num%>'" value="싫어요(<%=bichu %>)"></td>		
			</tr>
			
		</table>
	</form>
	<div>
			<div>
				<table style="text-align: center; border: 1px solid #dddddd">
					<tbody>
					<tr>
						<td align="left" bgcolor="beige">댓글</td>
					</tr>
					<tr>
						<%
							CommentDAO commentDAO = new CommentDAO();
							ArrayList<CommentVO> list = commentDAO.getList(num);
							for(int i=0; i<list.size(); i++){
						%>
							<div>
								<div >
									<table style="text-align: center; border: 1px solid #dddddd">
										<tbody>
										<tr>						
										<td align="left"><%= list.get(i).getUserName() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= list.get(i).getCommentDate()%></td>		
										<td colspan="2"></td>
										<td align="right"><%
													if(list.get(i).getUserID() != null && list.get(i).getUserID().equals(loginID)){
												%>
														<form name = "p_search">
															<a type="button" onclick="nwindow(<%=num %>,<%=list.get(i).getCommentID()%>)">수정</a>
														</form>	
														<a onclick="return confirm('정말로 삭제하시겠습니까?')" href = "commentDeleteAction.jsp?num=<%=num %>&commentID=<%= list.get(i).getCommentID() %>">삭제</a>
																	
												<%
													}
												%>	
										</td>
										</tr>
										<tr>
											<td colspan="5" align="left"><%= list.get(i).getCommentText() %>
											<% 	
												String commentReal = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject_GlobalIn\\boardone\\img\\uploadImg";
												/* String commentReal = "C:\\Users\\SUCCU\\Desktop\\JSP_STUDY\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject\\boardone\\img\\uploadImg"; */
												File commentFile = new File(commentReal+"\\"+num+"사진"+list.get(i).getCommentID()+".jpg");
												if(commentFile.exists()){
											%>	
											<br><br><img src = "../boardone/img/uploadImg/<%=num %>사진<%=list.get(i).getCommentID() %>.jpg" border="1px" width="100px" height="100px"><br><br></td>											
										<%}%>	
										</tr>
										</tbody>
									</table>			
						</div>
					</div>
					<%
						}
					%>
				</tr>
			</table>
		</div>
	</div>


<div align="center">
	<div>
		<form method="post" encType = "multipart/form-data" action="commentAction.jsp?num=<%= num %>" name="commentInputForm" onsubmit="return checkText()">
			<table style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td style="border-bottom:none;" valign="middle"><br><br><%if(loginID != null){%><%= vo.getName() %>(<%=loginID%>)<%}else{ %>^오^<%} %></td>
					<!-- <td><input type="text" style="height:100px;" placeholder="상대방을 존중하는 댓글을 남깁시다." name="commentText"></td> -->
					<td><textarea style="height:100px;" name="commentText" placeholder="상대방을 존중하는 댓글을 남깁시다."></textarea></td>
					<td><br><br><input type="submit" value="댓글 작성"></td>
				</tr>
				<tr>
					<td colspan="3"><input type="file" name="fileName"></td>
				</tr>
			</table>
		</form>
	</div>
</div>	
</div>
</body>
</html>