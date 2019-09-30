package com.example.demo;
import com.example.demo.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/*@SpringBootApplication(scanBasePackages = {"com.example.demo"})*/
@EnableSpringConfigured
@SpringBootApplication

public class DemoApplication {
    @Autowired
    static Test t;
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

      /*  ExecutorService es = Executors.newFixedThreadPool(3);
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
*/

        Test t = ApplicationContextHolder.getContext().getBean(Test.class);

        //Long time1 = t.slowInsert(1000);

        Future<Long> time2 = t.quickInsert(10000);

        Long time = 0L;

        try {
             time =  time2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(t);
    }


}
