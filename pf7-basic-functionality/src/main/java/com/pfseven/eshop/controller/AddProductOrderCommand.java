package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddProductOrderCommand implements Command{
    private final Logger logger = LoggerFactory.getLogger(AddProductOrderCommand.class);

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        String productID = EshopController.askAdministratorForProductID();
        logger.info("Hello from AddProductOrdersCommand... " + "customer's ID: " + customerID +
                ", product's ID: " + productID);//TODO replace and add functionality
    }
}
