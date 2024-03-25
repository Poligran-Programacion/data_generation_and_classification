package com.poligran.repository;

import com.poligran.implementation.IRoute;

import java.io.File;


/**
 * This class implements the IRoute interface to define methods and data required to access the routes of each element.
 * Separation of concerns is achieved through this implementation.
 */
public class RouteRepository implements IRoute {

    @Override
    public String getBaseRoute() {
        return getRoute("base");
    }

    @Override
    public String getReportRoute() {
        return getRoute("report");
    }

    @Override
    public String getSalesRoute() {
        return getRoute("sales");
    }

    /**
     * This function provides the path to a specific folder within a project's structure, regardless of the operating
     * system on which the application is running. The returned path is based on the user's working directory and
     * follows a standard folder structure commonly used in Java projects.
     *
     * @param folder The name of the folder within the project's structure.
     * @return The complete path to the specified folder.
     */
    private String getRoute(String folder){
        // Returns the path based on the user's working directory and standard folder structure.
        return System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + folder + File.separator;
    }


}
