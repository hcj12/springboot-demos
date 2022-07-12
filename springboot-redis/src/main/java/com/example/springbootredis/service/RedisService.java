package com.example.springbootredis.service;

import org.springframework.stereotype.Service;

import java.util.Set;

public interface RedisService {
    public Set getAllKeys();
}
