/*package com.jzeen.travel.admin.setting;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPoolConfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jzeen.travel.admin.setting.Redisconn.Pool;
//缓存管理
@Configuration  
@EnableCaching
public class RedisSetting extends CachingConfigurerSupport{
	@Autowired
	Redisconn conn;
	*//**
	 * 生成key的策略
	 * @return
	 *//*
	@Bean  
	    public KeyGenerator wiselyKeyGenerator(){  
	        return new KeyGenerator() {  
	            @Override  
	            public Object generate(Object target, Method method, Object... params) {  
	                StringBuilder sb = new StringBuilder();  
	                sb.append(target.getClass().getName());  
	                sb.append(method.getName());  
	                for (Object obj : params) {  
	                    sb.append(obj.toString());  
	                }  
	                return sb.toString();  
	            }
	        };  
	  
	    }  
	  @Bean
	  public JedisConnectionFactory redisConnectionFactory() {
	      JedisConnectionFactory factory = new JedisConnectionFactory();
	      factory.setHostName(conn.getHost());
	      factory.setPort(conn.getPort());
	      factory.setTimeout(conn.getTimeout()); //设置连接超时时间
	      JedisPoolConfig config=new JedisPoolConfig();
	      Pool pool = conn.getPool();
	      config.setMaxIdle(pool.getMaxIdle());
	      config.setMaxWaitMillis(pool.getMaxWait());
	      config.setMaxTotal(pool.getMaxActive());
	      factory.setPoolConfig(config);
	      return factory;
	  }
	    @Bean  
	    public CacheManager cacheManager(RedisTemplate redisTemplate) {  
	        return new RedisCacheManager(redisTemplate);  
	    }  
	  *//**
	   * RedisTemplate配置
	   * @param factory
	   * @return
	   *//*
	    @Bean  
	    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
	        StringRedisTemplate template = new StringRedisTemplate(factory);
	        setSerializer(template); //设置序列化工具，这样ReportBean不需要实现Serializable接口
	        template.afterPropertiesSet();
	        return template;
	    } 
	    private void setSerializer(StringRedisTemplate template) {
	        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
	        ObjectMapper om = new ObjectMapper();
	        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	        jackson2JsonRedisSerializer.setObjectMapper(om);
	        template.setValueSerializer(jackson2JsonRedisSerializer);
	    }
}
*/