package com.example.demo.model.Embeddable;

import com.example.demo.model.worker_jobs.Job;

import javax.persistence.*;

@Embeddable
public class ExtraInfo {
    @Column
    public String details;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(foreignKey =  @ForeignKey(name = "job_fk"),name = "job_id")
    private Job job;
}
