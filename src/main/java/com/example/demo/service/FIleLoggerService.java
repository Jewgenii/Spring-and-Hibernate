package com.example.demo.service;

import com.example.demo.Config;
import org.springframework.stereotype.Service;

import java.io.*;
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
    public synchronized void Log(String source) {
        String filePath = "./RequestLog.txt";
        try{
            BufferedWriter stream = new BufferedWriter(new FileWriter(filePath,new File(filePath).exists()));
            stream.write(source+"\r\n");
            stream.flush();
            stream.close();
        }
        catch(IOException ex){

        }
        finally {

        }
    }
}
