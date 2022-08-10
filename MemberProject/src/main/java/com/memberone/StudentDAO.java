package com.memberone;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;


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
      return result;//end idCheck
   }
   
}