package com.pfseven.eshop.controller;

public class BuyProductsCommand implements Command{

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        System.out.println("Hello from BuyProductsCommand... " + "customer's ID: " + customerID);//TODO replace and add functionality
    }
}
