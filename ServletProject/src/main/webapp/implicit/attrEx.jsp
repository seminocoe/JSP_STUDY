<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    // pageContext Scope에 속성 값 저장하기 (해당 JSP 페이지 내에서만 유효함)
    pageContext.setAttribute("pageAttribute", "홍길동");
    //pageContext.setAttribute("pageAttribute", "홍길동", PageContext.PAGE_SCOPE);//해당page에서만 사용한다는 범위 지정도 가능
    //뒤에 것은 생략 가능하기에 위에 껄로 일단 적용
    
    //request Scope에 속성 저장하기 (client request 객체가 유지되는 동안만 유효성을 가짐)
    request.setAttribute("requestAttribute", "010-1234-1234");
    //pageContext.setAttribute("requestAttribute", "010-1234-1234", PageContext.REQUEST_SCOPE);
    //이 친구들은 로그아웃 되기 전까지 살아있음
    
    //session Scope 속성 저장하기
    session.setAttribute("sessionAttribute",	 "hong@naver.com");
    //pageContext.setAttribute("sessionAttribute", "hong@naver.com", PageContext.SESSION_SCOPE);
    //섹션이 유지되는 동안은 살아있음(서블릿,jsp가 가지고있음 섹션은)
    
    //application Scop 속성 저장하기
    application.setAttribute("applicationAttribute" , "Global In(주)");
    //pageContext.setAttribute("applicationAttribute, "Global In(주)", PageContext.APPLICATION_SCOPE);
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<ul>
	<li><%=pageContext.getAttribute("pageAttribute") %></li>
	<li><%=request.getAttribute("requestAttribute") %></li>
	<li><%=session.getAttribute("sessionAttribute") %></li>
	<li><%=application.getAttribute("applicationAttribute") %></li>
</ul>
<%-- 
	<jsp:useBean id="빈 이름" scope="유효 범위" class="빈의 저장 위치"/>
		id: 객체를 식별하는 이름(인스턴스)
		scope : 객체의 참조 유효성을 의미함(기본 범위 : page)
		class : 완전한 형태의 클래스 이름
 --%>
</body>
</html>