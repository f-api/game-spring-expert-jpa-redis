package com.example.board.service;

import java.time.Duration;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisHashService {
    private static final Duration TTL = Duration.ofMinutes(5);
    private final StringRedisTemplate redisTemplate;

    public void put(
            String key,
            String field,
            String value
    ) {
        redisTemplate.opsForHash().put(key, field, value);
        redisTemplate.expire(key, TTL);
    }

    public Object get(
            String key,
            String field
    ) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public Map<Object, Object> entries(
            String key
    ) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Long delete(
            String key,
            String field
    ) {
        return redisTemplate.opsForHash().delete(key, field);
    }
}
