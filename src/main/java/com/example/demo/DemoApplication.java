package com.example.demo;
import com.example.demo.model.*;
import com.example.demo.service.MyConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.*;


/*@SpringBootApplication(scanBasePackages = {"com.example.demo"})*/
@EnableSpringConfigured
@SpringBootApplication

public class DemoApplication {
    @Autowired
    static Test t;

    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

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

        AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext(MyConfig.class);
        con.register(LogFormater.class);
        LogFormater l = con.getBean(LogFormater.class);
        System.out.println(l.getItemToLog());
/*
        AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext(MyConfig.class);
        con.register(LogFormater.class);
        LogFormater l = con.getBean(LogFormater.class);
        System.out.println(l.ge tItemToLog());*/

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



      // Testing parallelism !!!!!
/*
        Logger logger = Logger.getLogger(DemoApplication.class.getName());
        Handler fh = null;
        try {
             fh = new FileHandler("log%?.txt",1024*1024,20);
             fh.setLevel(Level.WARNING);
             fh.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fh);


        Test t = ApplicationContextHolder.getContext().getBean(Test.class);


        for (int records = 100_000;records<=2_000_000;records+=300_000)
            for (int threads =2;threads<= Runtime.getRuntime().availableProcessors();threads++){
                List<Future<Long>> timelist = t.quickInsertAsync(records,threads);

                Long totalTime = 0L;
                Long actualTime = 0L;

                try {

                    for (Future<Long> timer:timelist) {
                        Long tmp = timer.get();
                        actualTime=actualTime<tmp?tmp:actualTime;
                        totalTime+=tmp;
                        System.out.println(timer.get());
                    }

                    System.out.println("totalTime = " + totalTime);
                    logger.log(Level.WARNING,String.format("records: %d, threads: %d, total time: %d, actual time: %d",records,threads,totalTime,actualTime));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                t.clearAllCities();
            }*/

    }


}
