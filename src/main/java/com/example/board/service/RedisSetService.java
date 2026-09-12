package com.example.board.service;

import java.time.Duration;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSetService {
    private static final Duration TTL = Duration.ofMinutes(5);
    private final StringRedisTemplate redisTemplate;

    public Long add(
            String key,
            String value
    ) {
        Long added = redisTemplate.opsForSet().add(key, value);
        redisTemplate.expire(key, TTL);
        return added;
    }

    public Set<String> members(
            String key
    ) {
        return redisTemplate.opsForSet().members(key);
    }

    public Boolean contains(
            String key,
            String value
    ) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public Long remove(
            String key,
            String value
    ) {
        return redisTemplate.opsForSet().remove(key, value);
    }
}
