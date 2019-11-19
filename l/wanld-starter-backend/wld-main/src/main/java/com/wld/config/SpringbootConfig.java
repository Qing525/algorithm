package com.wld.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * 重写springboot的配置类
 *
 * @author wangzg
 */
@Configuration
public class SpringbootConfig {

    /**
     * Controller 反回对象： 返回值序列化时，将long默认转成string返回前端。
     *
     * @return Jackson2ObjectMapperBuilderCustomizer
     */
    @Primary
    @Bean()
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
        };
    }
}

