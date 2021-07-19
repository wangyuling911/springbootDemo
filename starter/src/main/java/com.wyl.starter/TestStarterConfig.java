package com.wyl.starter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestStarterConfig {

    @Bean
    public Yone yone() {
        return new Yone();
    }

}
