package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @Column
    String first_name;
    @Column
    String second_name;
    @Column
    String email;
    @Column
    Long age;

    public Person() { }

    public Person(String first_name, String second_name, String email, Long age) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("name:%s,surname:%s,age:%d,email:%s",first_name,second_name,age,email);
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getEmail() {
        return email;
    }

    public Long getAge() {
        return age;
    }

}
