package com.example.demo.model.worker_jobs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Table(name = "job", schema = "worker_job")
@Entity
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column
    private String description;

    //@OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE})
    //@JoinColumn(name = "job_id")
    //private List<Worker> workers;

    @CreationTimestamp
    private Time creattiontime;

    @ColumnTransformer(
            read = "decrypt( 'AES', '00', pswd  )",
            write = "encrypt('AES', '00', ?)"
    )
    private String pass;

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setCreattiontime(Time creattiontime) {
        this.creattiontime = creattiontime;
    }

    public void setUpdateTimestamp(Time updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getUpdateTimestamp() {
        return updateTimestamp;
    }

    @UpdateTimestamp
    private Time updateTimestamp;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Time getCreattiontime() {
        return creattiontime;
    }

    public Job(String description, List<Worker> workers) {
        this.description = description;
       // this.workers = workers;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

/*    public  List<Worker> getWorkers() {
        return workers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWorkers( List<Worker> workers) {
        this.workers = workers;
    }*/

    public Job() { }
/*
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name="version")
    private Version v;

    enum Version{
        BUGFIX,PRO
    }*/
}
