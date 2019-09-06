package com.example.demo.db;

import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class TestServiceImpl implements TestService {

   @Autowired
    public TestRepositery repository;

    @Override
    public TestT getById(Long id) {
        TestT test = repository.findById(id).orElseThrow(null);
        return test;
    }
}
