package com.comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CommentDAO {
	private Connection conn;	//db에 접근하는 객체
	private ResultSet rs;
	
	public CommentDAO() {
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
	public String getDate() {
		String SQL = "SELECT SYSDATE FROM DUAL";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //데이터베이스 오류
	}
	public int getNext() {
		String SQL = "SELECT commentID FROM commentDB ORDER BY commentID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 1; //첫번째 댓글인 경우
	}
	public int write(int num, String userID, String commentText, String userName, String imageUID) {
		String SQL = "INSERT INTO commentDB VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setInt(2, num);
			pstmt.setString(3, userID);
			pstmt.setString(4, userName);
			pstmt.setString(5, getDate());
			pstmt.setString(6, commentText);
			pstmt.setInt(7, 1);
			pstmt.setString(8, imageUID);
			pstmt.executeUpdate();
			return getNext();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	public String getUpdateComment(int commentID) {
		String SQL = "SELECT commentText FROM commentDB WHERE commentID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, commentID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //오류
	}
	public ArrayList<CommentVO> getList(int num){
		String SQL = "SELECT * FROM commentDB WHERE num= ? AND commentAvailable = 1 ORDER BY commentID"; 
		ArrayList<CommentVO> list = new ArrayList<CommentVO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentVO cmt = new CommentVO();
				cmt.setCommentID(rs.getInt(1));
				cmt.setNum(rs.getInt(2));
				cmt.setUserID(rs.getString(3));
				cmt.setUserName(rs.getString(4));
				cmt.setCommentDate(rs.getString(5));
				cmt.setCommentText(rs.getString(6));
				cmt.setCommentAvilable(rs.getInt(7));
				cmt.setImageUID(rs.getString(8));
				list.add(cmt);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //데이터베이스 오류
	}
	
	public int update(int commentID, String commentText, String imageUID) {
		String SQL = "UPDATE commentDB SET commentText = ?, imageUID = ? WHERE commentID LIKE ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, commentText);
			pstmt.setString(2, imageUID);
			pstmt.setInt(3, commentID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	public CommentVO getComment(int commentID) {
		String SQL = "SELECT * FROM commentDB WHERE commentID = ? ORDER BY commentID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  commentID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentVO cmt = new CommentVO();
				cmt.setCommentID(rs.getInt(1));
				cmt.setNum(rs.getInt(2));
				cmt.setUserID(rs.getString(3));
				cmt.setUserName(rs.getString(4));
				cmt.setCommentDate(rs.getString(5));
				cmt.setCommentText(rs.getString(6));
				cmt.setCommentAvilable(rs.getInt(7));
				cmt.setImageUID(rs.getString(8));
				return cmt;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int delete(int commentID) {
		String SQL = "DELETE FROM commentDB WHERE commentID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, commentID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	public int countComment(int commentNum) {
		
		int cmt = 0;
		
		String SQL = "select count(*) from commentDB where num = ?";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, commentNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cmt = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cmt;
	}
}