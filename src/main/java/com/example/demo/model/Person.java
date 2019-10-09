package com.example.demo.model;

import com.example.demo.convertors.ImageByteArrConvertor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "person")
public class Person  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Column
    String username;
    @Column
    String first_name;

    @Column
    String second_name;

    @Column
    String email;

    @Column
    Long age;

    @Column(name = "`timestamp`")
    @CreationTimestamp
    private Date date;

    @Embedded
    private AttachedProfileFile attachedProfileFile;

    public void setAttachedProfileFile(AttachedProfileFile attachedProfileFile) {
        this.attachedProfileFile = attachedProfileFile;
    }

    public AttachedProfileFile getAttachedProfileFile() {
        return attachedProfileFile;
    }

    public Person() { }

    public Person(String first_name, String second_name, String email, Long age) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.age = age;
        this.attachedProfileFile = new AttachedProfileFile();
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("name:%s,surname:%s,age:%d,email:%s",first_name,second_name,age,email);
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setImage(MultipartFile fl) {
        byte[] barr = null;
        try {

            barr = fl.getBytes();

        } catch (IOException e) {
            e.printStackTrace();
        }

        File _fl = new File(fl.getOriginalFilename());
        this.attachedProfileFile.setFileName(fl.getOriginalFilename());
        this.attachedProfileFile.setImage(_fl);

        try {
            new FileOutputStream(_fl).write(barr);
        } catch (IOException e) {
            e.printStackTrace();
        }
     //   _fl.delete();
    }

    public File getImage() {
        return this.attachedProfileFile.getImage();
    }


    public String getEmail() {
        return email;
    }

    public Long getAge() {
        return age;
    }

}
