package com.poligran.implementation;

/**
 * This interface defines the necessary methods for handling routes.
 * These routes will be located in the resources folder and will be responsible
 * for storing the data we will process.
 */
public interface IRoute {

    /**
     * Retrieves the base route containing user data, product information,
     * and necessary initialization data for the program.
     * @return The base route.
     */
    String getBaseRoute();

    /**
     * Retrieves the route where CSV-format reports of finalized transactions will be stored.
     * @return The route for storing report files.
     */
    String getReportRoute();

    /**
     * Retrieves the route where all sales made by the vendors will be stored.
     * Each vendor may have more than one file, so this folder will contain all
     * the sales records of the program.
     * @return The route for storing sales files.
     */
    String getSalesRoute();
}
