package com.shang.schedule.configuration;

import com.shang.schedule.pojo.Users;
import com.shang.schedule.utils.CookieUtils;
import com.shang.schedule.utils.JedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		Users user = new Users();
		//判断用户是否登陆  如果没有登陆  重定向到登陆页面   不放行   如果登陆了  就放行了
		String requestURI = request.getRequestURI();
//		String username = (String) request.getSession().getAttribute("USER_SESSION");
		String token = CookieUtils.getCookieValue(request, "token");
//		System.out.println("token: " + token + "访问requestURI: " + requestURI);
		if(StringUtils.isBlank(token)){
			response.sendRedirect("/");
			return false;
		} else {
			user = JedisUtils.getUser(request);
			if(null == user){
				response.sendRedirect("/");
				return false;
			}
		}
//		System.out.println("用户: " + user.getUserName() + "访问requestURI: " + requestURI);
		return true;
	}
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}



}
