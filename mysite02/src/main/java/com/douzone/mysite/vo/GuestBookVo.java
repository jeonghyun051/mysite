package com.douzone.mysite.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GuestBookVo {

	private Long no;
	private String name;
	private String password;
	private String message;
	private String regDate;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRegDate() {
		Date a = new Date();	// 현재날짜 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String regDate = sdf.format(a);
		return regDate;
	}
	public void setRegDate(Date reg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String regDate2 = sdf.format(reg);
		this.regDate = regDate2;
	}
	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", password=" + password + ", message=" + message
				+ ", regDate=" + regDate + "]";
	}
}