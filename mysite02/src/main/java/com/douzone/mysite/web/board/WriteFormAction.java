package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		String groupNo = request.getParameter("groupNo");
		request.setAttribute("groupNo", groupNo);
		request.setAttribute("boardNo", boardNo);
		MvcUtils.forward("board/write", request, response);

	}

}
