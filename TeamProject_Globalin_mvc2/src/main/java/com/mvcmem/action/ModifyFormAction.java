package com.mvcmem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvcmem.control.ActionForward;
import com.mvcmem.model.MemberDAO;
import com.mvcmem.model.MemberVO;

public class ModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		MemberDAO dao = MemberDAO.getInstance();
		HttpSession session = request.getSession();
		
		
		String loginID = (String)session.getAttribute("loginID");
		
		MemberVO vo = dao.getMember(loginID);
		
		request.setAttribute("id", vo.getId());
		request.setAttribute("nickname", vo.getNickname());
		request.setAttribute("pass", vo.getPass());
		request.setAttribute("name", vo.getName());
		request.setAttribute("phone1", vo.getPhone1());
		request.setAttribute("phone2", vo.getPhone2());
		request.setAttribute("phone3", vo.getPhone3());
		request.setAttribute("email", vo.getEmail());
		
		
		return new ActionForward("/mvcmem/modifyForm.jsp", false);
	}

}
