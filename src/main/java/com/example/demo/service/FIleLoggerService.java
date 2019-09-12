package com.example.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;

import com.example.demo.model.LogFormater;


public class FIleLoggerService implements MyLogger{
    private FIleLoggerService(){}
    private static FIleLoggerService instance;
    public static FIleLoggerService getInstance(){
        if (instance == null){
            instance = new FIleLoggerService();
        }
        return instance;
    }
    @Override
    public void log(String source) {
        Thread th =
        new Thread(new Runnable() {
            @Override
            public void run() {
                // local stach variables

                String filePath = "./RequestLog.txt";
                LogFormater lgf = new LogFormater(source);
                synchronized(this)
                {
                    try{
                        BufferedWriter stream = new BufferedWriter(new FileWriter(filePath,new File(filePath).exists()));
                        stream.write(lgf.toString());
                        stream.flush();
                        stream.close();
                    }
                    catch(IOException ex){
                        System.out.println(ex);
                    }
                }
            }
        });
        th.setDaemon(true);
        th.start();
    }
}
