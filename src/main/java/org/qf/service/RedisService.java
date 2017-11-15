package org.qf.service;

public interface RedisService {

    void set(String key,Object value);

    Object get(String key);

    void del(String key);

    void lPush(String key,String value);
    Object rPop(String key);

    void rPush(String key,String value);
    Object lPop(String key);

    //设置有效期
    void setTime(String key,String value,Long second);

    //原子递增
    Long incr(String key);

    //原子递减
    Long decr(String key);

    //单独设置有效时间
    void expire(String key,int second);

    //查看剩余过期时间
    Long ttl(String key);

    //hash
    void hset(String key,String item,String value);
    Object hget(String key,String item);
    void hdel(String key,String item);
}
