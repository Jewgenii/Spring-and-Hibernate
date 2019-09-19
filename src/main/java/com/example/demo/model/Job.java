package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "job")
@Entity
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @OneToMany
    private List<Worker> workers;

    public Job(String description, List<Worker> workers) {
        this.description = description;
        this.workers = workers;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public Job() { }
/*
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name="version")
    private Version v;

    enum Version{
        BUGFIX,PRO
    }*/
}
