package com.board.model;

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
		
		int num = article.getNum();
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
			
			strQuery="insert into board(num, writer, email, subject, pass, regdate, ref, step, depth, content, ip, imageUID)"
					+ " values(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
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
			pstmt.setString(11, article.getImageUID());
			
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
	 *  
	 *  start:시작번호 end:끝번호
	 */
	
	public List<BoardVO> getArticles(int start, int end){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVO>articleList = null;
		
		try {
			
			conn = ConnUtil.getConnection();
			
			//pstmt = conn.prepareStatement("select * from board order by num desc"); 
				pstmt = conn.prepareStatement("select * from ("
						+"select rownum rnum, num, writer, email, subject, "
						+"pass, regdate, readcount, ref, step, depth, content, ip ,imageUID from("
						+"select * from board order by ref desc, step asc)) "
						+"where rnum >= ? and rnum <= ?");
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				
				articleList = new ArrayList<BoardVO>(end - start + 1);
				
				do {
					BoardVO article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					article.setImageUID(rs.getString("imageUID"));
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
	
	/*
	 * list.jsp 페이지에서 글 제목을 누르면 글 내용을 볼 수 있도록 구현함
	 * 
	 * 글을 num을 매개변수로 해서 하나의 글을 가져와서 보여줌
	 * 상세정보를 데이터베이스에서 가져옴
	 * 
	 * 글 하나의 정보를 가져오는 메소드를 구현해야함
	 */
	
	public BoardVO getArticle(int num){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		
		try {
			
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"update board set readcount=readcount+1 where num=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(
					"select * from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardVO();
				
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
				article.setImageUID(rs.getString("imageUID"));
			}
			
		}catch (SQLException s1) {
			s1.printStackTrace();

		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		return article;
	}//end getArticle
	
	/*
	 * 글 수정 버튼을 누를 경우 updateForm.jsp페이지로 이동하도록 링크를 걸었당
	 * 
	 * 글 수정시 글 목록보기와 다르게 조회수를 증가 시킬필요는 없당
	 * 
	 * 조회수 증가를 제외하고 num에 해당하는 글만 가져오는 메소드를 구현한당
	 */
	
	public BoardVO updateGetArticle(int num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		
		try {
			
			conn = ConnUtil.getConnection();
			
			pstmt = conn.prepareStatement(
					"select * from board where num=?");
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardVO();
				
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
				article.setImageUID(rs.getString("imageUID"));
			}
			
		}catch (SQLException s1) {
			s1.printStackTrace();

		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		return article;
	}//end updateGetArticle
	
	public int updateArticle(BoardVO article) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd= "";
		String strQuery = "";
		int result = -1;
		
		try {
			
			conn = ConnUtil.getConnection();
			
			pstmt = conn.prepareStatement("select pass from board where num=?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("pass");
				
				if(dbpasswd.equals(article.getPass())) {
					
					strQuery = "update board set writer=?, email=?,subject=?,content=?, imageUID=? where num=?";
					pstmt = conn.prepareStatement(strQuery);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getEmail());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getContent());
					pstmt.setString(5, article.getImageUID());
					pstmt.setInt(6, article.getNum());
					
					pstmt.executeUpdate();
					result = 1;//수정성공
				}else{
					result = 0;//수정실패
				}
			}
			
		}catch (SQLException s1) {
			s1.printStackTrace();

		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		return result;
		
	}//end pdateArticle
	
	/*
	 * 글 삭제 처리
	 *  글 삭제 폼에서 비밀번호를 입력하고 삭제를 수행하면 데이터베이스에서 비밀번호를 검색해서
	 *  비밀번호가 일치하면 삭제처리를 수행하고 그렇지 않으면 비밀번호 오류
	 */
	
	public int deleteArticle(int num, String pass) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd= "";

		int result = -1;
		
		try {
			
			conn = ConnUtil.getConnection();
			
			pstmt = conn.prepareStatement("select pass from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("pass");
				
				if(dbpasswd.equals(pass)) {
					
					pstmt = conn.prepareStatement("delete from board where num=?");
					
					pstmt.setInt(1, num); 
					
					pstmt.executeUpdate();
					result = 1;//삭제성공
				}else{
					result = 0;//삭제실패
				}
			}
			
		}catch (SQLException s1) {
			s1.printStackTrace();

		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sq1) {}
			if (conn != null)try {conn.close();} catch (SQLException sq2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sq3) {}
		}
		return result;
	}//end deleteArticle
	
//검색한 내용이 몇개 있는지를 반환하는 기능(what:검색조건, content:검색내용)
public int getArticleCount(String what, String content) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try {
			
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board where "+what+" like '%"+content+"%'");
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


//검색한 내용을 리스트로 받아옴(what:검색조건, content:검색내용,시작번호, 끝번호)시작번호와 끝번호는 페이지처리를 위해
public List<BoardVO> getArticles(String what, String content, int start, int end){
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	List<BoardVO>articleList = null;
	
	try {
		
		conn = ConnUtil.getConnection();
		
		pstmt = conn.prepareStatement("select * from ("
				+"select rownum rnum, num, writer, email, subject, "
				+"pass, regdate, readcount, ref, step, depth, content, ip, imageUID from("
				+"select * from board where "+what+" like '%"+content+"%' order by ref desc, step asc)) "
				+"where rnum >= ? and rnum <= ?");
		
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		rs = pstmt.executeQuery();
		
		
		if(rs.next()) {
			
			articleList = new ArrayList<BoardVO>(end - start + 1);
			
			do {
				BoardVO article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
				article.setImageUID(rs.getString("imageUID"));
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
