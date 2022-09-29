<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
<form action="member.mdo?cmd=regProc" method="post" name="regForm">
<table border="1">
     <tr>
        <td colspan="2" align="center">회원 가입 정보 입력</td>
   </tr>
   
   <tr>
        <td align="right"> 아이디 </td>
        <td>
                  <input type="text" name="id">&nbsp;
                  <input type="button" value="중복확인" onclick="idCheck(this.form.id.value)">        
        </td>
   </tr>
   <tr>
        <td align="right"> 닉네임 </td>
        <td>
               <input type="text" name="nickname">&nbsp;
               <input type="button" value="중복확인" onclick="idCheck(this.form.nickname.value)">
        </td>
   </tr>
   
   <tr>
        <td align="right"> 비밀번호 </td>
        <td>
               <input type="password" name="pass">
        </td>
   </tr>
   
   <tr>
        <td align="right"> 비밀번호 확인 </td>
        <td>
               <input type="password" name="repass">
        </td>
   </tr>
   
   <tr>
        <td align="right"> 이름 </td>
        <td>
               <input type="text" name="name">
        </td>
   </tr>
   
   <tr>
        <td align="right"> 전화번호 </td>
        <td>
               <select name="phone1">
                               <option value="02">02</option>
                               <option value="010">010</option>
                               <option value="031">031</option>
                               <option value="032">032</option>
                               <option value="033">033</option>
                               <option value="061">061</option>
               </select>-
               <input type="text" name="phone2" size="5">-
               <input  type="text" name="phone3" size="5">
        </td>
   </tr>
   
   <tr>
        <td align="right"> 이메일 </td>
        <td>
               <input type="text" name="email">
        </td>
   </tr>
   

   <tr>
        <td colspan="2" align="center">
            <input type="submit" value="회원가입" onclick="inputCheck()">&nbsp;&nbsp;
            <!-- <input type="submit" value="회원가입"> -->
            <input type="reset" value="다시입력" >
        </td>
   </tr>
</table>

</form>
</body>
</html>