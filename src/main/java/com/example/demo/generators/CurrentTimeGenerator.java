package com.example.demo.generators;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTimeGenerator implements ValueGenerator<Date> {
    @Override
    public Date generateValue(Session session, Object o) {

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        return date;
    }
}
