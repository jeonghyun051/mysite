package com.douzone.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user/api")
@Controller("userControllerApi")
public class UserController {
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public Object checkEmail(@RequestParam(value = "email", required = true, defaultValue = "") String email) {
		
		Map<String,Object> result = new HashMap<>();
		result.put("result", "sucess");
		result.put("exist", false);
		return result;
	}	
}