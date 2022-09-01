package com.mvcmem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcmem.control.ActionForward;

import com.mvcmem.model.StudentDAO;
import com.mvcmem.model.ZipCodeVO;
import java.util.*;

public class ZipCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		
		request.setCharacterEncoding("utf-8");
		
		StudentDAO dao = StudentDAO.getInstance();
		String check = request.getParameter("check");
		String dong = request.getParameter("dong");
		
		//파라미터 값을 zipcodeRead()메소드의 매개변수로 전달
		Vector<ZipCodeVO> zipcodeList = dao.zipcodeRead(dong);
		
		int totalList = zipcodeList.size();
		//System.out.println("검색 개수 "+totalList);
		
		request.setAttribute("check", check);
		request.setAttribute("dong", dong);
		request.setAttribute("zipcodeList", zipcodeList);
		request.setAttribute("totlaList", totalList);
		
		return new ActionForward("/mvcmem/zipCheck.jsp", false);
	}

}
