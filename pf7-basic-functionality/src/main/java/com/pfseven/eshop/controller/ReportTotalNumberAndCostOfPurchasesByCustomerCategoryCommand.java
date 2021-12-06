package com.pfseven.eshop.controller;

import com.pfseven.eshop.model.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;

public class ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand implements Command{
    private final Logger logger = LoggerFactory.getLogger(ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand.class);

    @Override
    public void execute() {
        Statement statement = DatabaseService.getStatement();
        String customerType = EshopController.askAdministratorForCustomerType();
        logger.info("Hello from ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand... " +
                "customer's type: " + customerType);//TODO replace and add functionality
    }
}
