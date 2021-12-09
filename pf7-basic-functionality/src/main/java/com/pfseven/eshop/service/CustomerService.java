package com.pfseven.eshop.service;

import com.pfseven.eshop.model.Customer;
import com.pfseven.eshop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final HashMap<String, Customer> bunchOfCustomers;
    private HashMap<String, Product> bunchOfProducts;
    private final Statement statement;

    public CustomerService(Statement statement) {
        this.statement = statement;
        bunchOfCustomers = new HashMap<>();
        try {
            bunchOfProducts = loadFromDatabaseAllAvailableProducts();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private HashMap<String, Product> loadFromDatabaseAllAvailableProducts() throws SQLException {
        HashMap<String, Product> products = new HashMap<>();

        ResultSet resultSet = statement.executeQuery("select *" +
                "from products;");

        while (resultSet.next()){
            String id = resultSet.getString("product_id");
            String name = resultSet.getString("product_name");
            String price = resultSet.getString("product_price");

            Product product = new Product(name, price);
            products.put(id, product);
        }

        return products;
    }

    public void addOrderItem(String customerID, String productID){
        if (!bunchOfCustomers.containsKey(customerID)){
            logger.info("THERE IS NO CUSTOMER WITH ID OF: {}", customerID);
        } else{
            Product orderItem = bunchOfProducts.get(productID);
            bunchOfCustomers.get(customerID).addOrderItem(orderItem);
        }
    }

    public void removeOrderItem(String customerID, String productID){
        if (!bunchOfCustomers.containsKey(customerID)){
            logger.info("THERE IS NO CUSTOMER WITH ID OF: {}", customerID);
        } else{
            Product orderItem = bunchOfProducts.get(productID);
            bunchOfCustomers.get(customerID).removeOrderItem(orderItem);
        }
    }

    public void createNewCustomer(String name, String customerCategory) throws SQLException {
        if (!doesCustomerHaveAPendingOrder(name)){
            Customer newCustomer;
            if (isCustomerInDatabase(name)){
                newCustomer = loadCustomerFromDatabase(name);
            } else {
                newCustomer = new Customer(name, customerCategory);
            }
            String newCustomerID = newCustomer.getId();
            bunchOfCustomers.put(newCustomerID, newCustomer);
            logger.info("NEW CUSTOMER ADDED TO PENDING LIST: {}", newCustomer);
        }
    }

    private boolean doesCustomerHaveAPendingOrder(String name){
        for (Customer customer: bunchOfCustomers.values()){
            if (customer.getName().equals(name)){
                logger.info("A CUSTOMER WITH NAME: {} ALREADY EXISTS...", name);
                return true;
            }
        }
        return false;
    }

    private boolean isCustomerInDatabase(String name) throws SQLException {
        ResultSet resultSet;

        resultSet = statement.executeQuery("select customer_name " +
                "from customers " +
                "where customer_name = '" + name + "'");

        return resultSet.next();
    }

    private Customer loadCustomerFromDatabase(String name) throws SQLException {
        ResultSet resultSet;
        Customer customerFromDatabase = null;

        resultSet = statement.executeQuery("select customer_name " +
                "from customers " +
                "where customer_name = '" + name + "'");

        while (resultSet.next()) {
            String customerName = resultSet.getString("customer_name");
            String customerCategory = resultSet.getString("customer_category");

            customerFromDatabase = new Customer(customerName, customerCategory);
        }

        return customerFromDatabase;
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
                "'" + customer.getId() + "', '" + customer.getName() + "', '" + customer.getCategory() + "')");
        logger.info("ADD CUSTOMERS IN DATABASE COMMAND WAS SUCCESSFUL WITH RESULT {}.", result);
    }

    private void addOrderInDatabase(Customer customer, String paymentMethod) throws SQLException {
        String orderID = customer.getOrder().getId();
        String customerID = customer.getId();
        String customerCategory = customer.getCategory();
        ArrayList<Product> orderItems = customer.getOrder().getOrderItems();

        for (Product orderItem: orderItems){
            String productID = orderItem.getId();
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
            newPriceAfterDiscount = newPriceAfterDiscount - (0.2 * productPrice);
        } else if (customerCategory.equals("B2G")){
            newPriceAfterDiscount = newPriceAfterDiscount - (0.5 * productPrice);
        }
        if (paymentMethod.equals("credit")){
            newPriceAfterDiscount = newPriceAfterDiscount - (0.15 * productPrice);
        } else if (paymentMethod.equals("cash")){
            newPriceAfterDiscount = newPriceAfterDiscount - (0.1 * productPrice);
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
        logger.info("ADD ORDERS IN DATABASE COMMAND WAS SUCCESSFUL WITH RESULT {}.", result);
    }
}
