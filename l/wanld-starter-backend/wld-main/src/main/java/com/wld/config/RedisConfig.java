package com.wld.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedisConfig {


//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                //设置redis的过期时间
//                .entryTtl(Duration.ofSeconds(30))
//                .disableCachingNullValues();
//
//        RedisCacheManager cm = RedisCacheManager.builder(connectionFactory)
//                .cacheDefaults(config)
//                .transactionAware()
//                .build();
//
//        return cm;
//    }


}
