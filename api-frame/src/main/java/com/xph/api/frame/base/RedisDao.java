package com.xph.api.frame.base;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ovit.nswy.frame.common.redis.RedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by ${huipei.x} on 2017-11-14.
 */
@Component
public class RedisDao {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 发送频道消息
     */
    public void sendChannelMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }


    public Boolean exists(final Serializable key) {
        return redisTemplate.hasKey(key.toString());
    }


    public Long del(final Serializable... keys) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) {
                byte[][] ks = new byte[keys.length][];
                for (int i = 0; i < keys.length; i++) {
                    ks[i] = RedisSerializer.serialize(keys[i]);
                }
                return connection.del(ks);
            }
        });
    }


    public Boolean expire(final Serializable key, final long seconds) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) {
                return connection.expire(RedisSerializer.serialize(key), seconds);
            }
        });
    }


    public void set(final Serializable key, final Serializable value) {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) {
                byte[] k = RedisSerializer.serialize(key);
                byte[] v = RedisSerializer.serialize(value);
                connection.set(k, v);
                return true;
            }
        });
    }

    public <T extends Serializable> T get(final Serializable key, final Class<T> clazz) {
        return redisTemplate.execute(new RedisCallback<T>() {
            public T doInRedis(RedisConnection connection)  {
                byte[] k = RedisSerializer.serialize(key);
                byte[] result = connection.get(k);
                return RedisSerializer.deserialize(result, clazz);
            }
        });
    }

    public Boolean hashSet(final Serializable key, final Serializable field, final Serializable value) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)  {
                byte[] k = RedisSerializer.serialize(key);
                byte[] f = RedisSerializer.serialize(field);
                byte[] v = RedisSerializer.serialize(value);
                return connection.hSet(k, f, v);
            }
        });
    }


    public <T extends Serializable> T hashGet(final Serializable key, final Serializable field, final Class<T> clazz) {
        return redisTemplate.execute(new RedisCallback<T>() {
            public T doInRedis(RedisConnection connection)  {
                byte[] k = RedisSerializer.serialize(key);
                byte[] f = RedisSerializer.serialize(field);
                byte[] result = connection.hGet(k, f);
                return RedisSerializer.deserialize(result, clazz);
            }
        });
    }


    public <T extends Serializable> Set<T> hashKeys(final Serializable key, final Class<T> clazz) {
        return redisTemplate.execute(new RedisCallback<Set<T>>() {
            public Set<T> doInRedis(RedisConnection connection) {
                byte[] k = RedisSerializer.serialize(key);
                Set<byte[]> result = connection.hKeys(k);
                Set<T> sets = Sets.newHashSet();
                if (result != null && result.size() > 0) {
                    for (byte[] b : result) {
                        T t = RedisSerializer.deserialize(b, clazz);
                        sets.add(t);
                    }
                }
                return sets;
            }
        });
    }


    public <T extends Serializable> List<T> hashVals(final Serializable key, final Class<T> clazz) {
        return redisTemplate.execute(new RedisCallback<List<T>>() {
            public List<T> doInRedis(RedisConnection connection) {
                byte[] k = RedisSerializer.serialize(key);
                List<byte[]> result = connection.hVals(k);
                List<T> sets = Lists.newArrayList();
                if (result != null && result.size() > 0) {
                    for (byte[] b : result) {
                        T t = RedisSerializer.deserialize(b, clazz);
                        sets.add(t);
                    }
                }
                return sets;
            }
        });
    }


}
