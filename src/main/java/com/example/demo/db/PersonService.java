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
public class PersonService implements JpaRepository<Person,Long> {
    @Autowired
    protected PersonRepository person;

    @Override
    public List<Person> findAll(Sort sort) {
        return null;
    }

    @Override
    public <S extends Person> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Person> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Person> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Person> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Page<Person> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Person> findById(Long aLong) {
        return person.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public void deleteAll(Iterable<? extends Person> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Person> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public <S extends Person> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Person> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Person> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public <S extends Person> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Person> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Person> S save(S s) {
        try{
            person.save(s);
        }catch(Exception e){
        }
        return s;
    }

    @Override
    public void deleteById(Long aLong) {
        person.deleteById(aLong);
    }

    @Override
    public Person getOne(Long aLong) {
        return person.getOne(aLong);
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(person.findAll());
    }
}
