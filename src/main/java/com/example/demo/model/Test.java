package com.example.demo.model;

import com.example.demo.model.cities_streets.City;
import com.example.demo.model.cities_streets.Street;
import com.example.demo.service.CitiesToStreetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Configurable
@Service
public class Test {
    private ExecutorService executorService =
            Executors.newSingleThreadExecutor();


    @Autowired
    CitiesToStreetsService service;

        public Long slowInsert(int records){

           Long t1 = System.currentTimeMillis();

            List<City> cityList = new ArrayList<>();
            for (int i = 0; i < records; i++) {
                City city = new City();
                city.setName(String.format(("_city %d"), i));
                cityList.add(city);
            }
            for (City city : cityList) {
                 service.addCity(city);
            }

            Long t2 = System.currentTimeMillis();

            return t2 - t1;
        }

        public Future<Long> quickInsert(int records){

            return executorService.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {

                    Long t1 = System.currentTimeMillis();

                    List<City> cityList = new ArrayList<>();
                    for (int i = 0; i < records; i++) {
                        City city = new City();
                        city.setName(String.format(("city %d"), i));
                        cityList.add(city);
                    }
                    service.saveAllCities(cityList);

                    Long t2 = System.currentTimeMillis();

                    return t2 - t1;
                }
            });

        }
}
