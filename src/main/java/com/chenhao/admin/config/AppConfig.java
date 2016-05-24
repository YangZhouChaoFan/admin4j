package com.chenhao.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
        @PropertySource("classpath:jdbc.properties"),
        @PropertySource("classpath:log4j.properties"),
        @PropertySource("classpath:redis.properties"),
        @PropertySource("classpath:activemq.properties")})
public class AppConfig {
    @Autowired
    Environment env;

}