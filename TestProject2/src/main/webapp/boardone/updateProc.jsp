<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.boardone.BoardDAO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.UUID" %>

<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>

<% request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="article" class="com.boardone.BoardVO" scope="page">
	<jsp:setProperty name = "article" property="*" />
</jsp:useBean>

<%

String realFolder="";
String saveFolder = "boardone/img/uploadImg";		//사진을 저장할 경로
String encType = "utf-8";				//변환형식
int maxSize=20*1024*1024;				//사진의 size 20메가
		
ServletContext context = this.getServletContext();		//절대경로를 얻는다
realFolder = context.getRealPath(saveFolder);			//saveFolder의 절대경로를 얻음
		
MultipartRequest multi = null;

//파일업로드를 직접적으로 담당		
multi = new MultipartRequest(request,realFolder,maxSize,encType,new DefaultFileRenamePolicy());

 //form으로 전달받은걸 가져온다(이름,메일,제목,내용,비밀번호,파일? 등)
 
String fileName = multi.getFilesystemName("fileName");
String writer = multi.getParameter("writer");
String email = multi.getParameter("email");
String subject = multi.getParameter("subject");
String content = multi.getParameter("content");
String pass = multi.getParameter("pass");
int num = Integer.parseInt(multi.getParameter("num"));
String imageUID = multi.getParameter("imageUID");
UUID uid = null;
String uidName = null;
if(fileName != null){
	uidName = UUID.randomUUID().toString();
}

article.setWriter(writer);
article.setEmail(email);
article.setSubject(subject);
article.setContent(content);
article.setPass(pass);
article.setNum(num);
article.setImageUID(uidName);

	String pageNum = request.getParameter("pageNum");
	BoardDAO dbPro = BoardDAO.getInstance();
	
	int check = dbPro.updateArticle(article);
	
	if(check == 1){
		if(fileName != null){
			String real = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject_GlobalIn\\boardone\\img\\uploadImg";
			File delFile = new File(real+"\\"+imageUID+".jpg");
			if(delFile.exists()){
				delFile.delete();
			}
			File oldFile = new File(realFolder+"\\"+fileName);
			File newFile = new File(realFolder+"\\"+uidName+".jpg");
			oldFile.renameTo(newFile);
		}else{
			String real = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject_GlobalIn\\boardone\\img\\uploadImg";
			File delFile = new File(real+"\\"+imageUID+".jpg");
			if(delFile.exists()){
				delFile.delete();
			}
		}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta http-equiv="Refresh" content="0;url=list.jsp?pageNum=<%=pageNum%>">
</head>
<body>
<%}else{%>
	<script type="text/javascript">
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);//이전페이지로 가는건데 or history.back();
	</script>
<%}%>
</body>
</html>