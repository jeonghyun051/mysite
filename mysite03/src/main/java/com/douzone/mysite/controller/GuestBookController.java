package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestBookVo> list = guestBookService.getMessageList();
		model.addAttribute("list",list);
		return "guestbook/index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, GuestBookVo vo) {
		guestBookService.insertMessage(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable int no) {
		model.addAttribute("no",no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
		guestBookService.deleteMessage(no, password);
		return "redirect:/guestbook";
	}
	
//	@ExceptionHandler(Exception.class) // 모든 exception
//	public String HandlerException() {
//		// 1. logging 
//		// 2. 사용자한테 사과
//		return "error/exception";
//	}
}