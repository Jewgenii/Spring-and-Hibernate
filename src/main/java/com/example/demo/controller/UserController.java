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
    public Person person;

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
        Optional<Person> p = person_service.findById(id);
        person_service.deleteById(id);
    return p.isPresent()?p.get():null;
    }

    @RequestMapping(name = "/update",method = RequestMethod.POST)
    public Person update(@RequestParam Long id,String first_name,String second_name,String email,Long age){
        person = person_service.findById(id).get();
        person.setSecond_name(second_name);
        person.setFirst_name(first_name);
        person.setEmail(email);
        person.setAge(age);

        person_service.save(person);
        return person;
    }
    @RequestMapping(name="/get",method = RequestMethod.GET)
    public Person get(@RequestParam Long id){
        Optional<Person> p = person_service.findById(id);
        return p.isPresent()?p.get():null;
    }

}
