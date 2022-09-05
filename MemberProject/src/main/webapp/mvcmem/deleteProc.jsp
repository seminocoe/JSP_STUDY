<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>

<meta http-equiv="Refresh" content="3;url=member.mdo?cmd=login">
<body>

<c:set var="result" value="${result }"/>

	<div align="center">
		<c:if test="${result eq 0 }">
			<script type="text/javascript">
				alert("비밀번호가 맞지 않습니다.");
				history.go(-1);//이전페이지로 가는건데 or history.back();
			</script>
		</c:if>
		
		<font size = "5" face = "궁서체">
			<b>회원님의 정보가 싸끄리 몽땅 삭제 되었습니다.</b><br><br>
			안녕히 가시고 다음 기회에 다시 뵙기를 기원합니다.
			3초 후에 Login Page로 이동합니다.
		</font>
	</div>
</html>