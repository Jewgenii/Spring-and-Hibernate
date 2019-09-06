package com.example.demo.service;

import com.example.demo.db.TestT;
import org.springframework.stereotype.Service;

@Service
public interface TestService {
    TestT getById(Long id);
}
