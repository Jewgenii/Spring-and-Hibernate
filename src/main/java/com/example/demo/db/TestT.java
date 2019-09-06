package com.example.demo.db;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TestT")
public class TestT implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "test_field")
    public String testField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTestField(String testField) {
        this.testField = testField;
    }

    public String getTestField() {
        return testField;
    }


}
