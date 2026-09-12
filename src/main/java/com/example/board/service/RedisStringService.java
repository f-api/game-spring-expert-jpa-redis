package com.example.board.service;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisStringService {
    private static final Duration TTL = Duration.ofMinutes(5);
    private final StringRedisTemplate redisTemplate;

    public void set(
            String key,
            String value
    ) {
        redisTemplate.opsForValue().set(key, value, TTL);
    }

    public String get(
            String key
    ) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean delete(
            String key
    ) {
        return redisTemplate.delete(key);
    }

    public Long increment(
            String key
    ) {
        Long count = redisTemplate.opsForValue().increment(key);
        redisTemplate.expire(key, TTL);
        return count;
    }
}
