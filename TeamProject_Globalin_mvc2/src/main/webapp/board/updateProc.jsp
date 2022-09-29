<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<c:if test="${check == 1 }">
	<meta http-equiv="Refresh"
		content="0;url=member.mdo?cmd=content&num=${num }&pageNum=${pageNum }">
</c:if>

<c:if test="${check == 0 }">
	<script type="text/javascript">
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);//이전페이지로 가는건데 or history.back();
	</script>
</c:if>