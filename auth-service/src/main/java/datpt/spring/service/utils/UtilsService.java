package datpt.spring.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class UtilsService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void setKeyRedis(String key, String value, long ttl) {
        redisTemplate.opsForValue().set(
                key,
                value,
                ttl
        );
    }

    public void isHasKeyRedis(String key) {
        redisTemplate.hasKey(key);
    }
}
