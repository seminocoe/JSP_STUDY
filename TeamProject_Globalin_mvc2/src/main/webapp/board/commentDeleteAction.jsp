<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="view/color.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

	<c:set var="loginID" value="${sessionScope.loginID }" />

	<c:if test="${loginID == null }">
		<script type="text/javascript">
			alert('로그인을 하세요.');
			location.href = 'member.mdo?cmd=login';
 		</script>
	</c:if>
	<c:if test="${commentID == 0 }">
		<script type="text/javascript">
			alert('유효하지 않은 댓글 입니다.');
			history.back();
		</script>
	</c:if>
	<c:if test="${comment == null }">
		<script type="text/javascript">
			alert('이미 삭제된 댓글입니다.');
			location.href=document.referrer;
		</script>
	</c:if>
	<c:choose>
		<c:when test="${!loginID eq comment.userID }">
			<script type="text/javascript">
			alert('권한이 없습니다.');
			history.back();
		</script>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${result == -1 }">
					<script type="text/javascript">
				alert('댓글 삭제에 실패했습니다');
				history.back();
			</script>
				</c:when>
				<c:otherwise>
					<script type="text/javascript">
				location.href=document.referrer;
			</script>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>

</body>
</html>