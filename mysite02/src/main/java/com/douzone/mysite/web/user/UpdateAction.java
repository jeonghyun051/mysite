package com.douzone.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		Long no = Long.parseLong(request.getParameter("no"));
		
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		vo.setNo(no);
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		authUser.setName(name);
		authUser.setPassword(password);
		authUser.setGender(gender);
		
		new UserRepository().update(vo);
		
		
		MvcUtils.redirect(request.getContextPath(), request, response);
		
	}
}