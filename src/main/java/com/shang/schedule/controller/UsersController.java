package com.shang.schedule.controller;

import com.shang.schedule.pojo.Users;
import com.shang.schedule.service.UsersService;
import com.shang.schedule.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Writer: 尚钰洋
 * @version Create-Time：2021/4/9 10:02
 */
@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	@RequestMapping("/getUser")
	public MyResult getUser(String user, String password){
		MyResult result = null;

		try {
			if(usersService.verifyLogin(user, password)){
				Users users = usersService.selectUserByUserName(user);
				result = new MyResult(users);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new MyResult(500, e.getMessage(), null);
		}

		return result;
	}

}
