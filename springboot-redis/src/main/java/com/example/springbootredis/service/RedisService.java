package com.example.springbootredis.service;

import org.springframework.stereotype.Service;

import java.util.Set;

public interface RedisService {
    public Set getAllKeys();

    /**
     * redis实现分布式锁
     * @return
     */
    public void redisLock();
}
