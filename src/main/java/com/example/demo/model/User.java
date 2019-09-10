package com.example.demo.model;

public class User {
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
