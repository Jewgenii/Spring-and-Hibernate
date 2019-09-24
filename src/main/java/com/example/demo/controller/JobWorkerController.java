package com.example.demo.controller;

import com.example.demo.model.worker_jobs.Job;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "jobs")
public class JobWorkerController {
    @Autowired
    JobService jobService;
    @GetMapping(value = "getall")
    public ResponseEntity<List<Job>> getAll(){

        List<Job> j = jobService.getAllJobs();
        return new ResponseEntity<List<Job>>(j,HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value="ins")
    public void insert(@RequestParam String pass,String description){
   /* public void insert(@ModelAttribute Job j){*/
        try{
            Job b = new Job();
            b.setPass(pass);
            b.setDescription(description);
            jobService.insert(b);
        }
        catch (Exception ex){
            System.out.println("ex = " + ex);
        }
        
    }

}
