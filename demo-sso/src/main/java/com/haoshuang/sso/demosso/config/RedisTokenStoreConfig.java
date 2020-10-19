package com.haoshuang.sso.demosso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class RedisTokenStoreConfig {

    @Bean
    public RedisTokenStore initRedisTokenStore(RedisConnectionFactory redisConnectionFactory){
        return new RedisTokenStore(redisConnectionFactory);
    }
}
