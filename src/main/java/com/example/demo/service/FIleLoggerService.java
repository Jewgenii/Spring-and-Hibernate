package com.example.demo.service;

import com.example.demo.Config;
import org.springframework.stereotype.Service;

import java.io.*;
@Service
public class FIleLogger implements MyLogger{
    @Override
    public void Log(String source) {
        String filePath = "./RequestLog.txt";
        try{
            BufferedWriter stream = new BufferedWriter(new FileWriter(filePath,true));
            stream.close();
        }
        catch(IOException ex){

        }

    }
}
