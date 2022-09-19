<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="dao" class="com.mvcmem.model.StudentDAO"/>

<%
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");

	int check = dao.loginCheck(id, pass);
	
	if(check == 1){//로그인 성공
		session.setAttribute("loginID", id);//지금 서버에 저장되어 있음 섹션값으로?
		/* response.sendRedirect("login.jsp"); */
		response.sendRedirect("/board/mem/login.jsp");
	}else if(check == 0){//아이디는 존재하는데 비밀번호가 오류
%>
<script type="text/javascript">
	alert("비밀번호가 틀렸습니다.")
	history.go(-1)//바로 이전 페이지로 돌아감
</script>
<%}else{//아이디가 존재하지 않는 경우%>
<script type="text/javascript">
	alert("아이디가 존재하지 않습니다.")
	history.go(-1)
</script>
<%}%>