package com.pfseven.eshop.controller;

public class AddProductOrderCommand implements Command{

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        String productID = EshopController.askAdministratorForProductID();
        System.out.println("Hello from AddProductOrdersCommand");//TODO replace and add functionality
    }
}
