package com.mvcmem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.action.CommandAction;
import com.mvcmem.model.StudentDAO;

public class LoginProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		StudentDAO dao = StudentDAO.getInstance();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		int check = dao.loginCheck(id, pass);
		
		if(check == 1) {
			
			HttpSession session = request.getSession();
			session.setAttribute("loginID", id);
			
		}else if(id != null && pass != null) {
			request.setAttribute("check", check);
		}
		
		return "/board/mem/loginProc.jsp";
	}

}
