package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveProductOrderCommand implements Command{
    private final Logger logger = LoggerFactory.getLogger(RemoveProductOrderCommand.class);

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        String productID = EshopController.askAdministratorForProductID();
        logger.info("Hello from RemoveProductOrderCommand... " + "customer's ID: " + customerID +
                ", product's ID: " + productID);//TODO replace and add functionality
    }
}
