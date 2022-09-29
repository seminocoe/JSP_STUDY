package com.mvcmem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcmem.control.ActionForward;
import com.mvcmem.model.MemberDAO;
import com.mvcmem.model.MemberVO;

public class RegProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	
		request.setCharacterEncoding("utf-8");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberVO vo = new MemberVO(
				request.getParameter("id"),
				request.getParameter("nickname"),
				request.getParameter("pass"),
				request.getParameter("name"),
				request.getParameter("phone1"),
				request.getParameter("phone2"),
				request.getParameter("phone3"),
				request.getParameter("email"));
		
		boolean flag = dao.memberInsert(vo);
		request.setAttribute("flag", flag);
		return new ActionForward("/mvcmem/regProc.jsp", false);
	}

}
