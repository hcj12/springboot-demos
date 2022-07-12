package com.example.springbootredis.service.impl;

import com.example.springbootredis.service.RedisService;
import com.example.springbootredis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public Set getAllKeys() {
        return redisUtil.getAllKeys();
    }
}
