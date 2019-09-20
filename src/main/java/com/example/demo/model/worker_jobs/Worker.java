package com.example.demo.model.worker_jobs;

import com.example.demo.generators.CurrentTimeGenerator;
import com.example.demo.model.worker_jobs.Job;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "worker", schema = "worker_job", uniqueConstraints = {@UniqueConstraint(name = "unique_job_worker",columnNames = {"name"})})
@Entity
public class Worker implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="`name`")
    private String name;

    @Column
    @GeneratorType(type = CurrentTimeGenerator.class, when = GenerationTime.ALWAYS)
    private Date insdate;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE})
    private Job job;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Worker(String name, Job job) {
        this.name = name;
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Worker() {
    }

}
