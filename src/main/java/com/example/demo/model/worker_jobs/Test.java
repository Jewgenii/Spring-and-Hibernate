package com.example.demo.model.worker_jobs;

import com.example.demo.model.Embeddable.ExtraInfo;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Test {
    @Id
    Long id;
    @Embedded
    ExtraInfo info;

    public void setInfo(ExtraInfo info) {
        this.info = info;
    }

    public ExtraInfo getInfo() {
        return info;
    }
}
