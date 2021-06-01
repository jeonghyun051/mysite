package com.douzone.mysite.web.guestbook;

import com.douzone.web.Action;
import com.douzone.web.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("list".equals(actionName)) {
			action = new IndexAction();
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		}else if ("addAction".equals(actionName)) {
			action = new AddAction();
		}else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		}
		
		else {
			action = new IndexAction();
		}
		return action;
	}

}
