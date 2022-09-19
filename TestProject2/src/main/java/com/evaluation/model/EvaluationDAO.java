package com.evaluation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EvaluationDAO {

	private Connection conn;	//db에 접근하는 객체
	private ResultSet rs;
	
	public EvaluationDAO() {
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
			String dbID = "scott";
			String dbPassword = "tiger";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int write(int num, String userID,int gechu, int bichu) {
		String SQL = "INSERT INTO evaluation VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			pstmt.setString(2, userID);
			pstmt.setInt(3, gechu);
			pstmt.setInt(4, bichu);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	public ArrayList<EvaluationVO> whereList(int num, String userID){
		String SQL = "SELECT * FROM evaluation WHERE num = ? AND userID =?"; 
		ArrayList<EvaluationVO> list = new ArrayList<EvaluationVO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			pstmt.setString(2, userID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvaluationVO eva = new EvaluationVO();
				eva.setNum(rs.getInt(1));
				eva.setUserID(rs.getString(2));
				eva.setGechu(rs.getInt(3));
				eva.setBichu(rs.getInt(4));
				list.add(eva);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //데이터베이스 오류
	}
	
	public ArrayList<EvaluationVO> getList(int num){
		String SQL = "SELECT * FROM evaluation WHERE num = ?"; 
		ArrayList<EvaluationVO> list = new ArrayList<EvaluationVO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvaluationVO eva = new EvaluationVO();
				eva.setNum(rs.getInt(1));
				eva.setUserID(rs.getString(2));
				eva.setGechu(rs.getInt(3));
				eva.setBichu(rs.getInt(4));
				list.add(eva);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //데이터베이스 오류
	}
	
	public int update(int num, String userID,int gechu, int bichu) {
		String SQL = "UPDATE evaluation SET gechu=?, bichu=? WHERE num = ? AND userID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, gechu);
			pstmt.setInt(2, bichu);
			pstmt.setInt(3, num);
			pstmt.setString(4, userID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	public int delete(int num, String userID) {
		String SQL = "DELETE FROM evaluation WHERE num = ? AND userID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			pstmt.setString(2, userID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	public int countGechu(int num) {
		
		int cnt = 0;
		
		String SQL = "select count(gechu) from evaluation where num = ? and gechu = 1";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}