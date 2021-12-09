package com.pfseven.eshop.service;

import com.pfseven.eshop.model.Product;
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


    public DatabaseService(){
        try {
            launchDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void launchDB() throws SQLException {
        server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
        server.start();
        logger.info("SERVER STARTED... " + server.getStatus());

        Driver.load();
        logger.info("DRIVER LOADED...");

        connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
        statement = connection.createStatement();

        createCustomersTable();
        createProductsTable();
        createOrdersTable();
        addProductsInDatabase();
    }

    private void createCustomersTable() throws SQLException {
        int result;

        result = statement.executeUpdate("create table customers(" +
                "customer_id varchar not null," +
                "customer_name varchar(50) not null," +
                "customer_category varchar not null" +
                ");");
        logger.info("CREATE CUSTOMERS TABLE COMMAND WAS SUCCESSFUL WITH RESULT {}.", result);
    }

    private void createProductsTable() throws SQLException {
        int result;

        result = statement.executeUpdate("create table products(" +
                "product_id varchar not null," +
                "product_name varchar(50) not null," +
                "product_price double not null" +
                ");");
        logger.info("CREATE PRODUCTS TABLE COMMAND WAS SUCCESSFUL WITH RESULT {}.", result);
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
        logger.info("CREATE ORDERS TABLE COMMAND WAS SUCCESSFUL WITH RESULT {}.", result);
    }

    private void addProductsInDatabase() throws SQLException {
        addProductInDatabase("dildo", "123");
        addProductInDatabase("car", "10000000");
        addProductInDatabase("earplugs", "30");
        addProductInDatabase("lamp", "100");
        addProductInDatabase("bulb", "1");
        addProductInDatabase("watch", "300");
    }

    private void addProductInDatabase(String product_name,
                                      String product_price) throws SQLException {
        int result;
        Product newProduct = new Product(product_name, product_price);

        result = statement.executeUpdate("insert into products values (" +
                "'" + newProduct.getID() + "', '" + product_name + "', '" + product_price + "')");
        logger.info("New product added to DB: {}. With result: {}",
                "product's ID: " + newProduct.getID() + " product's name: " +
                        product_name + " product's price: " + product_price,
                result);
    }

    public void shutDB(){
        server.stop();
        server.shutdown();
        logger.info("SERVER HAS BEEN SHUTDOWN");
    }

    public static Statement getStatement(){
        return statement;
    }
}
