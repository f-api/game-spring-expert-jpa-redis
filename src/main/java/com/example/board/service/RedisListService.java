package com.example.board.service;

import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisListService {
    private static final Duration TTL = Duration.ofMinutes(5);
    private final StringRedisTemplate redisTemplate;

    public Long leftPush(
            String key,
            String value
    ) {
        Long size = redisTemplate.opsForList().leftPush(key, value);
        redisTemplate.expire(key, TTL);
        return size;
    }

    public Long rightPush(
            String key,
            String value
    ) {
        Long size = redisTemplate.opsForList().rightPush(key, value);
        redisTemplate.expire(key, TTL);
        return size;
    }

    public List<String> range(
            String key
    ) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public void set(
            String key,
            long index,
            String value
    ) {
        redisTemplate.opsForList().set(key, index, value);
        redisTemplate.expire(key, TTL);
    }

    public String leftPop(
            String key
    ) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public Long remove(
            String key,
            String value
    ) {
        return redisTemplate.opsForList().remove(key, 1, value);
    }
}
