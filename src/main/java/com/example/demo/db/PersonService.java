package com.example.demo.db;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
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

}
