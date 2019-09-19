package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Street;
import com.example.demo.service.CitiesToStreetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citiesstreets")
public class CitiesToStreetsController {
    @Autowired
    CitiesToStreetsService service;

    @PostMapping(value = "add")
    public void add(@RequestParam(name = "cityname") String cityname,
                    @RequestParam(name = "streetname") String streetname){

                City c = service.getCities().get(0);

                Street s = service.getStreets().get(0);

                service.add(c,s);
    }
}