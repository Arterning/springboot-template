package cn.ning.springboot.starter.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

@Service
public class CacheService {

    @Resource
    private RedisTemplate redisTemplate;

    public String putCache(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        return value;
    }
}
