package com.boardone;

import java.sql.*;
import java.util.*;

public class BoardDAO {

	//이렇게 객체의 직렬화로 디비를 이렇게 연결하면 db가 private 로 되어있어서 보안상으로 좋음
	private static BoardDAO instance = null;
	
	private BoardDAO() {	}
	
	public static BoardDAO getInstance() {
		
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	//이곳에서 부터 게시판 작업 기능을 하나씩 메소드를 추가해서 작성하면 됨
	
	//글을 데이터베이스에 추가하는 메소드
	public void insertArticle(BoardVO article) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = article.getNumber();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		
		int number=0;
		String strQuery = "";
		
		try {
			
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) number = rs.getInt(1)+1;
			else number = 1;

			if(num != 0) {//답변 글일 경우
				
				strQuery ="update board set step=step+1 where ref=? and step > ?";
				
				pstmt = conn.prepareStatement(strQuery);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				
				pstmt.executeUpdate();
				
				step = step +1;
				depth = depth +1;
				
			}else {//새 글인 경우
				ref = number;
				step = 0;
				depth = 0;
			}
			
			strQuery="insert into board(num, writer, email, subject, pass, regdate, ref, step, depth, content, ip)"
					+ " values(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPass());
			pstmt.setTimestamp(5, article.getRegdate());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, step);
			pstmt.setInt(8, depth);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			
			pstmt.executeUpdate();
			
		} catch (SQLException s1) {
			s1.printStackTrace();

		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		
	}//end insertArticle
	
	/*
	 * 글 작성을 수행하고 나면 list.jsp로 리다이렉트 하도록 되어 있음
	 * 따라서 결과를 보려면 list.jsp를 작성해야함
	 * 글 전체 글의 개수를 가져와서 목록을 보여줘야함
	 */
	
	public int getArticleCount() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try {
			
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
			
		} catch (SQLException s1) {
			s1.printStackTrace();

		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		return x;
	}//end getArticleCount
	
	/*
	 *  board 테이블에서 데이터를 가져와서 보여줄 메소드를 추가
	 *  List<BoardVO>
	 */
	
	public List<BoardVO> getArticles(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVO>articleList = null;
		
		try {
			
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from board order by num desc");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				articleList = new ArrayList<BoardVO>();
				
				do {
					BoardVO article = new BoardVO();
					article.setNumber(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setIp(rs.getString("ip"));
					articleList.add(article);
				}while(rs.next());
				
			}
			
		}catch (SQLException s1) {
			s1.printStackTrace();

		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		
		return articleList;
	}//end getArticles
}
