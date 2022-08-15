package com.msb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by 金喆
 */

@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String ,Object> redisTemplate(RedisConnectionFactory factory)
    {
        RedisTemplate<String , Object> redisTemplate = new RedisTemplate<String , Object>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

}
