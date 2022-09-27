<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="view/color.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	<c:set var="loginID" value="${sessionScope.loginID }"/>
	
	 <c:choose>
	 	<c:when test="${loginID == null }">
			 <script type="text/javascript">
				alert("로그인을 해주세요.");
				location.href = '../board/mem/login.jsp';
			</script>
	 	</c:when>
	 	
	 	<c:otherwise>
	 		
	 			<c:if test="${num == 0 }">
	 				<script type="text/javascript">
			 		alert('유효하지 않은 글입니다.');
			 		location.href = '../board/list.bdo';
			 		</script>
	 			</c:if>
	 			
		 		<c:choose>
	 			<c:when test="${result == -1 }">
	 				<script type="text/javascript">
			 		alert('평가를 실패했습니다.');
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