package com.sxt.filter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.alibaba.fastjson.JSON;

/**
 * 重写shrio authc 的过滤器
 * 解决 当用户使用浏览器访问未认证的页面时跳转到页面
 * 	   当用户使用ajax访问未认证的数据时返回json串
 * @author LJH
 *
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	/**
	 * 在访问controller前判断是否登录，返回json，不进行重定向。
	 * 
	 * @param request
	 * @param response
	 * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
	 * @throws Exception
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		//判断是否为ajax请求
		if(isAjax(request)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			Map<String,Object> resultData = new HashMap<>();
			resultData.put("code", -1);
			resultData.put("msg", "登录认证失效，请重新登录!");
			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(resultData));
		}else {
			//重定向
			response.reset();
			HttpServletResponse servletResponse=(HttpServletResponse) response;
			servletResponse.sendRedirect("../index.jsp");
		}
		return false;
		
	}
	
	/**
	 * 判断是否为ajax请求
	 * @param request
	 * @return
	 */
	private boolean isAjax(ServletRequest request) {
		String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(header)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	
}
