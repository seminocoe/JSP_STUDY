<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>


<meta http-equiv="Refresh" content="1;url=member.mdo?cmd=login">
<body>

<c:set var="result" value="${result }" />

<div align="center">
     <c:if test="${result eq 0 }">
     <script type="text/javascript">
      alert("비밀번호가 맞지 않습니다.");
      history.go(-1);
    </script>
     </c:if>

<font size="5" face="굴림체">
회원님의 정보가 삭제 되었습니다.<br><br>
안녕히 가세요.<br><br>
Login Page로 이동합니다.

</font>
</div>

<body>

</body>
</html>