package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@RequestMapping("/{page}")
	public String index(@PathVariable int page, Model model, @AuthUser UserVo authUser) {
		
		int count = boardService.count();
		int firstPageNo = 0;
		int lastPageNo = (count - 1) / 5;
		
		List<BoardVo> list = boardService.findAll(page);
		int size = list.size();

		model.addAttribute("page",page);
		model.addAttribute("firstPageNo", firstPageNo);
		model.addAttribute("lastPageNo", lastPageNo);
		model.addAttribute("count", count);
		model.addAttribute("size", size);
		model.addAttribute("list", list);
		model.addAttribute("authUser", authUser);

		return "board/list";
	}

	@RequestMapping("/view/{no}")
	public String view(@PathVariable Long no, Model model, @AuthUser UserVo authUser) {
		boardService.updateHit(no);
		model.addAttribute("vo", boardService.findById(no));

		return "board/view";
	}

	@RequestMapping("/delete/{no}/{page}")
	public String delete(@PathVariable Long no, @PathVariable int page) {
		boardService.deleteById(no);

		return "redirect:/board/"+page;
	}
	
	@RequestMapping(value = "/search/{page}", method = RequestMethod.GET)
	public String search(@PathVariable int page, String kwd, Model model) {
		int count = boardService.countByKwd(page,kwd);
		if(count == 0) {
			return "";
		} else {
			List<BoardVo> list = boardService.findByKwd(page,kwd);
			model.addAttribute("firstPageNo2", 0);
			model.addAttribute("lastPageNo2", (count-1) / 5);
			model.addAttribute("count2", count);
			model.addAttribute("size2", list.size());
			model.addAttribute("list", list);
			model.addAttribute("kwd",kwd);
			
			return "board/list";
		}
	}
	
	@RequestMapping("/modify/{no}")
	public String modify(@PathVariable Long no, Model model) {
		model.addAttribute("vo", boardService.findById(no));
		
		return "board/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVo vo, Model model) {	
		boardService.boardUpdate(vo);
		
		return "redirect:/board/view/"+vo.getNo();
	}
	
	@RequestMapping("write/{boardNo}/{groupNo}")
	public String write(@PathVariable int boardNo, @PathVariable int groupNo, Model model) {	
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("groupNo", groupNo);

		return "board/write";
	}
	
	@RequestMapping(value = "write/{boardNo}", method = RequestMethod.POST)
	public String write(@PathVariable Long boardNo, int groupNo, BoardVo vo) {	

		if (boardNo == 0) {
			vo.setGroupNo(boardService.findMaxGroupNo());
			boardService.insert(vo);
			return "redirect:/board/"+0;
		}
		
		else {
			BoardVo vo2 = boardService.findById(boardNo);
			if(vo2.getDepth() == 0) {
				vo.setTitle(vo.getTitle());
				vo.setContents(vo.getContents());
				vo.setUserNo(vo.getUserNo());
				vo.setGroupNo(groupNo);
				vo.setOrderNO(1);
				vo.setDepth(vo2.getDepth()+1);
				vo.setHit(0);
				boardService.update(groupNo);
				boardService.insert2(vo);
		
				return "redirect:/board/0";
				
			} else if(vo2.getDepth() == 1) {
				vo.setTitle(vo.getTitle());
				vo.setContents(vo.getContents());
				vo.setUserNo(vo.getUserNo());
				vo.setGroupNo(groupNo);
				vo.setOrderNO(vo2.getOrderNO()+1);
				vo.setDepth(vo2.getDepth()+1);
				vo.setHit(0);
				boardService.update2(groupNo, vo2.getOrderNO());
				boardService.insert2(vo);
				
				return "redirect:/board/0";
			} else if(vo2.getDepth() == 2) {
				
				return "";
			}
		}
		return "";
	}
}