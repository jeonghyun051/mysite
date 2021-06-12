package com.douzone.mysite.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Auth
@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@RequestMapping("/{page}")
	public String index(@PathVariable int page, Model model, @AuthUser UserVo authUser) {
		int count = boardService.count();
		List<BoardVo> list = boardService.findAll(page);

		model.addAttribute("page",page);
		model.addAttribute("lastPageNo", (count - 1) / 5);
		model.addAttribute("count", count);
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
		System.out.println("count:" + count);
		if(count == 0) {
			return "board/list";
		} else {
			List<BoardVo> list = boardService.findByKwd(page,kwd);
			model.addAttribute("lastPageNo2", (count-1) / 5);
			model.addAttribute("count2", count);
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
	public String write(@ModelAttribute @Valid BoardVo vo, BindingResult result, Model model) {	
		
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			model.addAttribute("boardNo", vo.getNo());
			model.addAttribute("groupNo", vo.getGroupNo());

			return "board/write";
		} 
		return "redirect:/board/"+0;
		
//		if (boardNo == 0) {
//			vo.setGroupNo(boardService.findMaxGroupNo());
//			boardService.insert(vo);
//			return "redirect:/board/"+0;
//		}
//		else {
//			BoardVo vo2 = boardService.findById(boardNo);
//			vo.setGroupNo(groupNo);
//			vo.setDepth(vo2.getDepth()+1);
//			vo.setHit(0);
//			if(vo2.getDepth() == 0) {
//				vo.setOrderNO(1);
//				boardService.update(groupNo);
//				boardService.insert2(vo);
//				return "redirect:/board/0";
//			} else if(vo2.getDepth() == 1) {
//				vo.setOrderNO(vo2.getOrderNO()+1);
//				vo.setDepth(vo2.getDepth()+1);
//				boardService.update2(groupNo, vo2.getOrderNO());
//				boardService.insert2(vo);
//				return "redirect:/board/0";
//			} 
//		}
//		return "redirect:/board/0";
	}
}