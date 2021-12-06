package com.pfseven.eshop.controller;

import com.pfseven.eshop.model.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;

public class ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand implements Command{
    private final Logger logger = LoggerFactory.getLogger(ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand.class);

    @Override
    public void execute() {
        Statement statement = DatabaseService.getStatement();
        String paymentMethod = EshopController.askAdministratorForPaymentMethod();
        logger.info("Hello from ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand... " +
                "payment method: " + paymentMethod);//TODO replace and add functionality
    }
}
