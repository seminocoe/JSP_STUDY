package com.board.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;
import com.comment.model.CommentDAO;
import com.comment.model.CommentVO;
import com.evaluation.model.EvaluationDAO;
import com.evaluation.model.EvaluationVO;
import com.mvcmem.action.Action;
import com.mvcmem.control.ActionForward;
import com.mvcmem.model.MemberDAO;


public class ContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
			int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");
		
			BoardDAO dbPro = BoardDAO.getInstance();
			BoardVO article = dbPro.getArticle(num);
			
			EvaluationDAO evaDAO = new EvaluationDAO();
			int gechuCnt = evaDAO.countGechu(num);
			
			MemberDAO dao  = MemberDAO.getInstance();
			
			int gechu = 0;
			int bichu = 0;
			ArrayList<EvaluationVO> listEva = evaDAO.getList(num);
			for(int i=0; i<listEva.size(); i++){
				gechu = gechu + listEva.get(i).getGechu();
				bichu = bichu + listEva.get(i).getBichu();
			}
			
			CommentDAO commentDAO = new CommentDAO();
			ArrayList<CommentVO> list = commentDAO.getList(num);
			
			String commentReal = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Rebake86\\board\\img\\uploadImg";
			/* String commentReal = "C:\\Users\\SUCCU\\Desktop\\JSP_STUDY\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject\\boardone\\img\\uploadImg"; */
			
			String real = "C:\\jspworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Rebake86\\board\\img\\uploadImg";
			File viewFile = new File(real+"\\"+article.getImageUID()+".jpg");
			
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("article", article);
			request.setAttribute("gechuCnt", gechuCnt);
			request.setAttribute("real", real);
			request.setAttribute("viewFile", viewFile);
			request.setAttribute("gechu", gechu);
			request.setAttribute("bichu", bichu);
			request.setAttribute("list", list);
			request.setAttribute("dao", dao);
		
		return new ActionForward("/board/content.jsp", false);
	}
	
}
