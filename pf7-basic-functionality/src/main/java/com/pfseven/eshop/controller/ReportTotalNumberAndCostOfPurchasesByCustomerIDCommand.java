package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportTotalNumberAndCostOfPurchasesByCustomerIDCommand implements Command{
    private final Logger logger = LoggerFactory.getLogger(ReportTotalNumberAndCostOfPurchasesByCustomerIDCommand.class);

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        logger.info("Hello from ReportTotalNumberAndCostOfPurchasesByCustomerID... " +
                "customer's ID: " + customerID);//TODO replace and add functionality
    }
}
