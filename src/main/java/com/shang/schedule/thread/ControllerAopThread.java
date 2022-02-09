/**
 *
 *@author Writer: s
 *@version Create-Time：2020年5月28日 下午3:05:08
 *
 */
package com.shang.schedule.thread;

import com.shang.schedule.pojo.SystemLogs;
import com.shang.schedule.service.SystemLogsService;

/**
 * 
 */
public class ControllerAopThread implements Runnable{

	private SystemLogs systemLogs;
	private SystemLogsService systemLogsService;
	
	/**
	 * @param systemLogs
	 */
	public ControllerAopThread(SystemLogs systemLogs, SystemLogsService systemLogsService) {
		super();
		this.systemLogs = systemLogs;
		this.systemLogsService = systemLogsService;
	}
	
	public ControllerAopThread() {
		super();
	}

	@Override
	public void run() {
		systemLogsService.addSystemLogs(systemLogs);
	}
	
}
