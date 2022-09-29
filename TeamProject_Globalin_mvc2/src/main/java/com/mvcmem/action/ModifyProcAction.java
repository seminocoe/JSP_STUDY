package com.mvcmem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvcmem.control.ActionForward;
import com.mvcmem.model.MemberDAO;
import com.mvcmem.model.MemberVO;

public class ModifyProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("utf-8");
        
		HttpSession session = request.getSession();
		String loginID = (String)session.getAttribute("loginID");
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberVO vo = new MemberVO(
				loginID,
				request.getParameter("pass"),
				request.getParameter("nickname"),
				request.getParameter("name"),
				request.getParameter("phone1"),
				request.getParameter("phone2"),
				request.getParameter("phone3"),
				request.getParameter("email"));
		
		       dao.updateMember(vo);
		
		     return new ActionForward("/mvcmem/modifyProc.jsp", false);
	}

}
