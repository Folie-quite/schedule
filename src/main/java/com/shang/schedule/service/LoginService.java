package com.shang.schedule.service;


import com.shang.schedule.pojo.Users;

public interface LoginService {

	/**
	 * 验证用户登录
	 * @param userName 用户名
	 * @param password 密码
	 * @param ip 登录时IP地址
	 * @return 是否验证通过
	 */
	boolean loginService(Users user, String password, String ip);
	
}
