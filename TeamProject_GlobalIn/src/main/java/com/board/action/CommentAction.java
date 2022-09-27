package com.board.action;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.model.CommentDAO;
import com.comment.model.CommentVO;
import com.mvcmem.model.StudentDAO;
import com.mvcmem.model.StudentVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CommentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		StudentDAO dao  = StudentDAO.getInstance();
		CommentVO comment  = new CommentVO();
		
		String userID = null;
	 	String userName = "";
	 	if(request.getSession().getAttribute("loginID") != null){
	 		userID = (String) request.getSession().getAttribute("loginID");
	 		StudentVO vo = dao.getMember(userID);
			userName = vo.getName();
	 	}
	 	
	 	
		String realFolder="";
		String saveFolder = "board/img/uploadImg";
		String encType = "utf-8";
		int maxSize=5*1024*1024;
		
		ServletContext context = request.getSession().getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = null;
		
		multi = new MultipartRequest(request,realFolder,maxSize,encType,new DefaultFileRenamePolicy());		
		String fileName = multi.getFilesystemName("fileName");
		String commentText = multi.getParameter("commentText");
		comment.setCommentText(commentText);
	 	
	 	UUID uid = null;
	 	String uidName = null;
	 	if(fileName != null){
	 		uidName = UUID.randomUUID().toString();
	 	}
	 	
	 	
	 	int num = 0; 
	 	if (request.getParameter("num") != null){
	 		num = Integer.parseInt(request.getParameter("num"));
	 	}
	 	
	 	int commentID = -1;
	 	
	 	if(userID != null) {
	 	CommentDAO commentDAO = new CommentDAO();
 		commentID = commentDAO.write(num, userID, comment.getCommentText(), userName, uidName);
	 	}
	 	
		if( commentID != -1) {
	 		if(fileName != null){
				File oldFile = new File(realFolder+"\\"+fileName);
				File newFile = new File(realFolder+"\\"+uidName+".jpg");
				oldFile.renameTo(newFile);
	 		}
		}
 		
		request.setAttribute("num", num);
		request.setAttribute("commentID", commentID);
		
		return "/board/commentAction.jsp";
	}

}
