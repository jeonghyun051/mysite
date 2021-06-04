package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		return "guestbook/delete";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String delete(String password, @PathVariable int no) {
		GuestBookVo vo = guestBookService.findByPassword(no);
		int result = guestBookService.deleteMessage(no,password,vo);
		if(result == 1) {
			return "redirect:/guestbook";
		} else {
			return "guestbook/deleteform";
		}
	}
	
	@ExceptionHandler(Exception.class) // 모든 exception
	public String HandlerException() {
		// 1. logging 
		// 2. 사용자한테 사과
		return "error/exception";
	}
}