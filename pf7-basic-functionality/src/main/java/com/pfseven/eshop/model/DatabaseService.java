package com.pfseven.eshop.model;

import org.h2.tools.Server;

import java.sql.ResultSet;

public class DatabaseService {
    private static final String DB_CONNECTION_URL_MEMORY_MDDE = "jdbc:h2:mem:sample";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";
    private static Server server;

    public void launchDB(){

    }

    public void shutDB(){

    }

    public void executeUpdate(String update){

    }

    public ResultSet executeQuery(String query){
        return null;//TODO replace with actual func
    }
}
