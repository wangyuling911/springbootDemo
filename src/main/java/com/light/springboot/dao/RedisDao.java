package com.light.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisDao {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value) {


    }

    public String get(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        this.stringRedisTemplate.delete(key);
    }
}
