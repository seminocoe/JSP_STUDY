package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.model.CommentDAO;
import com.comment.model.CommentVO;

public class CommentUpdateProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String pageNum = ("1");
		
		int commentID = 0;
		if (request.getParameter("commentID") != null){
			commentID = Integer.parseInt(request.getParameter("commentID"));
		}
		int num = 0;
		if (request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num"));
		}
		String commentText = null;
		if (request.getParameter("commentText")!=null){
			commentText = request.getParameter("commentText");
		}
		CommentVO comment = new CommentDAO().getComment(commentID);
		CommentDAO commentDAO = new CommentDAO();
		int result = commentDAO.update(commentID, commentText);
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("commentID", commentID);
		request.setAttribute("commentText", commentText);
		request.setAttribute("result", result);
		
		return "/board/commentUpdateProc.jsp";
	}

}
