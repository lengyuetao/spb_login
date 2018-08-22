package com.example.spblogin.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final long EXPIRE_TIME=300l;

    @Resource
    private RedisTemplate redisTemplate;

    public Object get(String key){
        Object o=redisTemplate.opsForValue().get(key);
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value,EXPIRE_TIME, TimeUnit.SECONDS);
    }

    public void set(String key,Object value,long times){
        redisTemplate.opsForValue().set(key,value,times, TimeUnit.SECONDS);
    }

    public boolean del(String key){
        return redisTemplate.delete(key);
    }

    /**
     * 命令以秒为单位返回 key 的剩余过期时间。
     * @param key
     * @return
     */
    public long ttl(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 指定key在指定的日期过期
     * @param key
     * @param date
     */
    public void exprieKeyAt(String key,Date date){
        redisTemplate.expireAt(key,date);
    }

    /**
     * 初始化自增值
     * @param key
     * @param value
     */
    public void initIncr(String key,Integer value,long exprieTime){
        RedisAtomicInteger i=new RedisAtomicInteger(key,redisTemplate.getConnectionFactory());
        i.set(value);
        i.expire(exprieTime,TimeUnit.SECONDS);
    }

    /**
     * 自增
     * @param key
     * @return
     */
    public long incr(String key) {
        RedisAtomicInteger counter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
        return counter.incrementAndGet();
    }

    /**
     * 指定递增的值
     * @param key
     * @param increment
     * @return
     */
    public long incr(String key,int increment) {
        RedisAtomicInteger counter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
        return counter.addAndGet(increment);
    }

    /**
     * 递减
     * @param key
     * @return
     */
    public long decr(String key) {
        RedisAtomicInteger counter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
        return counter.decrementAndGet();
    }


}
