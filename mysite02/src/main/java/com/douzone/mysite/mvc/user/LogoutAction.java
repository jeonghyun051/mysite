package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.mvc2.util.MvcUtils;
import com.douzone.web.util.Action;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null) {
			/* 로그아웃 */
			session.removeAttribute("authUser");
			session.invalidate();
			
			return;
		}

		
		MvcUtils.redirect(request.getContextPath(), request, response);
	}

}
