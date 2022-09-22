package com.board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
	    
		    int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");
			String pass = request.getParameter("pass");
			BoardDAO dbPro = BoardDAO.getInstance();
			String imageUID = request.getParameter("imageUID");
			int check = dbPro.deleteArticle(num, pass);
			if(check == 1){
				if(imageUID != null){
					String real = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TestProject2\\board\\img\\uploadImg";
					File delFile = new File(real+"\\"+imageUID+".jpg");
					if(delFile.exists()){
						delFile.delete();
					}
				}
			}
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("check", check);
			
		return "/board/deleteProc.jsp";
	}

}
