package com.aiolos.common.redis.builder;

import com.aiolos.common.redis.RedisKeyBuilder;
import com.aiolos.common.redis.RedisKeyProperties;
import org.springframework.stereotype.Component;

@Component
public class CommonSmsRedisKeyBuilder extends RedisKeyBuilder {

    private static final String SMS_LOGIN_CODE_KEY = "sms:loginCode";
    
    public CommonSmsRedisKeyBuilder(RedisKeyProperties redisKeyProperties) {
        super(redisKeyProperties);
    }

    public String buildSmsLoginCodeKey(String phone) {
        return SMS_LOGIN_CODE_KEY + super.getSplit() + phone;
    }
}
