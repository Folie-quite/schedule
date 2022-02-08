package com.shang.schedule.controller;

import com.shang.schedule.jedis.JedisService;
import com.shang.schedule.pojo.Users;
import com.shang.schedule.service.UsersService;
import com.shang.schedule.utils.MyResult;
import com.shang.schedule.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Writer: 尚钰洋
 * @version Create-Time：2021/4/8 16:11
 */
@Controller
public class LoginController {

	@Autowired
	private UsersService usersService;
	@Autowired
	private JedisService jedisService;

	@RequestMapping("/")
	public String login(){
		return "login";
	}

	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/login")
	@ResponseBody
	public MyResult loginUser(String user, String password){
		MyResult result = null;
		try {
			if(usersService.verifyLogin(user, password)){
				Users users = usersService.selectUserByUserName(user);
				String s = users.getId().toString();
				System.out.println(s);
				jedisService.set(s, users);
				Users user1 = (Users)jedisService.get(s);
				System.out.println(user1.getUserName());
				result = new MyResult(users.getId());
			} else {
				result = new MyResult(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new MyResult(500, "loginUser异常！", null);
		}
		return result;
	}

}
