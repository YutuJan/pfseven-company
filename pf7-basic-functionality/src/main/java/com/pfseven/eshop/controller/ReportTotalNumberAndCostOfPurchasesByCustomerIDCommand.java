package com.pfseven.eshop.controller;

import com.pfseven.eshop.model.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;

public class ReportTotalNumberAndCostOfPurchasesByCustomerIDCommand implements Command{
    private final Logger logger = LoggerFactory.getLogger(ReportTotalNumberAndCostOfPurchasesByCustomerIDCommand.class);

    @Override
    public void execute() {
        Statement statement = DatabaseService.getStatement();
        String customerID = EshopController.askAdministratorForCustomerID();
        logger.info("Hello from ReportTotalNumberAndCostOfPurchasesByCustomerID... " +
                "customer's ID: " + customerID);//TODO replace and add functionality
    }
}
