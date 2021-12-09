package com.pfseven.eshop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReportService {
    private final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private Statement statement;

    public ReportService(Statement statement){
        this.statement = statement;
    }

    public void reportCustomersWhoPurchasedMostExpensiveProductAndHowManyTimes() throws SQLException {
        String mostExpensiveProduct;
        ArrayList<String> customersWhoPurchasedMostExpensiveProduct;

        mostExpensiveProduct = getTheMostExpensiveProduct();
        customersWhoPurchasedMostExpensiveProduct = reportCustomersWhoPurchasedMostExpensiveProduct(
                mostExpensiveProduct);
        reportHowManyTimesMostExpensiveProductWasPurchased(
                mostExpensiveProduct, customersWhoPurchasedMostExpensiveProduct);
    }

    private String getTheMostExpensiveProduct() throws SQLException {
        String mostExpensiveProduct = "";

        ResultSet resultSet = statement.executeQuery("select product_name " +
                "from products " +
                "order by product_price desc " +
                "limit 1");

        while (resultSet.next()){
            mostExpensiveProduct = resultSet.getString("product_name");
        }
        return mostExpensiveProduct;
    }

    private ArrayList<String> reportCustomersWhoPurchasedMostExpensiveProduct(
            String mostExpensiveProduct) throws SQLException {
        ArrayList<String> customersWhoPurchasedMostExpensiveProduct = new ArrayList<String>();

        ResultSet resultSet = statement.executeQuery("select distinct customer_name " +
                "from orders " +
                "inner join customers on customers.customer_id = orders.customer_id " +
                "inner join products p on orders.product_id = p.product_id " +
                "where product_name = '" + mostExpensiveProduct + "'");

        while (resultSet.next()) {
            String customerName = resultSet.getString("customer_name");
            customersWhoPurchasedMostExpensiveProduct.add(customerName);
        }

        for (String customerName: customersWhoPurchasedMostExpensiveProduct){
            logger.info("Customers who purchased most expensive product: {}", customerName);
        }

        return customersWhoPurchasedMostExpensiveProduct;
    }

    private void reportHowManyTimesMostExpensiveProductWasPurchased(String mostExpensiveProduct,
            ArrayList<String> customersWhoPurchasedMostExpensiveProduct) throws SQLException {
        ResultSet resultSet;

        for (String customerName: customersWhoPurchasedMostExpensiveProduct){
            resultSet = statement.executeQuery("select count(customer_name) " +
                    "from orders " +
                    "inner join customers on customers.customer_id = orders.customer_id " +
                    "inner join products p on orders.product_id = p.product_id " +
                    "where product_name = '" + mostExpensiveProduct + "' and customer_name = '" +
                    "" + customerName + "'");

            while (resultSet.next()) {
                logger.info("Times most expensive product was purchased: {}",
                        resultSet.getString("count(customer_name)"));
            }
        }
    }

    public void reportTotalNumberAndCostOfPurchasesByCustomerCategory(String category) throws SQLException {
        reportTotalNumberOfPurchasesByCustomerCategory(category);
        reportTotalCostOfPurchasesByCustomerCategory(category);
    }

    private void reportTotalNumberOfPurchasesByCustomerCategory(String category) throws SQLException {
        ResultSet resultSet;

        resultSet = statement.executeQuery("select count(customer_category) " +
                "from orders " +
                "inner join customers on customers.customer_id = orders.customer_id " +
                "inner join products p on orders.product_id = p.product_id " +
                "where customer_category = '" + category + "'");

        while (resultSet.next()) {
            String totalNumberOfOfPurchases;

            totalNumberOfOfPurchases = resultSet.getString("count(customer_category)");
            logger.info("Total number of purchases: {}", totalNumberOfOfPurchases);
        }
    }

    private void reportTotalCostOfPurchasesByCustomerCategory(String category) throws SQLException {
        ResultSet resultSet;

        resultSet = statement.executeQuery("select sum(cost_with_discount) " +
                "from orders " +
                "inner join customers on customers.customer_id = orders.customer_id " +
                "inner join products p on orders.product_id = p.product_id " +
                "where customer_category = '" + category + "'");

        while (resultSet.next()) {
            String totalCostOfPurchases;

            totalCostOfPurchases = resultSet.getString("sum(cost_with_discount)");
            logger.info("Total cost of purchases: {}", totalCostOfPurchases);
        }
    }

    public void reportTotalNumberAndCostOfPurchasesByCustomerID(String id) throws SQLException {
        String customerName;

        customerName = getCustomerNameByCustomerID(id);
        reportTotalNumberOfPurchasesByCustomerName(customerName);
        reportTotalCostOfPurchasesByCustomerName(customerName);
    }

    private String getCustomerNameByCustomerID(String id) throws SQLException {
        ResultSet resultSet;
        String customerName = "";

        resultSet = statement.executeQuery("select customer_name " +
                "from customers " +
                "where customer_id = '" + id + "'");

        while (resultSet.next()) {
            customerName = resultSet.getString("customer_name");
            logger.info("Customer name: {}", customerName);
        }

        return customerName;
    }

    private void reportTotalNumberOfPurchasesByCustomerName(String name) throws SQLException {
        ResultSet resultSet;

        resultSet = statement.executeQuery("select count(customer_name) " +
                "from orders " +
                "inner join customers on customers.customer_id = orders.customer_id " +
                "inner join products p on orders.product_id = p.product_id " +
                "where customer_name = '" + name + "'");

        while (resultSet.next()) {
            String totalNumberOfPurchases;

            totalNumberOfPurchases = resultSet.getString("count(customer_name)");
            logger.info("Total number of purchases: {}", totalNumberOfPurchases);
        }
    }

    private void reportTotalCostOfPurchasesByCustomerName(String name) throws SQLException {
        ResultSet resultSet;

        resultSet = statement.executeQuery("select sum(cost_with_discount) " +
                "from orders " +
                "inner join customers on customers.customer_id = orders.customer_id " +
                "inner join products p on orders.product_id = p.product_id " +
                "where customer_name = '" + name + "'");

        while (resultSet.next()) {
            String totalCostOfPurchases;

            totalCostOfPurchases = resultSet.getString("sum(cost_with_discount)");
            logger.info("Total cost of purchases: {}", totalCostOfPurchases);
        }
    }

    public void reportTotalNumberAndCostOfPurchasesByPaymentMethod(String paymentMethod) throws SQLException {
        reportTotalNumberOfPurchasesByPaymentMethod(paymentMethod);
        reportTotalCostOfPurchasesByPaymentMethod(paymentMethod);
    }

    private void reportTotalNumberOfPurchasesByPaymentMethod(String paymentMethod) throws SQLException {
        ResultSet resultSet;

        resultSet = statement.executeQuery("select count(payment_method) " +
                "from orders " +
                "inner join customers on customers.customer_id = orders.customer_id " +
                "inner join products p on orders.product_id = p.product_id " +
                "where payment_method = '" + paymentMethod + "'");

        while (resultSet.next()) {
            String totalNumberOfPurchases;

            totalNumberOfPurchases = resultSet.getString("count(payment_method)");
            logger.info("Total number of purchases: {}", totalNumberOfPurchases);
        }
    }

    private void reportTotalCostOfPurchasesByPaymentMethod(String paymentMethod) throws SQLException {
        ResultSet resultSet;

        resultSet = statement.executeQuery("select sum(cost_with_discount) " +
                "from orders " +
                "inner join customers on customers.customer_id = orders.customer_id " +
                "inner join products p on orders.product_id = p.product_id " +
                "where payment_method = '" + paymentMethod + "'");

        while (resultSet.next()) {
            String totalCostOfPurchases;

            totalCostOfPurchases = resultSet.getString("sum(cost_with_discount)");
            logger.info("Total cost of purchases: {}", totalCostOfPurchases);
        }
    }
}
