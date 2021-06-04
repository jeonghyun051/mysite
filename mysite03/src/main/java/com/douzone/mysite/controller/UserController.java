package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			HttpSession session,
			@RequestParam(value="email", required=true, defaultValue="") String email,
			@RequestParam(value="password", required=true, defaultValue="") String password,
			Model model) {
		System.out.println("email:"+email+" " + "password:" + password);
		
		UserVo authUser = userService.getUser(email,password);
		System.out.println(authUser);
		if(authUser == null) {
			model.addAttribute("result","fail");
			model.addAttribute("result",email);
			
			return "user/login";
		}
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			// 접근제어
			return("redirect:/");
		}
		
		// 로그아웃 처리
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
}