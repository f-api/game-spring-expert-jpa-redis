package com.example.board.controller;

import com.example.board.service.RedisHashService;
import com.example.board.service.RedisListService;
import com.example.board.service.RedisSetService;
import com.example.board.service.RedisSortedSetService;
import com.example.board.service.RedisStringService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisTypeController {
    private final RedisStringService stringService;
    private final RedisHashService hashService;
    private final RedisListService listService;
    private final RedisSetService setService;
    private final RedisSortedSetService sortedSetService;

    @PostMapping("/redis/strings")
    public void setString(
            @RequestParam String key,
            @RequestParam String value
    ) {
        stringService.set(key, value);
    }

    @GetMapping("/redis/strings")
    public String getString(
            @RequestParam String key
    ) {
        return stringService.get(key);
    }

    @DeleteMapping("/redis/strings")
    public Boolean deleteString(
            @RequestParam String key
    ) {
        return stringService.delete(key);
    }

    @PostMapping("/redis/strings/increment")
    public Long increment(
            @RequestParam String key
    ) {
        return stringService.increment(key);
    }

    @PostMapping("/redis/hashes")
    public void putHash(
            @RequestParam String key,
            @RequestParam String field,
            @RequestParam String value
    ) {
        hashService.put(key, field, value);
    }

    @GetMapping("/redis/hashes/field")
    public Object getHashField(
            @RequestParam String key,
            @RequestParam String field
    ) {
        return hashService.get(key, field);
    }

    @GetMapping("/redis/hashes")
    public Map<Object, Object> getHash(
            @RequestParam String key
    ) {
        return hashService.entries(key);
    }

    @DeleteMapping("/redis/hashes")
    public Long deleteHash(
            @RequestParam String key,
            @RequestParam String field
    ) {
        return hashService.delete(key, field);
    }

    @PostMapping("/redis/lists/left-push")
    public Long leftPush(
            @RequestParam String key,
            @RequestParam String value
    ) {
        return listService.leftPush(key, value);
    }

    @PostMapping("/redis/lists/right-push")
    public Long rightPush(
            @RequestParam String key,
            @RequestParam String value
    ) {
        return listService.rightPush(key, value);
    }

    @GetMapping("/redis/lists")
    public List<String> getList(
            @RequestParam String key
    ) {
        return listService.range(key);
    }

    @PutMapping("/redis/lists")
    public void setList(
            @RequestParam String key,
            @RequestParam long index,
            @RequestParam String value
    ) {
        listService.set(key, index, value);
    }

    @PostMapping("/redis/lists/left-pop")
    public String leftPop(
            @RequestParam String key
    ) {
        return listService.leftPop(key);
    }

    @DeleteMapping("/redis/lists")
    public Long removeList(
            @RequestParam String key,
            @RequestParam String value
    ) {
        return listService.remove(key, value);
    }

    @PostMapping("/redis/sets")
    public Long addSet(
            @RequestParam String key,
            @RequestParam String value
    ) {
        return setService.add(key, value);
    }

    @GetMapping("/redis/sets")
    public Set<String> getSet(
            @RequestParam String key
    ) {
        return setService.members(key);
    }

    @GetMapping("/redis/sets/contains")
    public Boolean containsSet(
            @RequestParam String key,
            @RequestParam String value
    ) {
        return setService.contains(key, value);
    }

    @DeleteMapping("/redis/sets")
    public Long removeSet(
            @RequestParam String key,
            @RequestParam String value
    ) {
        return setService.remove(key, value);
    }

    @PostMapping("/redis/sorted-sets")
    public Boolean addSortedSet(
            @RequestParam String key,
            @RequestParam String member,
            @RequestParam double score
    ) {
        return sortedSetService.add(key, member, score);
    }

    @GetMapping("/redis/sorted-sets")
    public Set<String> getRanking(
            @RequestParam String key
    ) {
        return sortedSetService.ranking(key);
    }

    @GetMapping("/redis/sorted-sets/score")
    public Double getScore(
            @RequestParam String key,
            @RequestParam String member
    ) {
        return sortedSetService.score(key, member);
    }

    @DeleteMapping("/redis/sorted-sets")
    public Long removeSortedSet(
            @RequestParam String key,
            @RequestParam String member
    ) {
        return sortedSetService.remove(key, member);
    }
}
