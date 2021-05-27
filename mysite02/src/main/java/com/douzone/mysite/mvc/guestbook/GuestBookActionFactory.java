package com.douzone.mysite.mvc.guestbook;

import com.douzone.mvc.Action;
import com.douzone.mvc.ActionFactory;
import com.douzone.mysite.mvc.main.MainAction;
import com.douzone.mysite.mvc.user.JoinAction;
import com.douzone.mysite.mvc.user.JoinFormAction;
import com.douzone.mysite.mvc.user.JoinSuccessAction;

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
