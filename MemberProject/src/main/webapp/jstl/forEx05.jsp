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

<c:forTokens var="car" items="Sprinter Trueno AE86,RX-7 SavannaFC3S, Lancer Evolution III, RX-7 Efini FD3s" delims=",">
자동차 이름 : <c:out value="${car }"/> <br>
</c:forTokens>

</body>
</html>