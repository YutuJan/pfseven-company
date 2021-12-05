package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand implements Command{
    private Logger logger = LoggerFactory.getLogger(ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand.class);

    @Override
    public void execute() {
        String customerType = EshopController.askAdministratorForCustomerType();
        logger.info("Hello from ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand... " +
                "customer's type: " + customerType);//TODO replace and add functionality
    }
}
