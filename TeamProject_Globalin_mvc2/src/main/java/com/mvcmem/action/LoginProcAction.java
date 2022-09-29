package com.mvcmem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvcmem.control.ActionForward;
import com.mvcmem.model.MemberDAO;
public class LoginProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		MemberDAO dao = MemberDAO.getInstance();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		int check = dao.loginCheck(id, pass);
		
		if(check == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("loginID", id);
		}else if(id != null && pass != null) {
		
			request.setAttribute("check", check);
		}
		
		return new ActionForward("member.mdo?cmd=login", false);
	}

}
