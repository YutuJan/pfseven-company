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

    public void reportCustomersWhoPurchasedMostExpensiveProductAndHowManyTimes(Statement statement) throws SQLException {
        this.statement = statement;
        String mostExpensiveProduct;
        ArrayList<String> customersWhoPurchasedMostExpensiveProduct;

        mostExpensiveProduct = getTheMostExpensiveProduct();
        customersWhoPurchasedMostExpensiveProduct = reportCustomersWhoPurchasedMostExpensiveProduct(
                mostExpensiveProduct);
        reportHowManyTimesMostExpensiveProductWasPurchased(
                mostExpensiveProduct, customersWhoPurchasedMostExpensiveProduct);
    }

    private String getTheMostExpensiveProduct() throws SQLException {
        String mostExpensiveProduct;

        ResultSet resultSet = statement.executeQuery("select product_name" +
                "from products" +
                "order by product_price desc" +
                "limit 1;");

        mostExpensiveProduct = resultSet.getString("product_name");
        return mostExpensiveProduct;
    }

    private ArrayList<String> reportCustomersWhoPurchasedMostExpensiveProduct(String mostExpensiveProduct) throws SQLException {
        ArrayList<String> customersWhoPurchasedMostExpensiveProduct = new ArrayList<String>();

        ResultSet resultSet = statement.executeQuery("select distinct customer_name" +
                "from orders" +
                "inner join customers on customers.customer_id = orders.customer_id" +
                "inner join products p on orders.product_id = p.product_id" +
                "where product_name = '" + mostExpensiveProduct + "';");

        while (resultSet.next()) {
            String customerName = resultSet.getString("customer_name");
            customersWhoPurchasedMostExpensiveProduct.add(customerName);
        }

        for (String customerName: customersWhoPurchasedMostExpensiveProduct){
            logger.info("customer_name: {}", customerName);
        }

        return customersWhoPurchasedMostExpensiveProduct;
    }

    private void reportHowManyTimesMostExpensiveProductWasPurchased(
            String mostExpensiveProduct,
            ArrayList<String> customersWhoPurchasedMostExpensiveProduct) throws SQLException {
        ResultSet resultSet;

        for (String customerName: customersWhoPurchasedMostExpensiveProduct){
            resultSet = statement.executeQuery("select count(customer_name)" +
                    "from orders" +
                    "inner join customers on customers.customer_id = orders.customer_id" +
                    "inner join products p on orders.product_id = p.product_id" +
                    "where product_name = '" + mostExpensiveProduct + "' and customer_name = '" +
                    "" + customerName + "';");

            while (resultSet.next()) {
                logger.info("count: {}",
                        resultSet.getString("count(customer_name)"));
            }
        }
    }

    public void reportTotalNumberAndCostOfPurchasesByCustomerCategory(Statement statement, String category){

    }

    public void reportTotalNumberAndCostOfPurchasesByCustomerID(Statement statement, String id){

    }

    public void reportTotalNumberAndCostOfPurchasesByPaymentMethod(Statement statement, String paymentMethod){

    }

}
