package com.example.demo.db;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    protected PersonRepository person;

    public void save(Person p){
        person.save(p);
    }

    public Optional<Person> findById(Long id){
        return person.findById(id);
    }

    public Optional<Person>  deleteById(Long id){
        Optional<Person> p = person.findById(id);
        person.deleteById(id);
        return p.isPresent()?p:null;
    }
    public int getalltablescount(){
        return  person.getalltablescount();
    }
    public List<String> getalltables(){
         return person.getalltables();
    }
    public List<String> getalltableinfoscheme(String name){
        return person.getalltableinfoscheme(name);
    }
    public void insertPerson(String first_name,
                             String second_name,
                             String email,
                             Long age){
        person.insertPerson(first_name,second_name,email,age);
    }

}
