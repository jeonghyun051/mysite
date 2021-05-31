package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.mvc2.util.MvcUtils;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.util.Action;

public class IndexAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<GuestBookVo> list = new GuestBookRepository().findAll();	
		request.setAttribute("size", list.size());
		request.setAttribute("list", list);
		MvcUtils.forward("guestbook/list", request, response);
	}
}