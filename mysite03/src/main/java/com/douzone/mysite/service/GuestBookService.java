package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookRepository guestBookRepository;
	
	public List<GuestBookVo> getMessageList() {
		return guestBookRepository.findAll();
	}	
	
	public int deleteMessage(int no, String password, GuestBookVo vo) {
		if(password.equals(vo.getPassword())) {
			guestBookRepository.deleteById(no);
			return 1;
		}else {
			return -1;
		}
	}
	
	public GuestBookVo findByPassword(int no) {
		return guestBookRepository.findById(no); 
	}
	
	public void insertMessage(GuestBookVo vo) {
		guestBookRepository.insert(vo);
	}
}