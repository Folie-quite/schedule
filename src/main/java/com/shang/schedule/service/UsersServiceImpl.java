package com.shang.schedule.service;

import com.shang.schedule.mapper.UsersMapper;
import com.shang.schedule.pojo.Users;
import com.shang.schedule.pojo.UsersExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Writer: 尚钰洋
 * @version Create-Time：2021/4/8 16:05
 */
@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersMapper usersMapper;

	@Override
	public Users selectUserByUserName(String userName) throws Exception {

		if(StringUtils.isBlank(userName)){
			throw new Exception("请输入用户名！");
		}

		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();

		criteria.andUserNameEqualTo(userName);

		List<Users> users = usersMapper.selectByExample(example);

		if(null == users || users.size() < 1){
			throw new Exception("用户不存在！");
		}

		return users.get(0);
	}

	@Override
	public Boolean verifyLogin(String userName, String password) throws Exception {
		Users users = selectUserByUserName(userName);
		return password.equals(users.getPassword());
	}
}
