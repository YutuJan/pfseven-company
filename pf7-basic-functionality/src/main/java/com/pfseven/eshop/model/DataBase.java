package com.pfseven.eshop.model;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataBase {

    private static ArrayList<Product> listOfProducts = new ArrayList();
    private static ArrayList<Product> orderList = new ArrayList();
    private static Float cost = 0F;
    HashMap<String, ArrayList<Float>> appMap = new HashMap<String, ArrayList<Float>>();
    private static Float number = 0F;
    private static final Logger logger = LoggerFactory.getLogger(DataBase.class);

    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:sample";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    private static Server server;

    public static void shutDown() {
        server.stop();
        server.shutdown();
        logger.info("H2 server has been shutdown.");

    }
    public static void startServer() throws SQLException {
        server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
        server.start();
        logger.info("H2 server has started with status '{}'.", server.getStatus());

        org.h2.Driver.load();
        logger.info("H2 JDBC driver server has been successfully loaded.");
    }
    public static void createTable(Statement statement) throws SQLException {
        int result = statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Product (" + " id INTEGER not NULL PRIMARY KEY, " + " name VARCHAR(20), " +
                        " category VARCHAR(50), " + " price FLOAT " + ");");
        logger.info("Created table command was successful with result {}.", result);

    }
    public static void insertInto(Statement statement) throws SQLException {
        int rowsAffected = statement.executeUpdate(
                "INSERT INTO Product VALUES (101, 'Milk', 'Food', 1.5)," + "(102, 'Chocolate', 'Food', 1)," +
                        "(103, 'Chips', 'Food', 1)," + "(104, 'Batteries', 'General', 2)," +
                        "(105, 'T-shirt', 'Clothes', 10)," + "(106, 'Cheese', 'Food', 5)," +
                        "(107, 'Chicken', 'Food', 6)," + "(108, 'Vodka', 'Alcohol', 15)," +
                        "(109, 'Whiskey', 'Alcohol', 18)," + "(110, 'Tequila', 'Alcohol', 17)," +
                        "(111, 'Gin', 'Alcohol', 12)," + "(112, 'Knife', 'Kitchen', 3.5)," +
                        "(113, 'Shampoo', 'Bath', 6.5)," + "(114, 'Shower gel', 'Bath', 5.5)," +
                        "(115, 'Lamp', 'General', 2.5)," + "(116, 'Wine', 'Alcohol', 7.5)," +
                        "(117, 'Pillow', 'General', 13)," + "(118, 'Iron', 'Electronics', 35)," +
                        "(119, 'Hair Dryer', 'Electronics', 25)," + "(120, 'Hair clippers', 'Electronics', 15)");
        logger.info("Update command was successful with {} row(s) affected.", rowsAffected);

    }
    public static void showProduct(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");
        while (resultSet.next()) {
            logger.info("Id: {}, Name:{}, Category:{}, Price: {}", resultSet.getString("id"),
                    resultSet.getString("name"), resultSet.getString("category"), resultSet.getString("price"));
        }
    }
    public static void addProducts(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setCategory(resultSet.getString("category"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getFloat("price"));
            listOfProducts.add(product);
        }
        logger.info(String.valueOf(listOfProducts));
    }
    public static void orderConfirmed(Integer id, Float number, Float cost, HashMap appMap){
        ArrayList<Float> array = new ArrayList();
        array.add(number+1);
        array.add(cost);
        appMap.put(id,array);
        logger.info(String.valueOf(appMap.get(id)));
    }
    public static Float totalCost(Float cost, OrderItem orderItem){
        for (Product product : DataBase.listOfProducts) {
            if (product.getId().equals(orderItem.getId())){
                cost = cost + product.getPrice();
            }
        }
        return cost;
    }
    public static void main(String[] args) throws SQLException {
        DataBase.startServer();
        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        DataBase.createTable(statement);
        DataBase.insertInto(statement);
        //DataBase.showProduct(statement);
        DataBase.addProducts(statement);
        Customer customer = new Customer();				//na diabazei apo plhktrologio id
        customer.setId(1);
        HashMap<Integer, ArrayList> appMap = new HashMap<>();
        //diabazei id proiontos
        OrderItem orderItem = new OrderItem();
        orderItem.setId(101);
        DataBase.cost = DataBase.totalCost(cost, orderItem);
        orderItem.setId(101);
        cost = DataBase.totalCost(cost, orderItem);
        orderItem.setId(101);
        cost = DataBase.totalCost(cost, orderItem);
        logger.info(String.valueOf(cost));
        DataBase.orderConfirmed(customer.getId(), number, cost, appMap);
        ArrayList<Float> array = new ArrayList();
        array = appMap.get(customer.getId());
        number = array.get(0);
        cost = array.get(1);
        DataBase.orderConfirmed(customer.getId(), number, cost, appMap);
        array = appMap.get(customer.getId());
        number = array.get(0);
        cost = array.get(1);
        orderItem.setId(101);
        cost = DataBase.totalCost(cost, orderItem);
        DataBase.orderConfirmed(customer.getId(), number, cost, appMap);

        DataBase.shutDown();
    }
}
