package com.example.demo.convertors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.hibernate.engine.jdbc.ReaderInputStream;

import javax.persistence.AttributeConverter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class ImageByteArrConvertor implements AttributeConverter<File,byte[]>
{
    @Override
    public byte[] convertToDatabaseColumn(File file) {
        try {
            return new FileInputStream(file).readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File convertToEntityAttribute(byte[] bytes) {

        File f = new File("tmp.img");

        try {
            new FileOutputStream(f).write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
    }
}
