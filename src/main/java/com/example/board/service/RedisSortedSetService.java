package com.example.board.service;

import java.time.Duration;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSortedSetService {
    private static final Duration TTL = Duration.ofMinutes(5);
    private final StringRedisTemplate redisTemplate;

    public Boolean add(
            String key,
            String member,
            double score
    ) {
        Boolean added = redisTemplate.opsForZSet().add(key, member, score);
        redisTemplate.expire(key, TTL);
        return added;
    }

    public Set<String> ranking(
            String key
    ) {
        return redisTemplate.opsForZSet().reverseRange(key, 0, -1);
    }

    public Double score(
            String key,
            String member
    ) {
        return redisTemplate.opsForZSet().score(key, member);
    }

    public Long remove(
            String key,
            String member
    ) {
        return redisTemplate.opsForZSet().remove(key, member);
    }
}
