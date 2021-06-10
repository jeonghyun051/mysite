package com.douzone.mysite.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("")
	public String main(Model model, HttpSession session) {
		SiteVo vo = adminService.findOne();
		model.addAttribute("vo",vo);
		// application.setAttribute("title", vo.getTitle());
		ServletContext abc = session.getServletContext();
		abc.setAttribute("title", vo.getTitle());
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg1")
	public String message1() {
		return "안녕";
	}
	
	@ResponseBody
	@RequestMapping("/msg2")
	public UserVo message2() {
		
		UserVo vo = new UserVo();
		vo.setNo(10L);
		vo.setEmail("rlawjdgus@naver.com");
		vo.setName("김정현00");		
		return vo;
	}
}