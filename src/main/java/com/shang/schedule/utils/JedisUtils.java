/**
 * 
 *@author 作者 Your-Name: s
 *@version 创建时间：2019年12月23日 下午3:26:50 
 *
 */

package com.shang.schedule.utils;

import com.shang.schedule.jedis.JedisService;
import com.shang.schedule.pojo.Users;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JedisUtils {

	public static JedisService jedisService;
	
	@Autowired
	public void setJedisService(JedisService jedis){
		jedisService = jedis;
	}
	
	public static Users getUser(HttpServletRequest request){
		
		String cookieValue = CookieUtils.getCookieValue(request, "token");
		if(StringUtils.isBlank(cookieValue)) {
			cookieValue = "";
		}
		Users user = (Users)jedisService.get(cookieValue);
		
		return user;
	}
	
	public static void setUser(Users user,HttpServletRequest request) {
		String cookieValue = CookieUtils.getCookieValue(request, "token");
		
		jedisService.setex(cookieValue, 600000, user);
	}
	
}
