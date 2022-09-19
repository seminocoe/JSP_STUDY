package com.mvcmem.model;

import java.sql.*;
import javax.sql.*;

import javax.naming.*;
import java.util.*;

public class StudentDAO {

	private static StudentDAO instance = null;
	
	private StudentDAO() {	}
	
	public static StudentDAO getInstance() {
		
		if(instance == null) {
			synchronized (StudentDAO.class) {
				instance = new StudentDAO();
			}
		}
		return instance;
	}
	
	private Connection getConnection() {
		
		Connection conn = null;

		try {

			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/myoracle");
			conn = ds.getConnection();

		} catch (NamingException e) {
			System.out.println("Connection 생성 실패....");
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.println("Connection 생성 실패....");
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

			pstmt = conn.prepareStatement("select * from student where id=?");

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (!rs.next())
				result = false;

		} catch (SQLException s1) {
			s1.printStackTrace();

		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		return result;
	}// end idCheck

	// 우편번호를 데이터베이스에서 검색해서 Vector에 저장한 후 리턴해 주는 메소드 구현
	public Vector<ZipCodeVO> zipcodeRead(String dong) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();

		try {

			// 디비 연결
			conn = getConnection();

			// 쿼리 작성
			String strQuery = "select * from zipcode where dong like '" + dong + "%'";

			pstmt = conn.prepareStatement(strQuery);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ZipCodeVO tempZipCode = new ZipCodeVO();
				tempZipCode.setZipcode(rs.getString("zipcode"));
				tempZipCode.setSido(rs.getString("sido"));
				tempZipCode.setGugun(rs.getString("gugun"));
				tempZipCode.setDong(rs.getString("dong"));
				tempZipCode.setRi(rs.getString("ri"));
				tempZipCode.setBunji(rs.getString("bunji"));

				vecList.addElement(tempZipCode);
			}

		} catch (SQLException s1) {
			s1.printStackTrace();

		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		return vecList;
	}// end zipcodeRead

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

			String strQuery = "insert into student values(?,?,?,?,?,?,?,?,?,?)";

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
			if (count > 0)
				flag = true;

		} catch (SQLException s1) {
			s1.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		return flag;
	}// end memberInsert

	/*
	 * 로그인 버튼을 클릭하면 입력한 아이디와 비밀번호를 데이터베이스에 저장되어 있는 아이디와 비밀번호를 비교해서 같으면 로그인 성공, 다르면
	 * 실패처리를 해야함
	 * 
	 * 데이터베이스에서 아이디와 비밀번호를 비교하여 그 결과를 정수형으로 리턴해주는 메소드를 구현 1:로그인 성공 0:비밀번호 오류 -1:아이디
	 * 없음
	 */
	public int loginCheck(String id, String pass) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int check = -1;

		try {

			conn = getConnection();

			String strQuery = "select pass from student where id = ?";
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPass = rs.getString("pass");
				if (pass.equals(dbPass))
					check = 1;
				else
					check = 0;
			}

		} catch (SQLException s1) {
			s1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}

		return check;
	}// end loginCheck

	public StudentVO getMember(String id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO vo = null;

		try {

			conn = getConnection();

			String strQuery = "select * from student where id = ?";
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {// 아이디에 해당하는 회원이 존재한다면
				vo = new StudentVO();
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setEmail(rs.getString("email"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress1(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
			}

		} catch (SQLException s1) {
			s1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		return vo;
	}// end getMember

	// 정보수정 버튼을 클릭했을 경우 데이터베이스에 update를 수정해야함
	// 정보수정 처리를 할 수 있는 메소드 구현
	public void updateMember(StudentVO vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConnection();
			String strQuery = "update student set pass = ?, phone1 = ?, phone2 = ?, phone3 = ?, email = ? , zipcode = ?, address1 = ?, address2 = ? where id = ?";

			pstmt = conn.prepareStatement(strQuery);

			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getPhone1());
			pstmt.setString(3, vo.getPhone2());
			pstmt.setString(4, vo.getPhone3());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddress1());
			pstmt.setString(8, vo.getAddress2());
			pstmt.setString(9, vo.getId());

			pstmt.executeUpdate();

		} catch (SQLException s1) {
			s1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}

	}//end memberupdate

	/*
	 * 회원탈퇴 버튼을 클릭하면 데이터베이스에 회원데이터가 삭제되어야함
	 * 데이터베이스에서 삭제처리해줄 메소드 구현
	 */
	
	public int deleteMember(String id, String pass) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPass = "";//데이터베이스에 저장된 비밀번호를 의미함
		
		int result = -1;
		
		try {
			
			conn = getConnection();
			
			String strQuery = "select pass from student where id = ?";
			
			pstmt = conn.prepareStatement(strQuery);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbPass = rs.getString("pass");
				
				if(dbPass.equals(pass)) {//본인확인 => true
					pstmt = conn.prepareStatement("delete from student where id = ?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					result = 1;//회원탈퇴 성공
				}else {//비밀번호 오류 -> 본인확인 실패
					result = 0;
				}
			}
			
		} catch (SQLException s1) {
			s1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		
		return result;
	}//end deleteMember
	
}