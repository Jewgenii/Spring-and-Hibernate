package com.example.demo.service;

import com.example.demo.model.LogFormater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;

@Configuration
public class MyConfig {
    @Bean
    public LogFormater logFormater(){
        LogFormater f = new LogFormater();
        return f;
    }
    @Bean
    public MyLogger fIleLoggerService(LogFormater hello){
        return FIleLoggerService.getInstance();
    }

/*    @Bean(name = "multipartResolver")
    public MultipartResolver resolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(-1);
        return multipartResolver;
    }*/

/*   @Bean
    public DataSource dataSource(){
       // in case of the absence of the configuration in application.properties or .yaml
       DriverManagerDataSource dataSource = new DriverManagerDataSource();

       dataSource.setDriverClassName("org.postgresql.Driver");
       dataSource.setUsername("postgres");
       dataSource.setPassword("1");
       dataSource.setUrl(
               "jdbc:postgresql://localhost:5432/postgres");

       return dataSource;
   }*/

}
