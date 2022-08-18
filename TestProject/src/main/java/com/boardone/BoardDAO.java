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
					
					strQuery = "update board set writer=?, email=?,subject=?,content=? where num=?";
					pstmt = conn.prepareStatement(strQuery);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getEmail());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getContent());
					pstmt.setInt(5, article.getNum());
					
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
	
	public ArrayList<BoardVO> getSearch(String searchField, String searchText){//특정한 리스트를 받아서 반환
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	    ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	    String SQL ="select * from board where "+searchField.trim();
	      
	    try {
	    	
	    	conn = ConnUtil.getConnection();
			 	        
			 SQL +=" LIKE '%"+searchText.trim()+"%' order by num desc"; 
	            
	        pstmt=conn.prepareStatement(SQL);
	        rs=pstmt.executeQuery();//select
	        
	        while(rs.next()) {
				BoardVO article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				/* article.setEmail(rs.getString("email")); */
				article.setSubject(rs.getString("subject"));
				/* article.setPass(rs.getString("pass")); */
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
				list.add(article);
	        }         
	    } catch(Exception e) {
	         e.printStackTrace();
	    }
	    return list;//게시글 리스트 반환
	}//end getSearch
}
