package com.example.demo.service;

import com.example.demo.model.LogFormater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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

/*    @Bean(name = "multipartResolver")
    public MultipartResolver resolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(-1);
        return multipartResolver;
    }*/
}
