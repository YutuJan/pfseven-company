package com.pfseven.eshop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public void payOrder(String customerID, String paymentMethod) throws SQLException {
        Customer customer = bunchOfCustomers.get(customerID);


        addCustomerInDatabase(customer);
        addOrderInDatabase(customer, paymentMethod);
        bunchOfCustomers.remove(customerID);
    }

    private void addCustomerInDatabase(Customer customer) throws SQLException {
        int result;

        result = statement.executeUpdate("insert into customers values (" +
                "'" + customer.getID() + "', '" + customer.getName() + "', '" + customer.getCategory() + "')");
        logger.info("Add Customers In Database command was successful with result {}.", result);
    }

    private void addOrderInDatabase(Customer customer, String paymentMethod) throws SQLException {
        String orderID = customer.getOrder().getID();
        String customerID = customer.getID();
        String customerCategory = customer.getCategory();
        ArrayList<Product> orderItems = customer.getOrder().getOderItems();

        for (Product orderItem: orderItems){
            String productID = orderItem.getID();
            String costWithDiscount = String.valueOf(computeDiscount(customerCategory, paymentMethod, orderItem));

            addOrderItemInDatabase(orderID, customerID, productID, paymentMethod, costWithDiscount);
        }
    }

    private double computeDiscount(String customerCategory,
                                   String paymentMethod,
                                   Product boughtProduct){
        double productPrice = boughtProduct.getCost();
        double newPriceAfterDiscount = productPrice;

        if (customerCategory.equals("B2B")){
            newPriceAfterDiscount -= 0.2 * productPrice;
        } else if (customerCategory.equals("B2G")){
            newPriceAfterDiscount -= 0.5 * productPrice;
        }
        if (paymentMethod.equals("credit")){
            newPriceAfterDiscount -= 0.15 * productPrice;
        } else if (paymentMethod.equals("cash")){
            newPriceAfterDiscount -= 0.1 * productPrice;
        }

        return newPriceAfterDiscount;
    }

    private void addOrderItemInDatabase(String orderID,
                                        String customerID,
                                        String productID,
                                        String paymentMethod,
                                        String costWithDiscount) throws SQLException {
        int result;

        result = statement.executeUpdate("insert into orders values (" +
                "'" + orderID + "', '" + customerID + "', '" + productID +
                "', '" + paymentMethod + "', '" + costWithDiscount + "')");
        logger.info("Add Orders In Database command was successful with result {}.", result);
    }
}
