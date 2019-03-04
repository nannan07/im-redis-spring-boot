package com.allmsi.cache.redis.service.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.allmsi.cache.redis.service.RedisCacheService;

/**
 * redis cache
 * 
 * @author sunnannan
 *
 */
@Service("com.allmsi.cache.redis.RedisCache")
public class RedisCache implements RedisCacheService {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean setString(String key, String value) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		vo.set(key, value);
		return true;
	}

	@Override
	public boolean setString(String key, String value, int seconds) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		vo.set(key, value, seconds, TimeUnit.SECONDS);
		return true;
	}

	@Override
	public boolean setObject(String key, Object obj) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		vo.set(key, obj);
		return true;
	}

	@Override
	public boolean setObject(String key, Object obj, int seconds) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		vo.set(key, obj, seconds, TimeUnit.SECONDS);
		return true;
	}

	@Override
	public String getString(String key) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		return (String) vo.get(key);
	}

	@Override
	public Object getObject(String key) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		return vo.get(key);
	}

	@Override
	public boolean isExists(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public boolean del(String key) {
		redisTemplate.delete(key);
		return true;
	}

	@Override
	public long expire(String key, int seconds) {
		boolean flag = redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
		if (flag) {
			return 1;
		}
		return 0;
	}

	@Override
	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}
	
	public void getTime(String key) {
		System.err.println("1---"+redisTemplate.getExpire(key));
		System.err.println("2---"+redisTemplate.getExpire(key,TimeUnit.SECONDS));
	}

}
