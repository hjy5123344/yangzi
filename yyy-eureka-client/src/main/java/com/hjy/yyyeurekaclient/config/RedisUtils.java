package com.hjy.yyyeurekaclient.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RedisUtils {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("写入缓存失败");
        }
        return result;
    }

    public void set(String key, String... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Object getForSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public void setHash(String key,Object hashKey,Object value) {
        redisTemplate.opsForHash().put(key,hashKey,value);
    }

    public Object getHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    public void setAllHash(String key, Map map) {
        redisTemplate.opsForHash().putAll(key,map);
    }



    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("写入缓存失败");
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
