package com.cloudta.shirodemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JackJson处理配置类
 */
@Configuration
public class JsonConfig {

    @Bean("objectMapper")
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
