package com.example.demo;
import com.example.demo.model.*;
import com.example.demo.service.MyConfig;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/*@SpringBootApplication(scanBasePackages = {"com.example.demo"})*/

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

        //testing serialization and deserialization
     /*   if (args.length>0)
            System.out.print(args[0]);
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(bout);
            com.example.demo.model.User u = new com.example.demo.model.User("user");
            stream.writeObject(u);
            stream.close();

            byte[] arr =  bout.toByteArray();
            ObjectInputStream inStream = new ObjectInputStream(new ByteArrayInputStream(arr));
            u =  (com.example.demo.model.User) inStream.readObject();

            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

/*
        AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext(MyConfig.class);
        con.register(LogFormater.class);
        LogFormater l = con.getBean(LogFormater.class);
        System.out.println(l.getItemToLog());*/

     /*   Threadspeedtester t = new Threadspeedtester();
        t.testArrayList();
        t.testVector();
        */
/*        Properties p = System.getProperties();

        Iterator<Object> iter = p.keySet().iterator();
        while(iter.hasNext()){
            String key = (String)iter.next();
            String prop = p.getProperty(key);
            System.out.println(key+":"+prop);
        }*/

        ExecutorService es = Executors.newFixedThreadPool(3);
        Long startTime = System.currentTimeMillis();
        Future<String> fs = es.submit(new Threadspeedtester());

        try {
            //es.awaitTermination(2500, TimeUnit.MILLISECONDS);
            Long endTime = System.currentTimeMillis();
            //fs.get(2100, TimeUnit.MILLISECONDS)

            System.out.println("no in thread : " + (endTime - startTime) + " ms and val " + fs.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Threadspeedtester t = new Threadspeedtester();
        Person p = new Person();
        t.foo(p);
    }
}
