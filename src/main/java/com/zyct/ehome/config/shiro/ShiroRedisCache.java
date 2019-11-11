package com.zyct.ehome.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.*;

/**
 * @author JGZ
 * @Classname ShiroRedisCache
 * @Date 2019/10/16 21:05
 * @Email 1945282561@qq.com
 */
public class ShiroRedisCache<K,V> implements Cache<K,V> {
    /**
     * key的前缀
     */
    private static final String REDIS_SHIRO_CACHE_KEY_PREFIX = "redis.shiro.cache_";
    /**
     * cache name
     */
    private String name;
    /**
     * redis操作对象
     */
    private RedisTemplate redisTemplate;
    /**
     * 序列化工具
     */
    private RedisSerializer serializer = new JdkSerializationRedisSerializer();
    /**
     * 存储key的redis.list的key值
     */
    private String keyListKey;


    public ShiroRedisCache() {
    }

    public ShiroRedisCache(String name, RedisTemplate redisTemplate) {
        this.name = name;
        this.redisTemplate = redisTemplate;
        this.keyListKey = "redis.shiro.cache.key_" + name;
    }


    @Override
    public V get(K key) throws CacheException {
        V result = (V) redisTemplate.opsForValue().get(generateKey(key));
        return result;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        V result = (V) redisTemplate.opsForValue().get(generateKey(key));
        redisTemplate.opsForValue().set(generateKey(key),value);
        redisTemplate.opsForList().rightPush(keyListKey,generateKey(key));
        RedisConnection redisConnection = null;
        return result;
    }

    @Override
    public V remove(K key) throws CacheException {
        V result = (V) redisTemplate.opsForValue().get(generateKey(key));
        redisTemplate.delete(generateKey(key));
        redisTemplate.opsForList().remove(keyListKey,0,generateKey(key));
        return result;
    }

    @Override
    public void clear() throws CacheException {
        Long size = redisTemplate.opsForList().size(keyListKey);
        if (size == 0){
            return;
        }

        List keyList = redisTemplate.opsForList().range(keyListKey, 0, size - 1);
        for (Object o : keyList) {
            redisTemplate.delete(o);
        }
        redisTemplate.delete(keyListKey);
        keyList.clear();

    }

    @Override
    public int size() {
        Long size = redisTemplate.opsForList().size(keyListKey);
        int i = Math.toIntExact(size);

        return i;
    }

    @Override
    public Set<K> keys() {
        Long size = redisTemplate.opsForList().size(keyListKey);
        Set<K> resultSet = new HashSet();
        if (size == 0){
            return resultSet;
        }
        List range = redisTemplate.opsForList().range(keyListKey, 0, size - 1);
        for (Object o : range) {
            resultSet.add((K) o);
        }

        return resultSet;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        Collection<V> vCollection = new ArrayList<>();
        for (K key : keys) {
            V v = get(key);
            vCollection.add(v);
        }
        return vCollection;
    }

    /**
     * 重组key
     * 区别其他使用环境的key
     *
     * @param key
     * @return
     */
    private String generateKey(K key) {
        return REDIS_SHIRO_CACHE_KEY_PREFIX + name + "_" + key;
    }
}
