package com.douzone.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@RequestMapping("/user/api")
@RestController("userControllerApi")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/checkemail")
	public Object checkEmail(@RequestParam(value = "email", required = true, defaultValue = "") String email) {
		UserVo userVo = userService.getUser(email);
		
		Map<String,Object> result = new HashMap<>();
		result.put("result", "success");
		result.put("exist", userVo != null);
		return result;
	}	
}