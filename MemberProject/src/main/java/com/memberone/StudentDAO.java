package com.memberone;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

public class StudentDAO {
   
   DataSource ds;

   private Connection getConnection() {
      
      Connection conn = null;
      
      try {
         
         Context initContext = new InitialContext();

          ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/myoracle");
          
          conn = ds.getConnection();
         
         
      }catch(Exception e) {
         
         System.out.println("Connection 생성 실패....");
         
      }
      
      return conn;
      
   }//디비 연결
   
   //아이디 체크
   public boolean idCheck(String id) {
      
      boolean result = true;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         
         conn = getConnection();
         
         pstmt = conn.prepareStatement("select * from student where id=?");
         
         pstmt.setString(1, id);
         
         rs = pstmt.executeQuery();
         if(!rs.next()) result = false;
         
      }catch(SQLException s1){
         s1.printStackTrace();
         
      }finally {
         if(rs != null)try{rs.close();}catch(SQLException sq1){}
          if(conn != null)try{conn.close();}catch(SQLException sq2){}
          if(pstmt != null)try{pstmt.close();}catch(SQLException sq3){}
      }
      return result;
   }//end idCheck
   
   //우편번호를 데이터베이스에서 검색해서 Vector에 저장한 후 리턴해 주는 메소드 구현
   public Vector<ZipCodeVO> zipcodeRead(String dong){
	   
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   Vector<ZipCodeVO>vecList = new Vector<ZipCodeVO>();
	   
	   try {
		
		   //디비 연결
		   conn = getConnection();
		   
		   //쿼리 작성
		   String strQuery = "select * from zipcode where dong like '"+dong+"%'";
		   
		   pstmt = conn.prepareStatement(strQuery);
		   rs = pstmt.executeQuery();
		   
		   while(rs.next()) {
			   
			   ZipCodeVO tempZipCode = new ZipCodeVO();
			   tempZipCode.setZipcode(rs.getString("zipcode"));
			   tempZipCode.setSido(rs.getString("sido"));
			   tempZipCode.setGugun(rs.getString("gugun"));
			   tempZipCode.setDong(rs.getString("dong"));
			   tempZipCode.setRi(rs.getString("ri"));
			   tempZipCode.setBunji(rs.getString("bunji"));
			   
			   vecList.addElement(tempZipCode);
		   }
		   
	   }catch(SQLException s1){
	         s1.printStackTrace();
	         
	      }finally {
	         if(rs != null)try{rs.close();}catch(SQLException sq1){}
	          if(conn != null)try{conn.close();}catch(SQLException sq2){}
	          if(pstmt != null)try{pstmt.close();}catch(SQLException sq3){}
	      }
	   return vecList;
   }//end zipcodeRead
   
   /*
    * 실제로 데이터베이스에 회원데이터를 저장하기 위한 기능을 구현
    */
   
   public boolean memberInsert(StudentVO vo) {
	   
	   boolean flag = false;
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   try {
			
		   conn = getConnection();
		   
		   String strQuery ="insert into student values(?,?,?,?,?,?,?,?,?,?)";
		   
		   pstmt = conn.prepareStatement(strQuery);
		   
		   pstmt.setString(1, vo.getId());
		   pstmt.setString(2, vo.getPass());
		   pstmt.setString(3, vo.getName());
		   pstmt.setString(4, vo.getPhone1());
		   pstmt.setString(5, vo.getPhone2());
		   pstmt.setString(6, vo.getPhone3());
		   pstmt.setString(7, vo.getEmail());
		   pstmt.setString(8, vo.getZipcode());
		   pstmt.setString(9, vo.getAddress1());
		   pstmt.setString(10, vo.getAddress2());
		   
		   int count = pstmt.executeUpdate();
		   if(count > 0)flag= true;

				   
		}catch(SQLException s1){
	        s1.printStackTrace();
	        
	    }catch(Exception e){
		        e.printStackTrace();
		        
		}finally {
	        if(rs != null)try{rs.close();}catch(SQLException sq1){}
	        if(conn != null)try{conn.close();}catch(SQLException sq2){}
	        if(pstmt != null)try{pstmt.close();}catch(SQLException sq3){}
	    }
		   return flag;
	   }
}