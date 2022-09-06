package com.board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;
//글 목록을 처리하는 클래스
public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		

		String pageNum = request.getParameter("pageNum");

		if(pageNum == null){ //현재 페이지는 무조건 1
			pageNum = "1";
		}

		//한 페이지에 보여줄 글 목록 수 지정
		int pageSize = 5;
		
		int currentPage = Integer.parseInt(pageNum);

		int startRow = (currentPage-1)*pageSize + 1; //공식
		int endRow = currentPage*pageSize;


		int count = 0;
		int number = 0;
		List<BoardVO> articleList = null;
		BoardDAO dbPro = BoardDAO.getInstance();

		count = dbPro.getArticleCount();
		
		if(count > 0){//현재 페이지에 해당 하는 글의 목록
			//전체 글 수가 하나라도 존재하면 리스트를 출력
			articleList = dbPro.getArticles(startRow, endRow);
		}else{//글이 없을 시?인가?
				articleList = Collections.emptyList();
		}
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
		
		//해당 뷰로 반환 해줌
		return "/board/list.jsp";
	}

}
