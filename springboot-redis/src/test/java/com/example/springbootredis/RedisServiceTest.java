package com.example.springbootredis;

import com.example.springbootredis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class RedisServiceTest {
    @Autowired
    private RedisService redisService;

    @Test
    public void testKeys(){
        Set set = redisService.getAllKeys();
        if(set.iterator().hasNext()){
            System.out.println(set.iterator().next());
        }
    }
}
