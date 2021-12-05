package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCustomerCommand implements Command{
    private Logger logger = LoggerFactory.getLogger(CreateCustomerCommand.class);

    @Override
    public void execute() {
        String name = EshopController.askAdministratorForCustomerName();
        String customerType = EshopController.askAdministratorForCustomerType();
        logger.info("Hello from CreateCustomerCommand... " + "name: " + name +
                ", customer type: " + customerType);//TODO replace and add functionality
    }
}
