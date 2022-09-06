<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<c:if test="${check == 1 }">
		<meta http-equiv="Refresh" content="0;url=/MemberProject/board/content.bdo?num=${num }&pageNum=${pageNum }">
	</c:if>
	
	<c:if test="${check != 1 }">
	비밀번호가 다릅니다.
	<a href="javascript:history.go(-1)">[글 수정 폼으로 돌아가기]</a>
	</c:if>