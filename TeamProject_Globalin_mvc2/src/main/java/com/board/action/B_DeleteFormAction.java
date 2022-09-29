package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcmem.action.Action;
import com.mvcmem.control.ActionForward;

public class B_DeleteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String imageUID = request.getParameter("imageUID");
				
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("imageUID", imageUID);
		
		return new ActionForward("/board/deleteForm.jsp", false);
	}
	
}
