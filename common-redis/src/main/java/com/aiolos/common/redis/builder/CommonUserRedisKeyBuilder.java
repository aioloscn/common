package com.aiolos.common.redis.builder;

import com.aiolos.common.redis.RedisKeyBuilder;
import com.aiolos.common.redis.RedisKeyProperties;
import org.springframework.stereotype.Component;

@Component
public class CommonUserRedisKeyBuilder extends RedisKeyBuilder {

    private static final String USER_INFO_KEY = "userInfo";
    private static final String USER_TOKEN_KEY = "userToken";
    private static final String ANONYMOUS_ID_KEY = "anonymousId";
    private static final String ANONYMOUS_DEVICE_ID_KEY = "anonymousDeviceId";

    public CommonUserRedisKeyBuilder(RedisKeyProperties redisKeyProperties) {
        super(redisKeyProperties);
    }

    public String buildUserInfoKey(Long userId) {
        return USER_INFO_KEY + super.getSplit() + userId;
    }
    
    public String buildUserTokenKey(String token) {
        return USER_TOKEN_KEY + super.getSplit() + token;
    }
    
    public String buildAnonymousIdKey(String deviceId) {
        return ANONYMOUS_ID_KEY + super.getSplit() + deviceId;
    }
    
    public String buildAnonymousDeviceIdKey(Long anonymousId) {
        return ANONYMOUS_DEVICE_ID_KEY + super.getSplit() + anonymousId;
    }
}
