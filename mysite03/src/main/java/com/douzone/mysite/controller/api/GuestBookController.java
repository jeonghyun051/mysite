package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller("guestbookControllerApi")
@RequestMapping("/guestbook/api")
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;

	@ResponseBody
	@RequestMapping("/delete/{no}")
	public JsonResult ex2(@PathVariable("no") Long no, String password) {
		// 삭제 작업(GuestbookService)
		Long data = 0L;
		if(guestBookService.deleteMessage(no, password) == true) {
			data = no;
		} else {
			// 1. 삭제가 안된 경우
			data = -1L;
		}
		// 2. 삭제가 성공한 경우
		return JsonResult.success(data);
	}
	
	@ResponseBody
	@RequestMapping("/list/{no}")
	public JsonResult list(@PathVariable int no) {
		List<GuestBookVo> list = guestBookService.getMessageList(no);
		return JsonResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public JsonResult add(@RequestBody GuestBookVo vo) {
		guestBookService.insertMessage(vo);
		return JsonResult.success(vo);
	}
}