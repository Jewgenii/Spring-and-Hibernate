package com.example.demo.model;

import com.example.demo.model.cities_streets.City;
import com.example.demo.service.CitiesToStreetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Configurable
@Component
public class Test {
    private ExecutorService executorService = Executors.newCachedThreadPool();
    @Autowired
    CitiesToStreetsService service;
    private Long bulkInsert(int records){

        Long t1 = System.currentTimeMillis();

        List<City> cityList = new ArrayList<>();

        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();

        for (int i = 0; i < records; i++) {
            City city = new City();
            city.setName(String.format(("%s_city %d"),uuidStr, i));
            cityList.add(city);
        }

        service.saveAllCities(cityList);

        Long t2 = System.currentTimeMillis();

        return t2 - t1;
    }

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

    public Long quickInsert(int records){
        return bulkInsert(records);
    }

    public List<Future<Long>> quickInsertAsync(int records,Integer parallelThreads) {
        parallelThreads = parallelThreads < 1 ? 1 : parallelThreads;

        executorService = Executors.newFixedThreadPool(parallelThreads);

        int[]partsArray = Test.splitIntoParts(records,parallelThreads);


        List<Callable<Long>> list = new ArrayList<>();

        for (int i= 0; i < parallelThreads; i++) {
            final int j = i;
            list.add(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    return bulkInsert(partsArray[j]);
                }
            });
        }

        try {
            return executorService.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int[] splitIntoParts(int whole, int parts) {
        int[] arr = new int[parts];
        int remain = whole;
        int partsLeft = parts;
        for (int i = 0; partsLeft > 0; i++) {
            int size = (remain + partsLeft - 1) / partsLeft; // rounded up, aka ceiling
            arr[i] = size;
            remain -= size;
            partsLeft--;
        }
        return arr;
    }

    public void clearAllCities() {
        service.removeAllCities();
    }
}
