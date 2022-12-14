package com.board.action;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;
import com.comment.model.*;
import com.evaluation.model.*;
import com.mvcmem.action.Action;
import com.mvcmem.control.ActionForward;

//글 목록을 처리하는 클래스
public class ListAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null){ //현재 페이지는 무조건 1
			pageNum = "1";
		}

		//한 페이지에 보여줄 글 목록 수 지정
		int pageSize = 5;
		
		//무엇을 검색할지 파라미터 가져와야함(작성자, 제목, 내용)
		String find = request.getParameter("find");
		String find_box = request.getParameter("find_box");
		
		//파라미터 가져와서 한글로 변환처리
		if(find_box != null){
			find_box = new String(find_box.getBytes("utf-8"),"utf-8");
		}
		
		int currentPage = Integer.parseInt(pageNum);

		int startRow = (currentPage-1)*pageSize + 1; //공식
		int endRow = currentPage*pageSize;


		int count = 0;
		int number = 0;
		List<BoardVO> articleList = null;
		BoardDAO dbPro = BoardDAO.getInstance();

		//검색이 아니면 전체 목록을 보여주고, 검색이면 검색한 내용만 보여줌

		if(find_box == null){//검색이 아닌경우
		
			count = dbPro.getArticleCount();
		
			if(count > 0){//현재 페이지에 해당 하는 글의 목록
				//전체 글 수가 하나라도 존재하면 리스트를 출력
				articleList = dbPro.getArticles(startRow, endRow);
			}else{//글이 없을 시?인가?
					articleList = Collections.emptyList();
			}
		}else{//검색일 경우
			
			count = dbPro.getArticleCount(find, find_box);

			if(count > 0){
				//전체 글 수가 하나라도 존재하면 리스트를 출력
				articleList = dbPro.getArticles(find, find_box, startRow, endRow);
			}
			
		}
		
		CommentDAO commentDAO = new CommentDAO();
		EvaluationDAO evaluationDAO = new EvaluationDAO();
		
		//글 목록에 표시할 글 번호
		number = count - (currentPage -1) * pageSize;
		

		//해당 뷰에서 사용할 속성 저장
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
		request.setAttribute("articleList", articleList);
		request.setAttribute("find", find);
		request.setAttribute("find_box", find_box);
		request.setAttribute("commentDAO", commentDAO);
		request.setAttribute("evaluationDAO", evaluationDAO);
		
		return new ActionForward("/board/list.jsp", false);
	}

}
