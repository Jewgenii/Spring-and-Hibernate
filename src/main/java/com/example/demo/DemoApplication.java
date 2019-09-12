package com.example.demo;
import com.example.demo.model.LogFormater;
import com.example.demo.model.Person;
import com.example.demo.model.TestIoc;
import com.example.demo.model.User;
import com.example.demo.service.MyConfig;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;
import java.util.Properties;


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

    }
}
