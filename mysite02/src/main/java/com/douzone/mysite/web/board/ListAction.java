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

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		firstPageNo = 1;
//		lastPageNo = 10;
//		nextPageNo = 11;
//		prevPageNo = 1;
		int page = Integer.parseInt(request.getParameter("p")); // 현재 페이지 처음 0 
		
		int count = new BoardRepository().count();
		int firstPageNo = 0;
		int lastPageNo = (count-1) / 5;
		
		List<BoardVo> list = new BoardRepository().findAll(page);
		int size = list.size();
		System.out.println("라스트페이지" + lastPageNo);
		
		request.setAttribute("firstPageNo", firstPageNo);
		request.setAttribute("lastPageNo", lastPageNo);
		request.setAttribute("size", size);
		request.setAttribute("list", list);
		
		MvcUtils.forward("board/list", request, response);

	}

}
