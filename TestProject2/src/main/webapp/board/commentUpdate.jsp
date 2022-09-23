<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title></title>
</head>
<body>
	<div align="center">
			<div style="padding-top: 1px;">				
				<h3><br>댓글수정창</h3>
				<form method="post" name = c_commentUpdate action="commentUpdate.bdo?num=${num }&commentID=${commentID }&commentText=${commentText}">
					<input type="text" id="update" style="width:400px;height:50px;" maxlength=1024 value="${commentText }">
					<input type="button" onclick="send(${num },${commentID })" value="수정">
					<br><br>
					<input type="file" id="updatePicture" name="updatePicture">		
				</form>
			</div>
	</div>
</body>
</html>