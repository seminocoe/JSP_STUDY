package com.mvcmem.control;

import com.board.action.*;
import com.mvcmem.action.Action;
import com.mvcmem.action.DeleteFormAction;
import com.mvcmem.action.DeleteProcAction;
import com.mvcmem.action.IdCheckAction;
import com.mvcmem.action.IndexAction;
import com.mvcmem.action.LoginFormAction;
import com.mvcmem.action.LoginProcAction;
import com.mvcmem.action.LogoutProcAction;
import com.mvcmem.action.ModifyFormAction;
import com.mvcmem.action.ModifyProcAction;
import com.mvcmem.action.RegFormAction;
import com.mvcmem.action.RegProcAction;

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
		
		switch(cmd) {
		
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
			action = new LogoutProcAction();
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
		
		
			
			
			
			
			
			
			
			
			
			
			
			
			
		case "list":
			action = new ListAction();
			break;
			
		case "comment":
			action = new CommentAction();
			break;
			
		case "commentDelete":
			action = new CommentDeleteAction();
			break;
			
		case "commentUpdate":
			action = new CommentUpdateAction();
			break;
			
		case "commentUpdatePro":
			action = new CommentUpdateProAction();
			break;
			
		case "content":
			action = new ContentAction();
			break;
			
		case "b_DeleteForm":
			action = new B_DeleteFormAction();
			break;
			
		case "b_DeletePro":
			action = new B_DeleteProAction();
			break;
			
		case "evaluation":
			action = new EvaluationAction();
			break;
			
		case "updateForm":
			action = new UpdateFormAction();
			break;
			
		case "updatePro":
			action = new UpdateProAction();
			break;
			
		case "writeForm":
			action = new WriteFormAction();
			break;
			
		case "writePro":
			action = new WriteProAction();
			break;
			
		default :
			action = new IndexAction();
			break;	
		
		}
		
		return action;
	}
}
