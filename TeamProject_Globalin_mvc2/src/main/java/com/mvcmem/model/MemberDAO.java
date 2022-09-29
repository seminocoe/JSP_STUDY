package com.mvcmem.model;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

public class MemberDAO {
	
	private static MemberDAO instance = null;
	private MemberDAO() {}
	
    public static MemberDAO getInstance() {
		
		if(instance == null) {
			synchronized (MemberDAO.class) {
				instance = new MemberDAO();
			}
		}
		return instance;
	}
	
	
	private Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/myoracle");
			conn = ds.getConnection();
			 
		}catch (NamingException e) {
			System.out.println("Connection 생성 실패 !!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("Connection 생성 실패 !!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}
	    return conn;
	}// 디비 연결
	
	
	// 아이디 체크
	public boolean idCheck(String id) {
		
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select * from member where id=?");
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(!rs.next()) result = false;
			
		}catch(SQLException s1) {
			s1.printStackTrace();
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
	   		if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
	   		if(conn != null) try{conn.close();}catch(SQLException s3){}
		}
		return result;
	}// end idCheck
	
	
	/*
	 * 실제로 데이터베이스에 회원데이터를 저장하기 위한 기능을 구현
	 */
	
	public boolean memberInsert(MemberVO vo) {
		
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String strQuery ="insert into member values(?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(strQuery);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getNickname());
			pstmt.setString(3, vo.getPass());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getPhone1());
			pstmt.setString(6, vo.getPhone2());
			pstmt.setString(7, vo.getPhone3());
			pstmt.setString(8, vo.getEmail());
			
			
			int count = pstmt.executeUpdate();
			if(count > 0) flag= true;
			
		}catch(SQLException s1) {
			s1.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
	   		if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
	   		if(conn != null) try{conn.close();}catch(SQLException s3){}
		}
		
		return flag;
	}// end memberInsert
	
	/*
	 * 로그인 버튼을 클릭하면 입력한 아이디와 비밀번호를 데이터베이스에 저장되어 있는 
	 * 아이디와 비밀번호를 비교해서 같으면 로그인 성공, 다르면 실패처리를 해야함
	 *  데이터베이스에서 아이디와 비밀번호를 비교하여 그 결과를 정수형으로 리턴해주는 메소드를 구현
	 *   1: 로그인성공, 0: 비밀번호 오류, -1: 아이디 없음
	 */
	public int loginCheck(String id, String pass) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int check = -1;// 아이디 없음
		
		try {
			conn = getConnection();
			
			String strQuery ="select pass from member where id=?";
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbPass = rs.getString("pass");
				if(pass.equals(dbPass)) check = 1;
				else check=0;
			}
			
		}catch(SQLException s1) {
			s1.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
	   		if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
	   		if(conn != null) try{conn.close();}catch(SQLException s3){}
		}
		return check;
		
	}// end loginCheck
	
	public MemberVO getMember(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo = null;
		
		try {
			
			conn = getConnection();
			String strQuery ="select * from member where id=?";
			
			pstmt = conn.prepareStatement(strQuery);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {// 아이디에 해당하는 회원이 존재한다면
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setEmail(rs.getString("email"));
				
			}
		
		}catch(SQLException s1) {
			s1.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
	   		if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
	   		if(conn != null) try{conn.close();}catch(SQLException s3){}
		}
		
		return vo;
		
	}
	
	// 정보수정 버튼을 클릭했을 경우 데이터베이스에 update를 수행해야함
	// 정보수정 처리를 할 수 있는 메소드 구현
	public void updateMember(MemberVO vo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			
			conn = getConnection();
			String strQuery ="update member set  nickname=?, pass=?, phone1=?, phone2=?, "
					+ "phone3=?, email=?  where id=?";
			
			pstmt = conn.prepareStatement(strQuery);
			
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getNickname());
			pstmt.setString(3, vo.getPhone1());
			pstmt.setString(4, vo.getPhone2());
			pstmt.setString(5, vo.getPhone3());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getId());
		
			pstmt.executeUpdate();		
			
		
		}catch(SQLException s1) {
			s1.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
	   		if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
	   		if(conn != null) try{conn.close();}catch(SQLException s3){}
		}
		
	}// end  updateMember
	
	/*
	 *   회원탈퇴 버튼을 클릭하면 데이터베이스에 회원데이터가 삭제되어야함
	 *    데이터베이스에서 삭제처리해줄 메소드 구현
	 */
	
	public int deleteMember(String id, String pass) {
		  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		String dbPass = "";//데이터베이스 저장된 비밀번호를 의미함
	  
		int result = -1;
		
		try {
			
			conn = getConnection();
			
			String strQuery ="select pass from member where id=?";
			 
			pstmt = conn.prepareStatement(strQuery);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dbPass = rs.getString("pass");
				
				if(dbPass.equals(pass)) {// 본인 확인 ==> true 
					pstmt = conn.prepareStatement("delete from member where id=?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					result = 1;//회원탙퇴 성공
				} else {// 비밀번호 오류 --> 본인 확인 실패
					result =0;
				}
			}
			
		}catch(SQLException s1) {
			s1.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
	   		if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
	   		if(conn != null) try{conn.close();}catch(SQLException s3){}
		}
		return result;
	  }
}
