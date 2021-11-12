package com.yevheniimakar.beltcutting.config;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class TikaConfig {

    @Bean
    public Tika tika() {
        return new Tika();
    }

}
