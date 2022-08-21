package com.logon;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class LoginDBBean {

	private static LoginDBBean instance = new LoginDBBean();

	public static LoginDBBean getInstance() {
		return instance;
	}
	
	private LoginDBBean() { }
	
	private Connection getConnection() throws Exception{
		
		
		Context InitialContext = new InitialContext();
		DataSource ds = (DataSource) InitialContext.lookup("java:/comp/env/jdbc/myoracle");
		return ds.getConnection();
	}

	
//사용자 인증(아이디, 비밀번호)
	
	public int userCheck(String id, String passwd) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		
		int check = -1;

		try {

			conn = getConnection();

			String strQuery = "select passwd from tempmember where id = ?";
			pstmt = conn.prepareStatement(strQuery);
			
			pstmt.setString(1, id);
			

			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbpasswd = rs.getString("passwd");
				if (dbpasswd.equals(passwd))
					check = 1;
				else
					check = 0;
			}else {
				check = -1;
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
	}
	
	
}