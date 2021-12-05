package com.pfseven.eshop.model;

import org.h2.Driver;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {
    private final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    private final String DB_CONNECTION_URL_MEMORY_MDDE = "jdbc:h2:mem:sample";
    private final String DB_USERNAME = "sa";
    private final String DB_PASSWORD = "";
    private Server server;

    public void launchDB() throws SQLException {
        server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
        server.start();
        logger.info("Server started... " + server.getStatus());

        Driver.load();
        logger.info("Driver loaded...");
    }

    public void shutDB(){
        server.stop();
        server.shutdown();
        logger.info("Server has been shutdown");
    }

    public void executeUpdate(String update){

    }

    public ResultSet executeQuery(String query){
        return null;//TODO replace with actual func
    }
}
