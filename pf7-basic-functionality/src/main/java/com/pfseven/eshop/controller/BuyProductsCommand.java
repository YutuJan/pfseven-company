package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuyProductsCommand implements Command{
    private Logger logger = LoggerFactory.getLogger(BuyProductsCommand.class);

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        String paymentMethod = EshopController.askAdministratorForPaymentMethod();
        logger.info("Hello from BuyProductsCommand... " + "customer's ID: " + customerID +
                ", payment method: " + paymentMethod);//TODO replace and add functionality
    }
}
