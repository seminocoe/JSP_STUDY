package com.mvcmem.control;

import com.mvcmem.action.Action;
import com.mvcmem.action.DeleteFormAction;
import com.mvcmem.action.DeleteProcAction;
import com.mvcmem.action.IndexAction;
import com.mvcmem.action.LoginFormAction;
import com.mvcmem.action.LogOutAction;
import com.mvcmem.action.LoginProcAction;
import com.mvcmem.action.ModifyFormAction;
import com.mvcmem.action.ModifyProcAction;
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
	
		case "regProc":
			action = new RegProcAction();
			break;
			
		case "idCheck":
			action = new IdCheckAction();
			break;
			
		case "zipCheck":
			action = new ZipCheckAction();
			break;
		
		case "modifyForm":
			action = new ModifyFormAction();
			break;

		case "modifyProc":
			action = new ModifyProcAction();
			break;
			
		case "deleteForm":
			action = new DeleteFormAction();
			break;
			
		case "deleteProc":
			action = new DeleteProcAction();
			break;
			
		default:
			action = new IndexAction();
			break;
		}
		return action;
	}
	
}
