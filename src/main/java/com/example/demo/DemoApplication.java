package com.example.demo;
import com.example.demo.model.Person;
import com.example.demo.model.User;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.objenesis.instantiator.ObjectInstantiator;

import java.io.*;
import java.util.Properties;


/*@SpringBootApplication(scanBasePackages = {"com.example.demo"})*/

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

        /*
        //testing serialization and deserialization
        if (args.length>0)
            System.out.print(args[0]);
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("./tetsObj.txt"));
            com.example.demo.model.User u = new com.example.demo.model.User("user");
            stream.writeObject(u);
            stream.close();
            ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("./tetsObj.txt"));
            u =  (com.example.demo.model.User) inStream.readObject();

            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
