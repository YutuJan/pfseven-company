package com.pfseven.eshop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private HashMap<String, Customer> bunchOfCustomers;
    private HashMap<String, Product> bunchOfProducts;
    private Statement statement;

    public CustomerService(Statement statement) throws SQLException {
        this.statement = statement;
        bunchOfCustomers = new HashMap<String, Customer>();
        bunchOfProducts = loadFromDatabaseAllAvailableProducts();
    }

    private HashMap<String, Product> loadFromDatabaseAllAvailableProducts() throws SQLException {
        HashMap<String, Product> products = new HashMap<>();

        ResultSet resultSet = statement.executeQuery("select *" +
                "from products;");

        while (resultSet.next()){
            String id = resultSet.getString("product_id");
            String name = resultSet.getString("product_name");
            double price = Double.parseDouble(resultSet.getString("product_price"));

            Product product = new Product(id, name, price);
            products.put(id, product);
        }

        return products;
    }

    public void addOrderItem(String customerID, String productID){
        if (!bunchOfCustomers.containsKey(customerID)){
            logger.info("There is no customer with ID of: {}", customerID);
        } else{
            Product orderItem = bunchOfProducts.get(productID);
            bunchOfCustomers.get(customerID).addOrderItem(orderItem);
        }
    }

    public void removeOrderItem(String customerID, String productID){
        if (!bunchOfCustomers.containsKey(customerID)){
            logger.info("There is no customer with ID of: {}", customerID);
        } else{
            Product orderItem = bunchOfProducts.get(productID);
            bunchOfCustomers.get(customerID).removeOrderItem(orderItem);
        }
    }

    public void createNewCustomer(String name, String customerCategory){
        if (!doesCustomerNameAlreadyExists(name)){
            Customer newCustomer = new Customer(name, customerCategory);
            String newCustomerID = newCustomer.getID();
            bunchOfCustomers.put(newCustomerID, newCustomer);
        }
    }

    private boolean doesCustomerNameAlreadyExists(String name){
        for (Customer customer: bunchOfCustomers.values()){
            if (customer.getName().equals(name)){
                logger.info("A customer with name: {} already exists...", name);
                return true;
            }
        }
        return false;
    }

    public void payOrder(String customerID, String paymentMethod){

    }

    private double computeDiscount(String customerCategory, String paymentMethod, Product boughtProduct){
        return 0;//TODO replace with the actual func
    }
}
