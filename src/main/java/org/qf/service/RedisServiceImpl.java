package org.qf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void set(String key, Object value) {

        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value);

    }

    @Override
    public Object get(String key) {

        ValueOperations valueOperations = redisTemplate.opsForValue();

        return valueOperations.get(key);
    }

    @Override
    public void del(String key) {

        redisTemplate.delete(key);
    }

    @Override
    public void lPush(String key, String value) {

        ListOperations listOperations = redisTemplate.opsForList();
        Long aLong = listOperations.leftPush(key, value);
        System.out.println(aLong);
    }

    @Override
    public Object rPop(String key) {

        ListOperations listOperations = redisTemplate.opsForList();
        Object o = listOperations.rightPop(key);
        return o;
    }

    @Override
    public void rPush(String key, String value) {

        ListOperations listOperations = redisTemplate.opsForList();
        Long aLong = listOperations.rightPush(key, value);
        System.out.println(aLong);

    }

    @Override
    public Object lPop(String key) {

        ListOperations listOperations = redisTemplate.opsForList();
        return listOperations.leftPop(key);
    }

    @Override
    public void setTime(String key, String value, Long second) {

       ValueOperations valueOperations = redisTemplate.opsForValue();
       valueOperations.set(key,value,second, TimeUnit.SECONDS);

    }

    @Override
    public Long incr(String key) {

        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.increment(key,1);
    }

    @Override
    public Long decr(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.increment(key,-1);
    }

    @Override
    public void expire(String key, int second) {

        Boolean expire = redisTemplate.expire(key,second,TimeUnit.SECONDS);
        System.out.println(expire);

    }

    @Override
    public Long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public void hset(String key, String item, String value) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key,item,value);
    }

    @Override
    public Object hget(String key, String item) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key,item);
    }

    @Override
    public void hdel(String key, String item) {

        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key,item);
    }
}
