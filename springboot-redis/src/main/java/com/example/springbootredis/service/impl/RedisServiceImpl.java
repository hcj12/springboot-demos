package com.example.springbootredis.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.springbootredis.service.RedisService;
import com.example.springbootredis.utils.RedisUtil;
import jodd.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;

/**
 * redis实现类
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Redisson redisson;

    private String redisLock = "redis-lock";

    private String key = "goods-00001";
    @Override
    public Set getAllKeys() {
        return redisUtil.getAllKeys();
    }

    @Override
    public void redisLock() {
        if(ObjectUtil.isNull(redisLock)){
            log.info("redis锁不存在");
            return;
        }
        RLock lock;
        try {
            Object o = redisUtil.get(redisLock);
            lock = redisson.getLock(redisLock);
        }catch (Exception e){
            log.info("获取不到lock");
            e.printStackTrace();
            return;
        }
        lock.lock(100, TimeUnit.MILLISECONDS);
        try{
            //模拟库存逻辑，需要做分布式锁来扣库存
            int numbers= Integer.valueOf(String.valueOf(redisUtil.get(key)));
            //模拟扣库存
            log.info("当前库存还剩余-{}",numbers);
            if(numbers>0){
                Integer currentNum=numbers-1;
                redisUtil.set(key,currentNum+"");
                log.info("扣库存成功，当前库存还剩余-{}",currentNum);
            }else{
                log.info("库存不足，扣减失败");
            }
        }catch (Exception e){
            log.info("库存逻辑存在问题，望知悉");
        }finally {
            if(lock.isLocked() && lock.isHeldByCurrentThread()){
                //注意这里的逻辑
                lock.unlock();
            }
        }
    }
}
