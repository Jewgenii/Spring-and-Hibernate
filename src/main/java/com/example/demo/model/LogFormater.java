package com.example.demo.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class LogFormater {
    private String itemToLog;
    private String dateTime;

    public String getItemToLog() {
        return itemToLog;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setItemToLog(String itemToLog) {
        this.itemToLog = itemToLog;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public LogFormater(String itemToLog) {
        this.itemToLog = itemToLog;

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        dateTime = formatter.format(date);
    }

    @Override
    public String toString() {
        return dateTime + System.lineSeparator()+itemToLog+System.lineSeparator();
    }
}
