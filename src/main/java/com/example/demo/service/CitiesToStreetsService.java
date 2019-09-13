package com.example.demo.service;

import com.example.demo.repositories.CitiesToStreetsRepository;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.StreetsRepository;
import com.example.demo.model.CitiesToStreets;
import com.example.demo.model.City;
import com.example.demo.model.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            repository.save(cs);
        }
        catch(Exception ex){
            System.out.println("ex = " + ex);
        }
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
}
