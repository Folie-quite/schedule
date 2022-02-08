/**
 * 
 *@author 作者 Your-Name: s
 *@version 创建时间：2019年3月1日 下午3:35:31 
 *
 */

package com.shang.schedule.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JedisServiceImpl implements JedisService {

	private boolean isPro() {
		return false;
	}

	@Autowired
	private JedisClientPool jedisSentinelClient;
	@Autowired
	private JedisClientPool jedisClient;

	@Override
	public Object get(String key) {
		try {
			if (isPro()) {
				return jedisSentinelClient.get(key);
			} else {
				Object object = jedisClient.get(key);
				return object;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 此处日志记录过于频繁，缓存没命中不影响程序正常运�?
			// logger.error(
			// "Exception occur when getCached value with key=" + key, e);
			return null;
		}
	}

	@Override
	public void set(String key, Object value) {
		if (null == key || null == value) {
			return;
		}
		if (isPro()) {
			jedisSentinelClient.set(key, value);
		} else {
			jedisClient.set(key, value);
		}

	}

	@Override
	public Long setnx(String key, Object value) {
		if (null == key || null == value) {
			return 0L;
		}
		if (isPro()) {
			return jedisSentinelClient.setnx(key, value);
		} else {
			return jedisClient.setnx(key, value);
		}
	}

	@Override
	public void setex(String key, int seconds, Object value) {
		if (null == key || null == value) {
			return;
		}
		
		if (isPro()) {
			if (seconds <= 0) {
				jedisSentinelClient.set(key, value);
			} else {
				jedisSentinelClient.setex(key, value, seconds);
			}
		} else {
			if (seconds <= 0) {
				jedisClient.set(key, value);
			} else {
				jedisClient.setex(key, value, seconds);
			}
		}
	}

	@Override
	public void del(String key) {
		try {
			if (isPro()) {
				jedisSentinelClient.del(key);
			} else {
				jedisClient.del(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("Exception occur when removeCached value with key="
			// + key, e);
		}
	}

	@Override
	public void sadd(String key, Object value) {
		if (null == key || null == value) {
			return;
		}
		if (isPro()) {
			jedisSentinelClient.sadd(key, value);
		} else {
			jedisClient.sadd(key, value);
		}

	}

	@Override
	public Set<Object> smembers(String key) {
		if (null == key) {
			return null;
		}
		if (isPro()) {
			return jedisSentinelClient.smembers(key);
		} else {
			return jedisClient.smembers(key);
		}
	}

	@Override
	public void srem(String key, Object value) {
		if (null == key || null == value) {
			return;
		}
		if (isPro()) {
			jedisSentinelClient.srem(key, value);
		} else {
			jedisClient.srem(key, value);
		}
	}

	@Override
	public boolean sismember(String key, Object value) {
		if (isPro()) {
			return jedisSentinelClient.sismember(key, value);
		} else {
			return jedisClient.sismember(key, value);
		}
	}

	@Override
	public void hset(String key, String field, Object value) {
		if (null == key || null == field || null == value) {
			return;
		}
		if (isPro()) {
			jedisSentinelClient.hset(key, field, value);
		} else {
			jedisClient.hset(key, field, value);
		}

	}

	@Override
	public void zadd(String key, double score, String field) {
		if (null == key || null == field) {
			return;
		}
		if (isPro()) {
			jedisSentinelClient.zadd(key, score, field);
		} else {
			jedisClient.zadd(key, score, field);
		}
	}

	@Override
	public void hdel(String key, String field) {
		if (null == key || null == field) {
			return;
		}
		if (isPro()) {
			jedisSentinelClient.hdel(key, field);
		} else {
			jedisClient.hdel(key, field);
		}
	}

	@Override
	public void zrem(String key, String field) {
		if (null == key || null == field) {
			return;
		}
		if (isPro()) {
			jedisSentinelClient.zrem(key, field);
		} else {
			jedisClient.zrem(key, field);
		}

	}

	@Override
	public Object hget(String key, String field) {
		if (null == key || null == field) {
			return null;
		}
		if (isPro()) {
			return jedisSentinelClient.hget(key, field);
		} else {
			return jedisClient.hget(key, field);
		}
	}

	@Override
	public double zscore(String key, String field) {
		if (null == key || null == field) {
			return 0;
		}
		if (isPro()) {
			return jedisSentinelClient.zscore(key, field);
		} else {
			return jedisClient.zscore(key, field);
		}
	}

	@Override
	public Object[] zrangeByScore(String key, double from, double to) {
		if (null == key) {
			return null;
		}
		if (isPro()) {
			return jedisSentinelClient.zrangeByScore(key, from, to);
		} else {
			return jedisClient.zrangeByScore(key, from, to);
		}
	}

	@Override
	public void incr(String key) {
		if (null == key) {
			return;
		}
		if (isPro()) {
			jedisSentinelClient.incrBy(key);
		} else {
			jedisClient.incrBy(key);
		}
	}

	@Override
	public long getCounter(String key) {
		if (null == key) {
			return 0l;
		}
		if (isPro()) {
			return jedisSentinelClient.getCounter(key);
		} else {
			return jedisClient.getCounter(key);
		}
	}

	@Override
	public Set<String> keys(String key) {

		if (null == key) {
			return null;
		}

		if (isPro()) {
			return jedisSentinelClient.keys(key);
		} else {
			return jedisClient.keys(key);
		}
	}


}
