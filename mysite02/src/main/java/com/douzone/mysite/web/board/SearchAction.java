package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int count = new BoardRepository().countByKwd();
		int firstPageNo = 0;
		int lastPageNo = (count-1) / 5;
		String kwd = request.getParameter("kwd");
		int page = 0;
		List<BoardVo> list = new BoardRepository().findByKwd(page, kwd);
		int size = list.size();
		
		request.setAttribute("firstPageNo", firstPageNo);
		request.setAttribute("lastPageNo", lastPageNo);
		request.setAttribute("count", count);
		request.setAttribute("size", size);
		request.setAttribute("list", list);
		MvcUtils.forward("board/list", request, response);
		

	}

}
