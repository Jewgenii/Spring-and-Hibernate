package com.example.demo.controller;

import com.example.demo.db.PersonService;
import com.example.demo.service.FIleLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Person;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
    @ResponseStatus(code = HttpStatus.CREATED)
    public Person add(@RequestParam String first_name,String second_name,String email,Long age) {

        Person p = new Person(first_name,second_name,email,age);
        try{
            person_service.save(p);
            log.log("add:\r\n"+p+ "::"+request.getRemoteAddr());
        }
        catch(DataIntegrityViolationException ex){
            System.out.println(ex.toString());

            log.log("(error)add:\r\n"+p + "::"+request.getRemoteHost());
        }

        return p;
    }

    @DeleteMapping
    @ResponseStatus(code=HttpStatus.OK)
    public Person delete(@RequestParam Long id){
        Optional<Person> p = person_service.findById(id);
        try{
            person_service.deleteById(id);
            log.log("delete:\r\n"+p);
        }catch(Exception ex){
            System.out.println(ex.toString());
        }

    return p.isPresent()?p.get():null;
    }

    @PutMapping("/update")
    @ResponseStatus(code=HttpStatus.OK)
    public ResponseEntity<Person> update(@ModelAttribute Person per){

        ResponseEntity<Person> p = null;
        //@RequestParam Long id,String first_name,String second_name,String email,Long age
        try{
            p = person_service.findById(per.getId()).map((_p)->{
                _p.setFirst_name(per.getFirst_name());
                _p.setSecond_name(per.getSecond_name());
                _p.setEmail(per.getEmail());
                _p.setAge(per.getAge());
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
    @ResponseStatus(code=HttpStatus.OK)
    public ResponseEntity<Person> get(@RequestParam Long id) throws Exception {
        ResponseEntity<Person> p = null;
        try{
         p = person_service.findById(id).map((_p)->{ return new ResponseEntity<Person>(_p,HttpStatus.OK);}).orElseThrow();
        } catch(Exception ex){
            System.out.println(ex);
            p = ResponseEntity.badRequest().body(null);
        }

       if(true)
           throw new Exception("shit happens");

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

    @GetMapping("/getalltablesinfoscheme")
    List<String> getalltablesinfoscheme(@RequestParam String namelike){
        return person_service.getalltableinfoscheme(namelike);
    }
    @PostMapping("insertuser")
    public void insertuser(@RequestParam String first_name,String second_name,String email, Long age){
        person_service.insertPerson(first_name,second_name,email,age);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String exception(Exception ex){
        return ex.toString();
    }
}
