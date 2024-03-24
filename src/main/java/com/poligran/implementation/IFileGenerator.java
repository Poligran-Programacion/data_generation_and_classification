package com.poligran.implementation;

/**
 * This interface defines methods for implementing CRUD (Create, Read, Update, Delete) operations
 * to establish initial business rules for file generation.
 */
public interface IFileGenerator {
    /**
     * Creates a file according to specified rules.
     */
    void createFile();

    /**
     * Deletes a file according to specified rules.
     */
    void deleteFile();

    /**
     * Updates a file according to specified rules.
     */
    void updateFile();
}

