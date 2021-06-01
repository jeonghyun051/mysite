package com.douzone.mysite.web.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long userNo = Long.parseLong(request.getParameter("userNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		//System.out.println("보드값 뭐냐" + boardNo);

		if (boardNo == 0) {
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(content);
			vo.setUserNo(userNo);
			vo.setGroupNo(0);
			vo.setOrderNO(0);
			vo.setDepth(0);
			vo.setHit(0);
			vo.setGroupNo(new BoardRepository().findMaxGroupNo());

			new BoardRepository().insert(vo);
			MvcUtils.redirect(request.getContextPath() + "/board?a=list", request, response);
		} else {
			BoardVo vo = new BoardVo();
			int groupNo = Integer.parseInt(request.getParameter("groupNo"));
			BoardVo vo2 = new BoardRepository().findById(boardNo);
			if(vo2.getDepth() == 0) {
				vo.setTitle(title);
				vo.setContents(content);
				vo.setUserNo(userNo);
				vo.setGroupNo(groupNo);
				vo.setOrderNO(1);
				vo.setDepth(vo2.getDepth()+1);
				vo.setHit(0);
				new BoardRepository().update(groupNo);
				new BoardRepository().insert2(vo);	
				MvcUtils.redirect(request.getContextPath() + "/board?a=list", request, response);
			} else if(vo2.getDepth() == 1) {
				vo.setTitle(title);
				vo.setContents(content);
				vo.setUserNo(userNo);
				vo.setGroupNo(groupNo);
				vo.setOrderNO(vo2.getOrderNO()+1);
				vo.setDepth(vo2.getDepth()+1);
				vo.setHit(0);
				new BoardRepository().update2(groupNo, vo2.getOrderNO());
				new BoardRepository().insert2(vo);	
				MvcUtils.redirect(request.getContextPath() + "/board?a=list", request, response);
			} else if(vo2.getDepth() == 2) {
				PrintWriter pw = response.getWriter();
				pw.print("<script>alert('덧글이 2개까지만 가능합니다.');history.go(-3);</script>");
				
			}
		}
	}
}