package com.shang.schedule.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ：Shang
 * @date ：Created at 0008 2022/2/8 17:22
 * @description：
 * @version:
 */

@SpringBootConfiguration
public class RedisConfiguration {

    @Value("${redis.node.maxTotal}")
    private Integer maxTotal;
    @Value("${redis.node.host}")
    private String host;
    @Value("${redis.node.port}")
    private Integer port;

    public JedisPoolConfig jedisPoolConfig(){    //这个是修改redis性能的时候需要的对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        return jedisPoolConfig;
    }

    @Bean  //这个注解注入工厂的名称是方法名
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = jedisPoolConfig();
        return new JedisPool(jedisPoolConfig,host,port);
    }

}
