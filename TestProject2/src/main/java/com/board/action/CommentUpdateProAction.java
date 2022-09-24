package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.model.CommentDAO;
import com.comment.model.CommentVO;

public class CommentUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String pageNum = ("1");
		
		String loginID = null;
		if (request.getSession().getAttribute("loginID") != null) {//유저아이디이름으로 세션이 존재하는 회원들은 
			loginID = (String) request.getSession().getAttribute("loginID");//유저아이디에 해당 세션값을 넣어준다.
		}
		
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
		int result = -1;
		
		if(loginID != null && commentText != null){
			result = commentDAO.update(commentID, commentText);
			System.out.println(result);
		}
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("commentID", commentID);
		request.setAttribute("commentText", commentText);
		request.setAttribute("result", result);
		
		return "/board/commentUpdateProc.jsp";
	}

}
