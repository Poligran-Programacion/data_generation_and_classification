package com.poligran.repository;

import com.poligran.implementation.IRoute;


/**
 * This class implements the IRoute interface to define methods and data required to access the routes of each element.
 * Separation of concerns is achieved through this implementation.
 */
public class RouteRepository implements IRoute {

    @Override
    public String getBaseRoute() {
        return null;
    }

    @Override
    public String getReportRoute() {
        return null;
    }

    @Override
    public String getSalesRoute() {
        return null;
    }
}
