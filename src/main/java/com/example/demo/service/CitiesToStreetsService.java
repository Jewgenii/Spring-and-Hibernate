package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repositories.CitiesToStreetsRepository;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.StreetsRepository;
import com.example.demo.model.cities_streets.CitiesToStreets;
import com.example.demo.model.cities_streets.City;
import com.example.demo.model.cities_streets.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Service
public class CitiesToStreetsService {

    @Autowired
    private CitiesToStreetsRepository repository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StreetsRepository  streetsRepository;

    public void add(City c, Street s){
        CitiesToStreets cs = new CitiesToStreets(c,s);
        try{
            cityRepository.save(c);
            streetsRepository.save(s);
            repository.save(cs);
        }
        catch(Exception ex){
            System.out.println("ex = " + ex);
        }
    }

    @Transactional
    @Async
    public void saveAllCities(List<City> list){
        cityRepository.saveAll(list);
        cityRepository.flush();
    }

    public void addCity(City c){
        cityRepository.save(c);
    }
    public void addStreet(Street s){
        streetsRepository.save(s);
    }


    public List<Street> getStreets(){
        List<Street> s = null;
        try{
             s = streetsRepository.findAll();
        }catch(Exception ex){
            System.out.println("ex = " + ex);
        }
        return s;
    }
    public List<City> getCities(){
        List<City> l = null;
        try{
           l = cityRepository.findAll();
        }catch(Exception ex){
            System.out.println("ex = " + ex);
        }

        return l;
    }

    public void removeAllCities() {
        cityRepository.deleteAll();
    }

    @Service
    public static class PersonService {
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
        @Transactional
        public Person insertPerson(String first_name,
                                 String second_name,
                                 String email,
                                 Long age){
            person.insertPerson(first_name,second_name,email,age);
          // int res = 1/0;
           return person.getLastInsertedPerson();
        }

    }
}
