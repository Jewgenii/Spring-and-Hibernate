package com.example.demo.controller;

import com.example.demo.db.PersonService;
import com.example.demo.service.FIleLoggerService;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Person;


import java.util.Optional;

@RestController
@RequestMapping(value = {"/person"})
public class UserController {
    @Autowired
    public PersonService person_service;
    public Person person;
    public volatile FIleLoggerService log;

    public UserController(){
        log = FIleLoggerService.getInstance();
    }

    @RequestMapping(name = "/add",method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Person add(@RequestParam String first_name,String second_name,String email,Long age) {

        Person p = new Person();
        p.setFirst_name(first_name);
        p.setSecond_name(second_name);
        p.setEmail(email);
        p.setAge(age);

        person_service.save(p);

        log.Log("add:\r\n"+p);
        return p;
    }

    @RequestMapping(name = "/delete",method = RequestMethod.DELETE)
    public Person delete(@RequestParam Long id){
        Optional<Person> p = person_service.findById(id);
        person_service.deleteById(id);
        log.Log("delete:\r\n"+p);
    return p.isPresent()?p.get():null;
    }

    @RequestMapping(name = "/update",method = RequestMethod.PUT)
    public ResponseEntity<Person> update(@RequestParam Long id,String first_name,String second_name,String email,Long age){
        return person_service.findById(id).map((_p)->{
            _p.setFirst_name(first_name);
            _p.setSecond_name(second_name);
            _p.setEmail(email);
            _p.setAge(age);
            person_service.save(_p);
            return new ResponseEntity<>(_p,HttpStatus.OK);
        }).orElseThrow();
    }
    @RequestMapping(name="/get",method = RequestMethod.GET)
    public Person get(@RequestParam Long id){
        Optional<Person> p = person_service.findById(id);
        return p.isPresent()?p.get():null;
    }

}
