package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class User implements Serializable {
    //must have this static final constant initialized for consistency ser/deser
    private static final long serialVersionUID = 1L;

    @Autowired
    LogFormater f;

    public String name;
    public User(String name){
        this.name  = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return name == ((User)obj).name;
    }


}
