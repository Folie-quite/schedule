/**
 * 
 *@author 作者 Your-Name: s
 *@version 创建时间：2019年3月1日 下午3:34:38 
 *
 */

package com.shang.schedule.jedis;

import java.util.Set;

public interface JedisService {

	Set<String> keys(String key);

	Object get(String key);

	void set(String key, Object value);

	Long setnx(String key, Object value);

	void setex(String key, int expried, Object value);

	void del(String key);

	void sadd(String key, Object value);

	void srem(String key, Object value);

	boolean sismember(String key, Object value);

	Set<Object> smembers(String key);

	void hset(String key, String field, Object value);

	void zadd(String key, double Long, String field);

	void hdel(String key, String field);

	void zrem(String key, String field);

	double zscore(String key, String field);

	Object hget(String key, String field);

	Object[] zrangeByScore(String key, double from, double to);

	void incr(String key);

	long getCounter(String key);
}
