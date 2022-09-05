package com.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;

import com.board.action.CommandAction;


public class ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//명령어와 명령어 처리 클래스를 쌍으로 불러다가 저장함
		private Map<String, Object> commandMap = 
				new HashedMap<String, Object>();
		
		//명령어와 처리 클래스가 매핑되어 있는 Command.properties파일을 읽어와서
		//Map객체인 commandMap에 저장한다.
		
		
		public void init(ServletConfig config) throws ServletException {
			
			//web.xml파일에서 propertyConfig에 해당하는 init-param의 값을 읽어옴
			String props = config.getInitParameter("propertyConfig");
			
			//명령어와 처리클래스 매핑정보를 저장할 Properties객체를 생성함
			Properties pr = new Properties();
			
			String path = config.getServletContext().getRealPath("/WEB-INF");
			FileInputStream f = null;
			
			try {
				//Command.properties파일의 내용을 읽어옴
				f = new FileInputStream(new File(path, props));
				//Command.properties파일의 정보를 Properties 객체에 저장함
				pr.load(f);
			}catch (IOException e) {
				throw new ServletException(e);
			}finally {
				if(f != null) try {f.close();}catch(IOException ex) {}
			}
			
			//Iterator 객체를 이용해서 파일 내용을 키 값을 읽어옴
			//Iterator 객체를 생성함
			Iterator<Object> keyIter = pr.keySet().iterator();
			
			while(keyIter.hasNext()) {
				
				String command = (String)keyIter.next();
				String className = pr.getProperty(command);
				
				try {
					Class commandClass = Class.forName(className);
					Object commandInstance = commandClass.newInstance();
					//해당 클래스의 객체를 생성함
					
					//Map객체인 commandMap에 객체를 저장함
					commandMap.put(command, commandInstance);
					
				}catch (ClassNotFoundException e) {
					throw new ServletException(e);
				}catch (InstantiationException e) {
					throw new ServletException(e);
				}catch (IllegalAccessException e) {
					throw new ServletException(e);
				}
			}
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	//사용자의 요청을 분석해서 해당 작업을 처리하는 메소드
		private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String view = null;
			CommandAction com = null;
			
			try {
				String command = request.getRequestURI();
				if(command.indexOf(request.getContextPath()) == 0) {
					command = command.substring(request.getContextPath().length());
				}
				com = (CommandAction)commandMap.get(command);
				view = com.requestPro(request, response);
			}catch (Throwable e) {
				throw new ServletException(e);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
			
		}

}
