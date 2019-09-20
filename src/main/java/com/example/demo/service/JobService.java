package com.example.demo.service;

import com.example.demo.model.worker_jobs.Job;
import com.example.demo.model.worker_jobs.Worker;
import com.example.demo.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;

    public List<Job> getAllJobs(){
        Job j = jobRepository.getOne(1l);
        ArrayList<Job>  arr= new ArrayList<>();
        arr.add(j);
        return arr;
    }
    public List<Job> getByWorker(Worker w){
     return null;
    }
}
