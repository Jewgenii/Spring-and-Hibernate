package com.example.demo;

import com.example.demo.db.TestServiceImpl;
import com.example.demo.service.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public PersonInfo personInfo(){
        return new PersonInfo(30,"Jack");
    }

   @Bean
    public TestService testService() {
       return new TestServiceImpl();
   }
}
