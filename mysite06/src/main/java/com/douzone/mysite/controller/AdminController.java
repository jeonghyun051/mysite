package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo vo = adminService.findOne();
		model.addAttribute("vo",vo);
		return "admin/main";
	}
	
	@RequestMapping(value = "/main/update", method = RequestMethod.POST)
	public String updateMain(SiteVo vo, 
			@RequestParam("file1") MultipartFile file1,
			Model model) {
		
		String url = fileUploadService.restore(file1);
		vo.setProfile(url);
		adminService.update(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String uesr() {
		return "admin/user";
	}
}