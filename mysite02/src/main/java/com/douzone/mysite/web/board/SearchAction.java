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
		
	
		int page = Integer.parseInt(request.getParameter("p"));
		String kwd = request.getParameter("kwd");
		
		int count = new BoardRepository().countByKwd(page,kwd);
		int firstPageNo = 0;
		int lastPageNo = (count-1) / 5;
		System.out.println("=======searchaction=========");
		System.out.println("검색값" + kwd);
		System.out.println("카운터" + count);
		System.out.println("첫페이지" + firstPageNo);
		System.out.println("라스트페이지" + lastPageNo);

		
		List<BoardVo> list = new BoardRepository().findByKwd(page, kwd);
		int size = list.size();
		
		request.setAttribute("firstPageNo2", firstPageNo);
		request.setAttribute("lastPageNo2", lastPageNo);
		request.setAttribute("count2", count);
		request.setAttribute("size2", size);
		request.setAttribute("list", list);
		MvcUtils.forward("board/list", request, response);
		

	}

}
