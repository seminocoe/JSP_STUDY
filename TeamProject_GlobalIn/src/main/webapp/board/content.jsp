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
<c:set var="vo" value="${dao.getMember(loginID)}"/>

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
					<c:out value="${gechuCnt }"></c:out>
				</td>
			</tr>
			
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c }">작성자</td>
				<td align="center" width="125">
					${article.writer }
				</td>
				<td align="center" width="125" bgcolor="${value_c }" colspan="2">작성일</td>
				<td align="center" width="125" colspan="2">
					${article.regdate }
				</td>
			</tr>
			
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c }">글제목</td>
				<td align="center" width="375" colspan="5">
					${article.subject }
				</td>
			</tr>
			<tr>
				<td align="center" width="125" bgcolor="${value_c }">글내용</td>
				<td align="left" width="375" colspan="5">
					<c:if test="${viewFile.exists() }">
						<br><br><img src = "../board/img/uploadImg/${article.imageUID }.jpg" border="1px" width="300px" height="300px"><br><br>
					</c:if>
					<pre>${article.content }</pre>
				</td>
			</tr>
			
			<tr height="30">
				<td align="right" bgcolor="${value_c }" colspan="6">
					<input type="hidden" value="${article.imageUID }" name="imageUID">
					<c:if test="${loginID != null }">
						<c:if test="${article.writer eq vo.name}">
					<input type="button" value="글수정"
					onclick="document.location.href='/TeamProject/board/updateForm.bdo?num=${article.num }&pageNum=${pageNum }'">
					&nbsp;&nbsp;&nbsp;&nbsp;

					<input type="button" value="글삭제"
					onclick="document.location.href='/TeamProject/board/deleteForm.bdo?num=${article.num }&pageNum=${pageNum }&imageUID=${article.getImageUID() }'">
					&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
					
					<input type="button" value="답글쓰기"
					onclick="document.location.href='/TeamProject/board/writeForm.bdo?num=${article.num }&pageNum=${pageNum }&ref=${article.ref }&step=${article.step }&depth=${article.depth }'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					
					<input type="button" value="글목록"
					onclick="document.location.href='/TeamProject/board/list.bdo?pageNum=${pageNum }'">
					&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr align="center">
				<td colspan="2"><input type="button" onclick="location.href='evaluationAction.bdo?gechu=1&bichu=0&num=${num}'" value="좋아요(${gechu })"></td>
				<td colspan="4"><input type="button" onclick="location.href='evaluationAction.bdo?gechu=0&bichu=1&num=${num}'" value="싫어요(${bichu })"></td>
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
						<c:forEach var="list" items="${list }">
							<div>
								<div >
									<table style="text-align: center; border: 1px solid #dddddd">
										<tbody>
										<tr>						
										<td align="left">${list.userName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${list.commentDate }</td>		
										<td colspan="2"></td>
										<td align="right">
												<c:if test="${list.userID eq loginID }">
														<form name = "p_search">
															<a type="button" onclick="nwindow(${num },${list.commentID })">수정</a>
														</form>	
														<a onclick="return confirm('정말로 삭제하시겠습니까?')" href = "commentDeleteAction.bdo?num=${num }&commentID=${list.commentID }">삭제</a>
												</c:if>
										</td>
										</tr>
										<tr>
											<td colspan="5" align="left">${list.commentText }
											<c:if test="${list.imageUID != null}">
												<br><br><img src = "../board/img/uploadImg/${list.imageUID }.jpg" border="1px" width="100px" height="100px"><br><br>
											</c:if>
											</td>											
										</tr>
										</tbody>
									</table>			
						</div>
					</div>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>


<div align="center">
	<div>
		<form method="post" encType = "multipart/form-data" action="commentAction.bdo?num=${num }" name="commentInputForm" onsubmit="return checkText()">
			<table style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td style="border-bottom:none;" valign="middle"><br><br><c:if test="${loginID != null }">${vo.name }(${loginID })</c:if><c:if test="${loginID == null }">^오^</c:if></td>
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