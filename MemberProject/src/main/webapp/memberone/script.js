function idCheck(id){
   if(id == ""){
      alert("아이디를 입력해주세요......");
      document.regForm.id.focus();
   }
   else{
      url="idCheck.jsp?id="+id;
      window.open(url,"post","width=300,height=150");
      
   }
}

function zipCheck(){
	url="zipCheck.jsp?check=y";
	window.open(url,"post","toolbar=no, width=500, height=300, directories=no, status=yes, scrollbars=yes, menubar=no");
}

function dongCheck(){
	
	if(document.zipForm.dong.value==""){
		alert("동 이름을 입력하세요.");
		document.zipFrom.dong.focus();
		return;
	}
	document.zipForm.submit();
}

function sendAddress(zipcode, sido, gugun, dong, ri, bunji){
	var address = sido+" "+gugun+" "+dong+" "+ri+" "+bunji;
	opener.document.regForm.zipcode.value=zipcode;
	opener.document.regForm.address1.value=address;
	self.close();
}