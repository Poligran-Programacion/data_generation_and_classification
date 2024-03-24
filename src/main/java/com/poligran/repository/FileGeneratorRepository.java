package com.poligran.repository;

import com.poligran.implementation.IFileGenerator;

/**
 * This class implements the IFileGenerator interface, which is used for both creating initial files
 * and generating reports. This establishes a more versatile design pattern and aids scalability.
 */
public class FileGeneratorRepository implements IFileGenerator {
    @Override
    public void createFile() {
        // Implementation for creating files
    }

    @Override
    public void deleteFile() {
        // Implementation for deleting files
    }

    @Override
    public void updateFile() {
        // Implementation for updating files
    }
}

