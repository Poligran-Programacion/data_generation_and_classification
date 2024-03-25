package com.poligran.repository;

import com.poligran.implementation.IFileGenerator;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class implements the IFileGenerator interface, which is used for both creating initial files
 * and generating reports. This establishes a more versatile design pattern and aids scalability.
 */
public class FileGeneratorRepository implements IFileGenerator {


    @Override
    public FileWriter createFile( String route, String fileName) {
        try {
            return new FileWriter(route + fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String fileName) {

    }

    @Override
    public void updateFile(String fileName) {

    }
}

