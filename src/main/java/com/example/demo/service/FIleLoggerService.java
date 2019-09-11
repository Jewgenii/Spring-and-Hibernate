package com.example.demo.service;

import com.example.demo.Config;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.ReentrantLock;
import com.example.demo.model.LogFormater;

@Service
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
