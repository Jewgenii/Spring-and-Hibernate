package com.example.demo.repositories;

import com.example.demo.model.worker_jobs.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
        Job getByDescriptionEqualsOrderByDescription(String description);
}
