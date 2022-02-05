package com.shang.schedule.service;


import com.shang.schedule.pojo.Users;

/**
 * @author Writer: 尚钰洋
 * @version Create-Time：2021/4/8 16:05
 */
public interface UsersService {

	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 */
	Users selectUserByUserName(String userName) throws Exception;

	/**
	 * 根据用户名密码验证登录信息
	 * @param userName
	 * @param password
	 * @return
	 */
	Boolean verifyLogin(String userName, String password) throws Exception;

}
