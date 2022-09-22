package com.board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.model.CommentDAO;
import com.comment.model.CommentVO;

public class CommentDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String userID = null;
		if (request.getSession().getAttribute("loginID") != null) {//유저아이디이름으로 세션이 존재하는 회원들은 
			userID = (String) request.getSession().getAttribute("loginID");//유저아이디에 해당 세션값을 넣어준다.
		}
		
		int num = 0;
		if (request.getParameter("num") != null){
			num = Integer.parseInt(request.getParameter("num"));
		}
		
		//댓글이 유효한지 판별
				int commentID = 0;
				if (request.getParameter("commentID") != null) {
					commentID = Integer.parseInt(request.getParameter("commentID"));
				}
		
				CommentVO comment = new CommentDAO().getComment(commentID);
				CommentDAO commentDAO = new CommentDAO();
				int result = commentDAO.delete(commentID);
				
				String real = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TestProject2\\board\\img\\uploadImg";
				/* String commentReal = "C:\\Users\\SUCCU\\Desktop\\JSP_STUDY\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject\\boardone\\img\\uploadImg"; */
				if(result != -1) {
					File delFile = new File(real+"\\"+comment.getImageUID()+".jpg");
					if(delFile.exists()){
						delFile.delete();
					}
				}
				
				request.setAttribute("result", result);
				request.setAttribute("num", num);
				request.setAttribute("commentID", commentID);
				request.setAttribute("comment", comment);
				
		return "/board/commentDeleteAction.jsp";
	}

}
