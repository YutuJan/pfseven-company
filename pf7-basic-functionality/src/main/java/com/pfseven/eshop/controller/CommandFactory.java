package com.pfseven.eshop.controller;

public class CommandFactory {
    public static Command getCommand(String commandType) throws Exception {
        commandType = commandType.replaceAll("\\s", "");
        if (commandType.equalsIgnoreCase("AddProductOrders")) {
            return new AddProductOrdersCommand();
        } else if (commandType.equalsIgnoreCase("BuyProducts")) {
            return new BuyProductsCommand();
        } else if (commandType.equalsIgnoreCase("CreateCustomer")) {
            return new CreateCustomerCommand();
        } else if (commandType.equalsIgnoreCase("LaunchSystem")) {
            return new LaunchSystemCommand();
        } else if (commandType.equalsIgnoreCase("ReportTotalNumberAndCostOfPurchases")) {
            return new ReportTotalNumberAndCostOfPurchasesByIDCommand();
        } else if (commandType.equalsIgnoreCase("ReportCustomersWhoPurchasedMostExpensiveProductByID")) {
            return new ReportCustomersWhoPurchasedMostExpensiveProductCommand();
        } else if (commandType.equalsIgnoreCase("ReportCustomersWhoPurchasedMostExpensiveProductByCustomerCategory")){
            return new ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand();
        } else if (commandType.equalsIgnoreCase("ReportCustomersWhoPurchasedMostExpensiveProductByPaymentMethod")){
            return new ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand();
        } else if (commandType.equalsIgnoreCase("ShutSystem")){
            return new ShutSystemCommand();
        } else{
            throw new Exception("Command type: " + "'" + commandType + "'" + " is not supported!");
        }
    }
}
