package com.douzone.mysite.web.board;

import com.douzone.web.Action;
import com.douzone.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("view".equals(actionName)) {
			action = new ViewAction();

		} else if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("write".equals(actionName)) {
			action = new WriteAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else if("search".equals(actionName)) {
			action = new searchAction();
		}
		
		else {
			action = new ListAction();
		}

		return action;

	}
}
