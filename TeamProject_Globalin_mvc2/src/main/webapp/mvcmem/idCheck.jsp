<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복체크</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="#FFFFCC">
<br>
<div align="center">
<b>${id }</b>

<c:if test="${check eq true }">
는 이미 존재하는 ID 입니다.<br><br>
</c:if>
<c:if test="${check ne true }">
사용 가능한 ID 입니다.<br><br>
</c:if>
<a href="#" onclick="javascript:self.close()">닫기</a>
</div>
</body>
</html>