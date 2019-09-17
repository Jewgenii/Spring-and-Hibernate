package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

public class Threadspeedtester implements Callable<String> {
    public void testVector() {
        long startTime = System.currentTimeMillis();

        Vector<Integer> vector = new Vector<>();

        for (int i = 0; i < 100_000_000; i++) {
            vector.addElement(i);
        }

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;

        System.out.println("Test Vector: " + totalTime + " ms");

    }

    public void testArrayList() {
        long startTime = System.currentTimeMillis();

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100_000_000; i++) {
            list.add(i);
        }

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;

        System.out.println("Test ArrayList: " + totalTime + " ms");

    }


    @Override
    public String call() throws Exception {
        Long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        Long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Thread : " + totalTime + " ms");
        return "hello world";
    }

    public void foo(final Person p)
    {

        Thread th = new Thread(()->{

            p.setAge(33l);
            p.setSecond_name("foo");
            p.setFirst_name("bar");

        });
        th.start();
        try{
            th.join();
        } catch(InterruptedException ex){

        }

        System.out.println(p);
    }
}
