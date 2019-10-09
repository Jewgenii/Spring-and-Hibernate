package com.example.demo.repositories;

import com.example.demo.model.worker_jobs.Job;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import static org.springframework.data.jpa.domain.Specification.where;

public interface JobRepository extends JpaRepository<Job,Long>, JpaSpecificationExecutor<Job> {
        Job getByDescriptionEqualsOrderByDescription(String description);

}
