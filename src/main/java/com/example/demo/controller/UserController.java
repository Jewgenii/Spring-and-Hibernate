package com.example.demo.controller;

import com.example.demo.db.PersonService;
import com.example.demo.service.FIleLoggerService;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Person;
import org.springframework.web.context.request.RequestContextHolder;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("/person")
public class UserController {
    @Autowired
    public PersonService person_service;
    public volatile FIleLoggerService log;
    @Autowired
    private HttpServletRequest request;

    public UserController(){
        log = FIleLoggerService.getInstance();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.OK)
    public Person add(@RequestParam String first_name,String second_name,String email,Long age) {

        Person p = new Person(first_name,second_name,email,age);
        try{
            person_service.save(p);
            log.Log("add:\r\n"+p+ "::"+request.getRemoteAddr());
        }
        catch(DataIntegrityViolationException ex){
            System.out.println(ex.toString());

            log.Log("(error)add:\r\n"+p + "::"+request.getRemoteHost());
        }

        return p;
    }

    @DeleteMapping
    public Person delete(@RequestParam Long id){
        Optional<Person> p = person_service.findById(id);
        try{
            person_service.deleteById(id);
            log.Log("delete:\r\n"+p);
        }catch(Exception ex){
            System.out.println(ex.toString());
        }

    return p.isPresent()?p.get():null;
    }

    @PutMapping("/update")
    public ResponseEntity<Person> update(@RequestParam Long id,String first_name,String second_name,String email,Long age){
        ResponseEntity<Person> p = null;
        try{
            p = person_service.findById(id).map((_p)->{
                _p.setFirst_name(first_name);
                _p.setSecond_name(second_name);
                _p.setEmail(email);
                _p.setAge(age);
                person_service.save(_p);
                return new ResponseEntity<>(_p,HttpStatus.OK);
            }).orElseThrow();
        }
        catch(Exception ex){
            System.out.println(ex);
            p = ResponseEntity.badRequest().body(null);
        }

        return p;
    }
    @GetMapping("/get")
    public ResponseEntity<Person> get(@RequestParam Long id){
        ResponseEntity<Person> p = null;
        try{
         p = person_service.findById(id).map((_p)->{ return new ResponseEntity<Person>(_p,HttpStatus.OK);}).orElseThrow();
        } catch(Exception ex){
            System.out.println(ex);
            p = ResponseEntity.badRequest().body(null);
        }
       return p;
    }
    @GetMapping("/tablecount")
    public int gettablecount(){
        return person_service.getalltablescount();
    }
    @GetMapping("/getalltables")
    public List<String> getalltables(){
        return person_service.getalltables();
    }
}
