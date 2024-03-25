package com.poligran.implementation;

import java.io.FileWriter;

/**
 * This interface defines methods for implementing CRUD (Create, Read, Update, Delete) operations
 * to establish initial business rules for file generation.
 */
public interface IFileGenerator {
    /**
     * Creates a file according to specified rules.
     */
    FileWriter createFile(String route, String fileName);

    /**
     * Deletes a file according to specified rules.
     */
    void deleteFile(String fileName);

    /**
     * Updates a file according to specified rules.
     */
    void updateFile(String fileName);
}

