<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취미와 성별</title>
</head>
<body>
	<h1>getParameterValues() 메소드 활용</h1>
	<form action="Sport" method="post">
	<fieldset>
		<legend><!-- 범례라는 뜻 --> 좋아하는 운동 및 성별</legend>
			<ul>
				<li>
					<label for="bassball">야구</label>	
					<input type="checkbox" name="sports" value="야구">
					<label for="football">축구</label>	
					<input type="checkbox" name="sports" value="축구">
					<label for="baskeball">농구</label>	
					<input type="checkbox" name="sports" value="농구">
				</li>
				<li>
					<label for="gender">남</label>
					<input type="radio" name="gender" value="남자" checked>
					<label for="gender">여</label>
					<input type="radio" name="gender" value="여자">
				</li>
				<li><input type="submit" value="전송"></li>
			</ul>
	</fieldset>
	</form>
</body>
</html>