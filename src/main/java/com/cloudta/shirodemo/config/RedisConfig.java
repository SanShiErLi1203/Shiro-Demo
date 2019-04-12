package com.cloudta.shirodemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
@Configuration
public class RedisConfig {

    private static Logger LOG = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * 使用@Value值注入值时，切记莫不可以使用包装类或者static（类级别）关键字
     */
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.lettuce.pool.max-wait}")
    private long maxWaitMillis;


    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        return new JedisPoolConfig();
    }

    /**
     * JedisPool（连接池）
     * JedisPool.getResource()方法 --> 得到Jedis（一个操作连接）
     * @param jedisPoolConfig
     * @return
     */
    @Bean("redisPool")
    public JedisPool redisPoolFactory(@Autowired JedisPoolConfig jedisPoolConfig){
        LOG.info("Come IN RedisConfig.redisPoolFactory()");
        LOG.info("redis's host = {} , port = {} , timeout = {}, password = {}" , host , port, timeout, password);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host, port, timeout, password);
        return jedisPool;
    }

}
