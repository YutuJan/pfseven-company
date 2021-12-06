package com.pfseven.eshop.model;

import org.h2.Driver;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseService {
    private final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    private final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:sample";
    private final String DB_USERNAME = "sa";
    private final String DB_PASSWORD = "";
    private Server server;
    private Connection connection;
    private Statement statement;

    public void launchDB() throws SQLException {
        int result;

        server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
        server.start();
        logger.info("Server started... " + server.getStatus());

        Driver.load();
        logger.info("Driver loaded...");

        connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
        statement = connection.createStatement();


        result = statement.executeUpdate("create table orders (" +
                " customer_id varchar not null, " +
                " customer_name varchar(50) not null, " +
                " customer_category varchar not null, " +
                " product_id varchar not null primary key, " +
                " product_name varchar(50) not null, " +
                " product_cost varchar not null, " +
                " product_cost_with_discount varchar not null)");
        logger.info("Created table command was successful with result {}.", result);

        result = statement.executeUpdate("create table products (" +
                " product_id varchar not null , " +
                " product_name varchar(50) not null , " +
                " product_cost varchar not null)");
        logger.info("Created table command was successful with result {}.", result);
    }

    public void shutDB(){
        server.stop();
        server.shutdown();
        logger.info("Server has been shutdown");
    }

    public void executeUpdate(String update) throws SQLException {
        int result = statement.executeUpdate(update);
        logger.info("Updating table was successful with result {}.", result);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public void showResultsFromOrdersTable(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            logger.info("customer_id: {}," +
                        " customer_name: {}," +
                        " customer_category: {}," +
                        " product_id: {}," +
                        " product_name: {}," +
                        " product_cost: {}," +
                        " product_cost_with_discount: {}",
                        resultSet.getString("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_category"),
                        resultSet.getString("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_cost"),
                        resultSet.getString("product_cost_with_discount"));
        }
    }

    public void showResultsFromProductsTable(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            logger.info("id: {}," +
                        " name: {}," +
                        " cost: {},",
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("cost"));
        }
    }
}
