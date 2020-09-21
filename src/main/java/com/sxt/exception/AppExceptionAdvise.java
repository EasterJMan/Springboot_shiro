package com.sxt.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice//  当下面的方法被回调时会以转发或重定向的方式出去
@RestControllerAdvice  //下面的方法会以json串的形式返回给页面
public class AppExceptionAdvise {
	
	@ExceptionHandler(value={UnauthorizedException.class})
	public Map<String,Object> unAuthorized() {
		Map<String,Object>  map=new HashMap<>();
		map.put("code", -1);
		map.put("msg", "您未授权");
		return map;
	}
	
	
}
