/**
 *
 *@author Writer: s
 *@version Create-Time：2020年5月27日 下午4:09:03
 *
 */
package com.shang.schedule.service;

import com.shang.schedule.mapper.SystemLogsMapper;
import com.shang.schedule.pojo.SystemLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 
 */
@Service
public class SystemLogsServiceImpl implements SystemLogsService {

	@Autowired
	private SystemLogsMapper systemLogsMapper;
	
	@Override
	public void addSystemLogs(SystemLogs systemLogs) {
		if(null == systemLogs.getCreateDate()) {
			systemLogs.setCreateDate(new Date());
		}
		systemLogsMapper.insertSelective(systemLogs);
	}

}
