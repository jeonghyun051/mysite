package com.douzone.mysite.web.guestbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String password = request.getParameter("password");
			int no = Integer.parseInt(request.getParameter("no"));
	
			GuestBookVo vo = new GuestBookRepository().findById(no);
			String bookPassword = vo.getPassword();
			
			if(password.equals(bookPassword)){
				new GuestBookRepository().deleteById(no);
				MvcUtils.redirect(request.getContextPath()+"/guestbook?a=list"+"&sucess=true", request, response);
			} else {
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter pw =response.getWriter();	
				pw.print("<script>alert('틀림');history.go(-1);</script>");
			}
	}

}
