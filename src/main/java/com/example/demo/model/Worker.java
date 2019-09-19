package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "worker")
@Entity
public class Worker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
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
