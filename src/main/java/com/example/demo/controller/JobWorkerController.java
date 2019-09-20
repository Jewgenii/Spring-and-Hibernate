package com.example.demo.controller;

import com.example.demo.model.worker_jobs.Job;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller(value = "jobs")
@RequestMapping("/jobs")
public class JobWorkerController {
    @Autowired
    JobService jobService;
    @GetMapping(name = "getall")
    public ResponseEntity<List<Job>> getAll(){

        List<Job> j = jobService.getAllJobs();
        return new ResponseEntity<List<Job>>(j,HttpStatus.OK);
    }
}
