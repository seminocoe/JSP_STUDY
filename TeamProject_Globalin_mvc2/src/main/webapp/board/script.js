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

function updateCommentSave() {
	if(document.c_commentUpdate.update.value==""){
		alert("댓글 내용을 입력하십시요.");
		document.c_commentUpdate.update.focus();
		return false;
	}
	
}

function loginIdCheck(loginID){
	
	if(loginID == ""){
		alert('로그인이 필요한 기능입니다.');
 		location.href = 'member.mdo?cmd=login';
 		return true;
 	}else{
	 	location.href = 'member.mdo?cmd=writeForm';
 		return true;
 	}
	
}

function checkText() {
	if(commentInputForm.commentText.value==""){
		alert("댓글을 입력하세요.");
		commentInputForm.commentText.focus();
		return false;
	}else{
		return true;
	}
}

function nwindow(num,commentID){
		window.name = "commentParant";
		var url= "member.mdo?cmd=commentUpdate&num="+num+"&commentID="+commentID;
		window.open(url,"","width=600,height=230,left=300");
}

function check(){
	if(document.find_frm.find_box.value==""){
		alert("검색어를 입력해 주세요");
		return false;
	}
}

function frm_sub(i){
	i_frm.action="member.mdo?cmd=list&pageNum="+i;
	i_frm.submit();
}