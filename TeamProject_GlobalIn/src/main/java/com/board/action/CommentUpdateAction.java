package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.model.CommentDAO;
import com.comment.model.CommentVO;

public class CommentUpdateAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = 0;
		if (request.getParameter("num") != null){
			num = Integer.parseInt(request.getParameter("num"));
		}
		int commentID = 0;
		if (request.getParameter("commentID") != null){
			commentID = Integer.parseInt(request.getParameter("commentID"));
		}
		CommentDAO commentDAO = new CommentDAO();
		CommentVO commentVO = commentDAO.getComment(commentID);//
		String commentText = commentDAO.getUpdateComment(commentID);
		
		String imageUID = commentVO.getImageUID();//
		
		request.setAttribute("num", num);
		request.setAttribute("commentID", commentID);
		request.setAttribute("commentText", commentText);
		
		request.setAttribute("imageUID", imageUID);//
		
		return "/board/commentUpdate.jsp";
	}

}
