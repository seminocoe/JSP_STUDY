package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;
import com.mvcmem.action.Action;
import com.mvcmem.control.ActionForward;

public class UpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

			BoardDAO dbPro = BoardDAO.getInstance();
			BoardVO article = dbPro.updateGetArticle(num);

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("article", article);
		
		return new ActionForward("/board/updateForm.jsp", false);
	}
	
}
