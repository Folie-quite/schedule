/**
 * 
 *@author 作者 Your-Name: s
 *@version 创建时间：2019年3月1日 下午3:21:36 
 *
 */

package com.shang.schedule.utils;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JedisClientPool {

	@Autowired
	private JedisPool jedisPool;

	//private static ApplicationContext applicationContext;
	
	private JedisPool getJedisPool() {
		if (null == jedisPool) {
			//jedisPool = (JedisPool)applicationContext.getBean("jedisPool");
		}
		return jedisPool;
	}

	public void del(String key) {

		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.del(key.getBytes());
		}
	}

	public Object get(String key) {
		Object obj = new Object();
		try (Jedis jedis = getJedisPool().getResource()) {
			obj = SerializingUtil.deserialize(jedis.get(key.getBytes()));
		}
		return obj;
	}

	public Set<String> keys(String key) {
		Set<String> retVal = null;
		try (Jedis jedis = getJedisPool().getResource()) {
			Set<byte[]> objSet = jedis.keys(key.getBytes());
			if (null == objSet || objSet.size() == 0) {
				return null;
			}
			retVal = new HashSet<String>();
			for (byte[] btsource : objSet) {
				retVal.add(new String(btsource));
			}
		}
		return retVal;
	}

	public void set(String key, Object obj) {
		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.set(key.getBytes(), SerializingUtil.serialize(obj));
		}
	}

	public Long setnx(String key, Object obj) {
		try (Jedis jedis = getJedisPool().getResource()) {
			Long flag = jedis.setnx(key.getBytes(), SerializingUtil.serialize(obj));
			return flag;
		}
	}

	public void setex(String key, Object obj, int second) {
		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.setex(key.getBytes(), second, SerializingUtil.serialize(obj));
		}
	}

	public void sadd(String key, Object obj) {
		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.sadd(key.getBytes(), SerializingUtil.serialize(obj));
		}
	}

	public void srem(String key, Object obj) {
		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.srem(key.getBytes(), SerializingUtil.serialize(obj));
		}
	}

	public Set<Object> smembers(String key) {
		Set<Object> obj = new HashSet<Object>();
		try (Jedis jedis = getJedisPool().getResource()) {
			obj = SerializingUtil.deserialize(jedis.smembers(key.getBytes()));
		}
		return obj;
	}

	public boolean sismember(String key, Object value) {
		boolean flag = false;
		try (Jedis jedis = getJedisPool().getResource()) {
			flag = jedis.sismember(key.getBytes(), SerializingUtil.serialize(value));
		}
		return flag;
	}

	public void hset(String key, String field, Object value) {
		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.hset(key.getBytes(), field.getBytes(), SerializingUtil.serialize(value));
		}
	}

	public void zadd(String key, double score, String field) {
		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.zadd(key.getBytes(), score, field.getBytes());
		}
	}

	public void hdel(String key, String field) {
		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.hdel(key.getBytes(), field.getBytes());
		}
	}

	public void zrem(String key, String field) {
		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.zrem(key.getBytes(), field.getBytes());
		}
	}

	public Object hget(String key, String field) {
		Object obj = null;
		try (Jedis jedis = getJedisPool().getResource()) {
			obj = SerializingUtil.deserialize(jedis.hget(key.getBytes(), field.getBytes()));
		}
		return obj;
	}

	public double zscore(String key, String field) {
		double score = 0;
		try (Jedis jedis = getJedisPool().getResource()) {
			score = jedis.zscore(key.getBytes(), field.getBytes());
		}
		return score;
	}

	public Object[] zrangeByScore(String key, double from, double to) {
		Set<Object> sobj = null;
		try (Jedis jedis = getJedisPool().getResource()) {
			sobj = SerializingUtil.deserialize(jedis.zrangeByScore(key.getBytes(), from, to));
			if (sobj.size() > 0) {
				return sobj.toArray();
			} else {
				return null;
			}
		}
	}

	public void hmset(String key, Map<String, String> value) {
		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.hmset(key, value);
		}
	}

	public void incrBy(String key) {
		try (Jedis jedis = getJedisPool().getResource()) {
			jedis.incr(key.getBytes());
		}
	}

	public long getCounter(String key) {
		try (Jedis jedis = getJedisPool().getResource()) {
			byte[] v = jedis.get(key.getBytes());
			if (v != null) {
				return Long.parseLong(new String(v));
			} else {
				return 0l;
			}
		}
	}
}
