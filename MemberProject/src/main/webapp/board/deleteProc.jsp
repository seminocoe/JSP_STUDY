<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<c:if test="${check == 1 }">
		<meta http-equiv="Refresh" content="0;url=/MemberProject/board/list.bdo">
	</c:if>
	
	<c:if test="${check == 0 }">
	비밀번호가 다릅니다.
	<a href="javascript:history.go(-1)">[비밀번호 입력부분으로 돌아가기]</a>
	</c:if>