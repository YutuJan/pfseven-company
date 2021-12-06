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
    private static Statement statement;

    public void launchDB() throws SQLException {
        server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
        server.start();
        logger.info("Server started... " + server.getStatus());

        Driver.load();
        logger.info("Driver loaded...");

        connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
        statement = connection.createStatement();

        createCustomersTable();
        createProductsTable();
        createOrdersTable();
    }

    private void createCustomersTable() throws SQLException {
        int result;

        result = statement.executeUpdate("create table customers(" +
                "customer_id varchar not null," +
                "customer_name varchar(50) not null," +
                "customer_category varchar not null" +
                ");");
        logger.info("Created table command was successful with result {}.", result);
    }

    private void createProductsTable() throws SQLException {
        int result;

        result = statement.executeUpdate("create table products(" +
                "product_id varchar not null," +
                "product_name varchar(50) not null," +
                "product_price double not null" +
                ");");
        logger.info("Created table command was successful with result {}.", result);
    }

    private void createOrdersTable() throws SQLException {
        int result;

        result = statement.executeUpdate("create table orders(" +
                "order_id varchar not null," +
                "customer_id varchar not null," +
                "product_id varchar not null," +
                "payment_method varchar not null," +
                "cost_with_discount double not null," +
                "foreign key (customer_id) references customers(customer_id)," +
                "foreign key (product_id) references products(product_id)" +
                ");");
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

    public static Statement getStatement(){
        return statement;
    }
}
