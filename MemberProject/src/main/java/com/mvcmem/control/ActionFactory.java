package com.mvcmem.control;

import com.mvcmem.action.Action;
import com.mvcmem.action.IndexAction;
import com.mvcmem.action.LoginFormAction;
import com.mvcmem.action.regFormAction;

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
	
		case "regForm":
			action = new regFormAction();
			break;	
		
		default:
			action = new IndexAction();
			break;
		}
		return action;
	}
	
}
