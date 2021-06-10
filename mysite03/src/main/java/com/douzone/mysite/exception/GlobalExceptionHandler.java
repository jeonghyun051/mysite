package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public String handlerException(Model model, Exception e, HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		// 1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		// 2. 요청구분
		// 만약에 json 요청인 경우이면 request header의 Accept에 application/json
		// 만약에 HTML 요청인 경우이면 request header의 Accept에 text/html
		String accept = request.getHeader("accept");
		
		
		if(accept.matches(".*application/json.*")) {
			// 3. json 응답을 해줘야함
			 
		} else {
			LOGGER.error(errors.toString());
			
			// 2. 사과페이지
			// 3. 정상종료
			request.setAttribute("exception",errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		
		}
//		System.out.println(errors);
		/*
		 * 1. appender
		 *  file appender /log-mysite/exception.1.log.zip
		 *  10MB (Archiving 정책)
		 *  1-10 (rolling)
		 *  - console appender
		 *  
		 * 2. logger
		 *  com.douzone.mysite.exception, level(error), (console+file) appender
		 *  Root - Root, level(debug) console appender
		 * 
		 * */
		return "error/exception";
	}
}
