package com.example.demo.service;

import com.example.demo.model.LogFormater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfig {
    @Bean
    public LogFormater logFormater(){
        LogFormater f = new LogFormater();
        return f;
    }
    @Bean
    public FIleLoggerService  fIleLoggerService(LogFormater hello){
        return FIleLoggerService.getInstance();
    }
}
