<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h3>EL(Expression Language)</h3>
<p>연산자를 사용</p>

<table border="1">
	<tr><td><b>표현식</b></td><td><b>값</b></td></tr>
	<tr><td><b>\${2+5 }</b></td><td><b>${2+5 }</b></td></tr>
	<tr><td><b>\${4/5 }</b></td><td><b>${4/5 }</b></td></tr>
	<tr><td><b>\${5 div 6 }</b></td><td><b>${5 div 6 }</b></td></tr> <!-- div라는 태그가 있기때문에 생기는 오류 -->
	<tr><td><b>\${5 mod 7 }</b></td><td><b>${5 mod 7 }</b></td></tr>
	<tr><td><b>\${5 lt 7 }</b></td><td><b>${5 lt 7 }</b></td></tr>
	<tr><td><b>\${5 gt 7 }</b></td><td><b>${5 gt 7 }</b></td></tr>
	<tr><td><b>\${(5 > 3) ? 5 : 3}</b></td><td><b>${(5 > 3) ? 5 : 3}</b></td></tr>
</table>

</body>
</html>