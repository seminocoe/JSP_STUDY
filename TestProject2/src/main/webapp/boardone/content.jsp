<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.boardone.BoardDAO" %>
<%@ page import="com.boardone.BoardVO" %>
<%@ page import="com.evaluation.*" %>
<%@ page import="com.memberone.*" %>
<%@ page import="com.comment.*" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="view/color.jsp" %>

<jsp:useBean id="dao" class="com.memberone.StudentDAO"/>

<%@page import="java.io.*"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="style.css?after">
<script type="text/javascript">


</script>
</head>
<%
	String loginID = (String)session.getAttribute("loginID");//여기서도 섹션이 죽으면 안되서 살게해줌
	StudentVO vo = dao.getMember(loginID);
%>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	try{
		EvaluationDAO evaluationDAO = new EvaluationDAO();
		
		BoardDAO dbPro = BoardDAO.getInstance();
		BoardVO article = dbPro.getArticle(num);
		
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		
		int gechu = 0;
		int bichu = 0;
		ArrayList<EvaluationVO> listEva = evaluationDAO.getList(num);
		for(int i=0; i<listEva.size(); i++){
			gechu = gechu + listEva.get(i).getGechu();
			bichu = bichu + listEva.get(i).getBichu();
		}
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
				<td align="center" width="63" bgcolor="<%=value_c%>">조회수</td>
				<td align="center" width="62">
					<%=article.getReadcount() %>
				</td>
				<td align="center" width="63" bgcolor="<%=value_c%>">추천수</td>
				<td align="center" width="62">
					<%=evaluationDAO.countGechu(article.getNum()) %>
				</td>
			</tr>
			
			<tr height="30">
				<td align="center" width="125" bgcolor="<%=value_c%>">작성자</td>
				<td align="center" width="125">
					<%=article.getWriter() %>
				</td>
				<td align="center" width="125" bgcolor="<%=value_c%>" colspan="2">작성일</td>
				<td align="center" width="125" colspan="2">
					<%=sdf.format(article.getRegdate()) %>
				</td>
			</tr>
			
			<tr height="30">
				<td align="center" width="125" bgcolor="<%=value_c%>">글제목</td>
				<td align="center" width="375" colspan="5">
					<%=article.getSubject() %>
				</td>
			</tr>
			<tr>
				<td align="center" width="125" bgcolor="<%=value_c%>">글내용</td>
				<td align="left" width="375" colspan="5">
					<%
					String real = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject_GlobalIn\\boardone\\img\\uploadImg";
					File viewFile = new File(real+"\\"+article.getImageUID()+".jpg");
					if(viewFile.exists()){ %>
						<br><br><img src = "../boardone/img/uploadImg/<%=article.getImageUID()%>.jpg" border="1px" width="300px" height="300px"><br><br>
					<%} %>
					<pre><%=article.getContent()%></pre>
				</td>
			</tr>
			
			<tr height="30">
				<td align="right" bgcolor="<%=value_c%>" colspan="6">
					<input type="hidden" value="<%=article.getImageUID() %>" name="imageUID">
					<%
					if(loginID != null){ 
						if(article.getWriter().equals(vo.getName())){
					%>
					<input type="button" value="글수정"
					onclick="document.location.href='updateForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum %>'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="글삭제"
					onclick="document.location.href='deleteForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum %>&imageUID=<%=article.getImageUID()%>'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<%} %>
					<input type="button" value="답글쓰기"
					onclick="document.location.href='writeForm.jsp?num=<%=num%>&pageNum=<%=pageNum %>&ref=<%=ref%>&step=<%=step%>&depth=<%=depth%>'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<%} %>
					
					<input type="button" value="글목록"
					onclick="document.location.href='list.jsp?pageNum=<%=pageNum %>'">
					&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			
					<tr align="center">
						<td colspan="2"><input type="button" onclick="location.href='evaluationAction.jsp?gechu=1&bichu=0&num=<%=num%>'" value="좋아요(<%=gechu %>)"></td>		
						<td colspan="4"><input type="button" onclick="location.href='evaluationAction.jsp?gechu=0&bichu=1&num=<%=num%>'" value="싫어요(<%=bichu %>)"></td>		
					</tr>
		
		</table>
<%}catch(Exception e){} %>
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

<script type="text/javascript">
	function nwindow(num,commentID,pageNum){
		window.name = "commentParant";
		var url= "commentUpdate.jsp?num="+num+"&commentID="+commentID;
		window.open(url,"","width=600,height=230,left=300");
	}
</script>
	
</body>
</html>