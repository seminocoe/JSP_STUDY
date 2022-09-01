package com.mvcmem.control;

import com.mvcmem.action.Action;
import com.mvcmem.action.IndexAction;
import com.mvcmem.action.LoginFormAction;
import com.mvcmem.action.LogOutAction;
import com.mvcmem.action.LoginProcAction;
import com.mvcmem.action.RegFormAction;
import com.mvcmem.action.RegProcAction;
import com.mvcmem.action.ZipCheckAction;
import com.mvcmem.action.IdCheckAction;

public class ActionFactory {
	
	private static ActionFactory factory;
	
	private ActionFactory() {	}
	
	public static synchronized ActionFactory getInstance() {
		
		if(factory == null) {
			factory = new ActionFactory();
		}
		return factory;
	}

	public Action getAction(String cmd) {
		
		Action action = null;
		
		switch (cmd) {
		case "index":
			action = new IndexAction();
			break;

		case "login":
			action = new LoginFormAction();
			break;
			
		case "loginProc":
			action = new LoginProcAction();
			break;
			
		case "logout":
			action = new LogOutAction();
			break;
			
		case "regForm":
			action = new RegFormAction();
			break;
	
		case "idCheck":
			action = new IdCheckAction();
			break;
			
		case "zipCheck":
			action = new ZipCheckAction();
			break;
		
		case "regProc":
			action = new RegProcAction();
			break;
			
		default:
			action = new IndexAction();
			break;
		}
		return action;
	}
	
}
