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
        addProductsInDatabase();
        /** only for testing needed
        addCustomersInDatabase();
        addOrdersInDatabase();
         **/
    }

    private void createCustomersTable() throws SQLException {
        int result;

        result = statement.executeUpdate("create table customers(" +
                "customer_id varchar not null," +
                "customer_name varchar(50) not null," +
                "customer_category varchar not null" +
                ");");
        logger.info("Create Customers Table command was successful with result {}.", result);
    }

    private void createProductsTable() throws SQLException {
        int result;

        result = statement.executeUpdate("create table products(" +
                "product_id varchar not null," +
                "product_name varchar(50) not null," +
                "product_price double not null" +
                ");");
        logger.info("Create Products Table command was successful with result {}.", result);
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
        logger.info("Create Orders Table command was successful with result {}.", result);
    }

    private void addProductsInDatabase() throws SQLException {
        addProductInDatabase("11", "dildo", "123");
        addProductInDatabase("12", "car", "10000000");
        addProductInDatabase("13", "earplugs", "30");
        addProductInDatabase("14", "lamp", "100");
        addProductInDatabase("15", "bulb", "1");
        addProductInDatabase("16", "watch", "300");
    }

    private void addProductInDatabase(String product_id,
                                      String product_name,
                                      String product_price) throws SQLException {
        int result;

        result = statement.executeUpdate("insert into products values (" +
                "'" + product_id + "', '" + product_name + "', '" + product_price + "')");
        logger.info("Add Product In Database command was successful with result {}.", result);
    }

    /** only for testing needed
    private void addCustomersInDatabase() throws SQLException {
        addCustomerInDatabase("1", "Aram", "B2B");
        addCustomerInDatabase("2", "Kosta", "B2B");
        addCustomerInDatabase("3", "Kira","B2B");
    }

    private void addCustomerInDatabase(String customer_id,
                                        String customer_name,
                                        String customer_category) throws SQLException {
        int result;

        result = statement.executeUpdate("insert into customers values (" +
                "'" + customer_id + "', '" + customer_name + "', '" + customer_category + "')");
        logger.info("Add Customers In Database command was successful with result {}.", result);
    }

    private void addOrdersInDatabase() throws SQLException {
        addOrderInDatabase("1", "1", "11", "cash", "12");
        addOrderInDatabase("2", "1", "13", "credit", "20");
        addOrderInDatabase("3", "2", "12", "cash", "50000");
        addOrderInDatabase("4", "2", "16", "cash", "100");
        addOrderInDatabase("5", "3", "13", "credit", "20");
        addOrderInDatabase("6", "3", "12", "credit", "30000");
        addOrderInDatabase("7", "3", "12", "credit", "30000");
    }

    private void addOrderInDatabase(String order_id,
                                     String customer_id,
                                     String product_id,
                                     String payment_method,
                                     String cost_with_discount) throws SQLException {
        int result;

        result = statement.executeUpdate("insert into orders values (" +
                "'" + order_id + "', '" + customer_id + "', '" + product_id +
                "', '" + payment_method + "', '" + cost_with_discount + "')");
        logger.info("Add Orders In Database command was successful with result {}.", result);
    }
     **/

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
