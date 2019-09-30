package com.example.demo.controller;

import com.example.demo.model.cities_streets.City;
import com.example.demo.model.cities_streets.Street;
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

    @PostMapping(value="addcity")
    public void addcity(@RequestParam String cityname) {

        int count = 500000;
        for (int i = 0; i < count; i++) {
            City city = new City();
            city.setName(String.format(("city %d"), i));
            Street street = new Street();
            street.setName(String.format(("street %d"), i));
            service.add(city, street);

        }
    }
}
