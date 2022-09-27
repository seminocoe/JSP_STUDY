package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String imageUID = request.getParameter("imageUID");
				
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("imageUID", imageUID);
		
		return "/board/deleteForm.jsp";
	}

}
