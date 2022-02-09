/**
 *
 *@author Writer: s
 *@version Create-Time：2020年5月25日 下午3:51:35
 *
 */
package com.shang.schedule.configuration;

import com.shang.schedule.jedis.JedisService;
import com.shang.schedule.pojo.SystemLogs;
import com.shang.schedule.pojo.Users;
import com.shang.schedule.service.SystemLogsService;
import com.shang.schedule.thread.ControllerAopThread;
import com.shang.schedule.utils.IpUtils;
import com.shang.schedule.utils.JedisUtils;
import com.shang.schedule.utils.MyDateUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * controller切面
 */
@Aspect
@Component 
public class ControllerAOP {
	protected final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);
	
	@Autowired
	private SystemLogsService systemLogsService;

	@Around(value = "@annotation(com.shang.schedule.configuration.SystemLogsAOP)")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) {
		Object proceed = null;
		String details = "";
		Integer status = 200;
		Long distanceTimestamp = 0L;
		String userName = "游客";
		//获得方法名
		String name = proceedingJoinPoint.getSignature().getName();
		
		//获取开始时间
		Date startDate = new Date();
		try {
			//执行方法主体
			proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
			//获取结束时间
			Date endDate = new Date();
			//计算用时
			long[] distanceTime = MyDateUtil.getDistanceTime(startDate, endDate);
			
			details = "访问 " + name + "成功！用时 " + distanceTime[0] + "天" + distanceTime[1] + "时" + distanceTime[2] + "分" + distanceTime[3] + "秒";
			
			//计算时间差
			distanceTimestamp = MyDateUtil.getDistanceTimestamp(startDate, endDate);
		} catch (Throwable e) {
			e.printStackTrace();
			status = 500;
			details = "访问 " + name + "异常！";
			Date endDate = new Date();
			//计算时间差
			distanceTimestamp = MyDateUtil.getDistanceTimestamp(startDate, endDate);
			logger.error(details + "(请求切面异常！)", e);
		}
		
		//获得HttpServletRequest
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        
        String ip = IpUtils.getIp(request);
        String header = request.getHeader("User-Agent");
        Users user = JedisUtils.getUser(request);
        String requestURI = request.getRequestURI();
		
		if(null != user && StringUtils.isNotBlank(user.getUserName())) {
			userName = user.getUserName();
		}
		SystemLogs systemLogs = new SystemLogs();
		systemLogs.setDetails(details);
		systemLogs.setMethod(name);
		systemLogs.setStates(status);
		systemLogs.setTime(distanceTimestamp);
		systemLogs.setIp(ip);
		systemLogs.setUrl(requestURI);
		systemLogs.setDeviceDetails(header);
		systemLogs.setCreateUser(userName);
		
		new Thread(new ControllerAopThread(systemLogs, systemLogsService)).start();
		
		return proceed;
	}
	
	private void testServlet(HttpServletRequest request) {
		String agent = request.getHeader("user-agent"); 
		System.out.println("agent: " + agent); 
		StringTokenizer st = new StringTokenizer(agent,";"); 
		st.nextToken(); 
		String userbrowser = st.nextToken(); 
		System.out.println("userbrowser: " + userbrowser); 
//		String useros = st.nextToken(); 
//		System.out.println("useros: " + useros); 
		System.out.println("os.name: " +  System.getProperty("os.name")); //win2003竟然是win xp？ 
		System.out.println("version: " + System.getProperty("os.version")); 
		System.out.println("arch: " + System.getProperty("os.arch")); 
		System.out.println("user-agent: " + request.getHeader("user-agent")); //返回客户端浏览器的版本号、类型 
		System.out.println("getMethod: " + request.getMethod()); //：获得客户端向服务器端传送数据的方法有get、post、put等类型 
		System.out.println(request.getRequestURI()); //：获得发出请求字符串的客户端地址 
		System.out.println(request.getServletPath()); //：获得客户端所请求的脚本文件的文件路径 
		System.out.println(request.getServerName()); //：获得服务器的名字 
		System.out.println(request.getServerPort()); //：获得服务器的端口号 
		System.out.println(request.getRemoteAddr()); //：获得客户端的ip地址 
		System.out.println(request.getRemoteHost()); //：获得客户端电脑的名字，若失败，则返回客户端电脑的ip地址
		System.out.println(request.getProtocol()); //： 
		System.out.println(request.getHeaderNames()); //：返回所有request header的名字，结果集是一个enumeration（枚举）类的实例 
		System.out.println("Protocol: " + request.getProtocol()); 
		System.out.println("Scheme: " + request.getScheme()); 
		System.out.println("Server Name: " + request.getServerName() ); 
		System.out.println("Server Port: " + request.getServerPort()); 
		System.out.println("Protocol: " + request.getProtocol()); 
//		System.out.println("Server Info: " + getServletConfig().getServletContext().getServerInfo()); 
		System.out.println("Remote Addr: " + request.getRemoteAddr()); 
		System.out.println("Remote Host: " + request.getRemoteHost()); 
		System.out.println("Character Encoding: " + request.getCharacterEncoding()); 
		System.out.println("Content Length: " + request.getContentLength()); 
		System.out.println("Content Type: "+ request.getContentType()); 
		System.out.println("Auth Type: " + request.getAuthType()); 
		System.out.println("HTTP Method: " + request.getMethod()); 
		System.out.println("Path Info: " + request.getPathInfo()); 
		System.out.println("Path Trans: " + request.getPathTranslated()); 
		System.out.println("Query String: " + request.getQueryString()); 
		System.out.println("Remote User: " + request.getRemoteUser()); 
		System.out.println("Session Id: " + request.getRequestedSessionId()); 
		System.out.println("Request URI: " + request.getRequestURI()); 
		System.out.println("Servlet Path: " + request.getServletPath()); 
		System.out.println("Accept: " + request.getHeader("Accept")); 
		System.out.println("Host: " + request.getHeader("Host")); 
		System.out.println("Referer : " + request.getHeader("Referer")); 
		System.out.println("Accept-Language : " + request.getHeader("Accept-Language")); 
		System.out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding")); 
		System.out.println("User-Agent : " + request.getHeader("User-Agent")); 
		System.out.println("Connection : " + request.getHeader("Connection")); 
		System.out.println("Cookie : " + request.getHeader("Cookie"));
	}

}
