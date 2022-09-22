package com.board.action;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String realFolder="";
		String saveFolder = "board/img/uploadImg";		//사진을 저장할 경로
		String encType = "utf-8";				//변환형식
		int maxSize=20*1024*1024;				//사진의 size 20메가
				
		ServletContext context = request.getSession().getServletContext();;		//절대경로를 얻는다
		realFolder = context.getRealPath(saveFolder);			//saveFolder의 절대경로를 얻음
				
		MultipartRequest multi = null;

		//파일업로드를 직접적으로 담당		
		multi = new MultipartRequest(request,realFolder,maxSize,encType,new DefaultFileRenamePolicy());

		 //form으로 전달받은걸 가져온다(이름,메일,제목,내용,비밀번호,파일? 등)
		 
		String fileName = multi.getFilesystemName("fileName");
		String writer = multi.getParameter("writer");
		String email = multi.getParameter("email");
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		String pass = multi.getParameter("pass");
		int num = Integer.parseInt(multi.getParameter("num"));
		String imageUID = multi.getParameter("imageUID");
		UUID uid = null;
		String uidName = null;
		if(fileName != null){
			uidName = UUID.randomUUID().toString();
		}
		//데이터 빈 처리
				BoardVO article = new BoardVO();
					article.setWriter(writer);
					article.setEmail(email);
					article.setSubject(subject);
					article.setContent(content);
					article.setPass(pass);
					article.setNum(num);
					article.setImageUID(uidName);
					
				String pageNum = request.getParameter("pageNum");
				BoardDAO dbPro = BoardDAO.getInstance();
				int check = dbPro.updateArticle(article);
				
				if(check == 1) {
					
					if(fileName != null){
						String real = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TestProject2\\board\\img\\uploadImg";
						File delFile = new File(real+"\\"+imageUID+".jpg");
						if(delFile.exists()){
							delFile.delete();
						}
						File oldFile = new File(realFolder+"\\"+fileName);
						File newFile = new File(realFolder+"\\"+uidName+".jpg");
						oldFile.renameTo(newFile);
					}else{
						String real = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TestProject2\\board\\img\\uploadImg";
						File delFile = new File(real+"\\"+imageUID+".jpg");
						if(delFile.exists()){
							delFile.delete();
						}
					}
				
				}
				
				request.setAttribute("num", num);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("check", check);
		
		return "/board/updateProc.jsp";
	}

}
