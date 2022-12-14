<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="view/color.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../board/style.css?after">
<script type="text/javascript" src="../board/script.js"></script>
</head>
<body bgcolor="${bodyback_c }">

	<c:set var="loginID" value="${sessionScope.loginID }" />

	<div align="center">
		<b><a href="member.mdo?cmd=list&pageNum=1">글 목록</a>(전체
			글:${count })</b>
		<table width="700">
			<tr>
				<c:choose>
					<c:when test="${loginID == null }">
						<td align="left" bgcolor="${value_c }">
							<a href="member.mdo?cmd=login">로그인</a>
						</td>
					</c:when>
					<c:otherwise>
						<td align="left" bgcolor="${value_c }">
							<a href="member.mdo?cmd=logout">로그아웃</a>
						</td>
					</c:otherwise>
				</c:choose>
				<td align="right" bgcolor="${value_c }"><a
					onclick="return loginIdCheck('${loginID }')">글쓰기</a></td>
			</tr>
		</table>

		<c:if test="${count == 0 }">

			<table width="700" border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">게시판에 저장된 글이 없습니다.</td>
				</tr>
			</table>
		</c:if>

		<c:if test="${count > 0 }">
			<table width="750" border="1" cellpadding="0" cellspacing="0"
				align="center">

				<tr height="30" bgcolor="${value_c }">
					<td align="center" width="50">번호</td>
					<td align="center" width="250">제목</td>
					<td align="center" width="100">작성자</td>
					<td align="center" width="150">작성일</td>
					<td align="center" width="50">조회</td>
					<td align="center" width="50">추천수</td>
					<td align="center" width="100">IP</td>
				</tr>

				<c:forEach var="article" items="${articleList }">

					<tr height="30">
						<td align="center" width="50"><c:out value="${number }" /> <c:set
								var="number" value="${number - 1 }" /></td>

						<td width="250"><c:if test="${article.depth > 0}">
								<img src="../board/img/level.gif" width="${5 * article.depth }"
									height="16">
								<img src="../board/img/re.gif">
							</c:if> <c:if test="${article.depth == 0}">
								<img src="../board/img/level.gif" width="${5 * article.depth }"
									height="16">
							</c:if> <a class="my_text"
							href="member.mdo?cmd=content&num=${article.num }&pageNum=${currentPage }">
								${article.subject }</a> <c:if test="${article.imageUID != null }">
									1
							</c:if> <c:set var="num" value="${article.num }"></c:set> <c:set
								var="cmCnt" value="${commentDAO.countComment(num) }"></c:set> <c:if
								test="${cmCnt >= 1 }">
				[${cmCnt }]
			</c:if> <c:if test="${article.readcount >= 20 }">
								<img src="../board/img/hot.gif" border="0" height="16">
							</c:if></td>

						<td align="center" width="100"><a
							href="mailto:${article.email }"> ${article.writer }</a></td>

						<td align="center" width="150">${article.regdate }</td>

						<td align="center" width="50">${article.readcount }</td>

						<td align="center" width="50"><c:set var="evCnt"
								value="${evaluationDAO.countGechu(num) }"></c:set> ${evCnt }</td>

						<td align="center" width="100">${article.ip }</td>

					</tr>

				</c:forEach>
			</table>
		</c:if>

		<!-- //페이지 블럭 -->
		<c:if test="${count > 0 }">

			<c:set var="pageBlock" value="${4 }" />
			<c:set var="imsi" value="${count % pageSize == 0 ? 0:1}" />
			<fmt:parseNumber var="pageCount" value="${count / pageSize + imsi }"
				integerOnly="true" />

			<!-- 	//시작 페이지 -->
			<fmt:parseNumber var="result" value="${(currentPage-1) / pageBlock }"
				integerOnly="true" />
			<c:set var="startPage" value="${result * pageBlock + 1}" />

			<!-- 	//마지막 페이지 -->
			<c:set var="endPage" value="${startPage + pageBlock -1}" />

			<!-- 	//마지막으로 보여줄 페이지 -->
			<c:if test="${endPage > pageCount }">
				<c:set var="endPage" value="${pageCount }" />
			</c:if>

			<!-- 	//페이지 블럭을 이전과 다음 작업 -->

			<c:if test="${startPage > pageBlock }">
				<c:if test="${find_box == null }">
 					<a href="member.mdo?cmd=list&pageNum=1">[<<]</a>
					<a href="member.mdo?cmd=list&pageNum=${startPage-pageBlock }">[이전]</a>
					<a href="member.mdo?cmd=list&pageNum=${currentPage - 1 }">[<]</a> 
				</c:if>
				<c:if test="${find_box != null }">
					<%-- <a href="member.mdo?cmd=list&pageNum=${startPage-pageBlock }&searchWhat=${searchWhat }&searchText=${searchText }">[이전]</a> --%>
					<a href="#" onclick="frm_sub(${startPage-pageBlock })">[이전]</a>
				</c:if>
			</c:if>

			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${find_box == null }">
					<a href="member.mdo?cmd=list&pageNum=${i }">[${i }]</a>
				</c:if>
				<c:if test="${find_box != null }">
					<%-- <a href="member.mdo?cmd=list&pageNum=${i }&searchWhat=${searchWhat }&searchText=${searchText }">[${i }]</a> --%>
					<a href="#" onclick="frm_sub(${i })">[${i }]</a>
				</c:if>
			</c:forEach>

			<c:if test="${endPage < pageCount}">
				<c:if test="${find_box == null }">
 					<a href="member.mdo?cmd=list&pageNum=${currentPage + 1}">[>]</a>
					<a href="member.mdo?cmd=list&pageNum=${startPage+pageBlock}">[다음]</a>
					<a href="member.mdo?cmd=list&pageNum=${pageCount }">[>>]</a> 
				</c:if>
				<c:if test="${find_box != null }">
					<%-- <a href="member.mdo?cmd=list&pageNum=${startPage+pageBlock}&searchWhat=${searchWhat }&searchText=${searchText }">[다음]</a> --%>
					<a href="#" onclick="frm_sub(${startPage+pageBlock })">[다음]</a>
				</c:if>
			</c:if>
		</c:if>

		<form action="" method="post" name="i_frm">
			<input type="hidden" name="find_box" value="${find_box }">
			<input type="hidden" name="find" value="${find }">
		</form>

		<!-- 검색 창 -->
		<form action="member.mdo?cmd=list" method="post" name="find_frm" onsubmit="return check(${pageNum})">
			<select name="find">
				<option value="writer">작성자</option>
				<option value="subject">제목</option>
				<option value="content">내용</option>
			</select> 
			<input type="text" name="find_box"> 
			<input type="submit"value="검색">
		</form>
	</div>
</body>
</html>