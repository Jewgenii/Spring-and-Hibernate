package com.example.demo.controller;

import com.example.demo.db.PersonService;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Person;

import java.util.Optional;

@RestController("/person")
public class UserController {

    @Autowired
    public PersonService person_service;

    @RequestMapping(name = "/add",method = RequestMethod.PUT)
    public Person add(@RequestParam String first_name,String second_name,String email,Long age) {

        Person p = new Person();
        p.setFirst_name(first_name);
        p.setSecond_name(second_name);
        p.setEmail(email);
        p.setAge(age);

        person_service.save(p);
        return p;
    }
    @RequestMapping(name = "/delete",method = RequestMethod.DELETE)
    public Person delete(@RequestParam Long id){
        Person p = person_service.findById(id).get();
        person_service.deleteById(id);
    return p;
    }
}
