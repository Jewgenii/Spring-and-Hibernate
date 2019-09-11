package com.example.demo.service;

import com.example.demo.model.LogFormater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public LogFormater logFormater(){
        return new LogFormater("");
    }
}
