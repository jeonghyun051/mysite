package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mysite.dao.GuestBookDao;
import com.douzone.mysite.mvc2.util.MvcUtils;
import com.douzone.mysite.vo.GuestBookVo;

public class IndexAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<GuestBookVo> list = new GuestBookDao().findAll();
		request.setAttribute("list", list);
		MvcUtils.forward("guestbook/list", request, response);
	}

}
