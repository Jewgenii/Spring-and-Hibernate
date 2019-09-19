package com.example.demo.model;

import com.example.demo.convertors.ImageByteArrConvertor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.File;
import java.io.Serializable;

@Embeddable
public class AttachedProfileFile  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Convert(converter = ImageByteArrConvertor.class)
    @Column(name="file")
    private File file;

    @Column(name="file_name")
    private String fileName;

    public AttachedProfileFile(File file, String fileName) {
        this.file = file;
        this.fileName = fileName;
    }

    public AttachedProfileFile() {

    }

    public File getImage() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setImage(File file) {
        this.file = file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
