function writeSave(){
	if(document.writeForm.writer.value==""){
		alert("작성자를 입력하십시요.");
		document.writeForm.writer.focus();
		return false;
	}
	
	if(document.writeForm.email.value==""){
		alert("이메일을 입력하십시요.");
		document.writeForm.email.focus();
		return false;
	}
	
	if(document.writeForm.subject.value==""){
		alert("제목을 입력하십시요.");
		document.writeForm.subject.focus();
		return false;
	}
	
	if(document.writeForm.content.value==""){
		alert("내용을 입력하십시요.");
		document.writeForm.content.focus();
		return false;
	}
	
	if(document.writeForm.pass.value==""){
		alert("비밀번호를 입력하십시요.");
		document.writeForm.pass.focus();
		return false;
	}
	
}
function searchSave() {
	if(document.search.searchText.value==""){
		alert("검색어를 입력하십시요.");
		document.search.searchText.focus();
		return false;
	}
	
}
function loginIdCheck(){
	var loginID = '<%=(String)session.getAttribute("loginID")%>';
	
	if(loginID == 'null'){
		alert('로그인이 필요한 기능입니다.');
 		location.href = '../memberone/login.jsp';
 		return true;
 	}else{
	 	location.href = '../boardone/writeForm.jsp';
 		return true;
 	}
	
}