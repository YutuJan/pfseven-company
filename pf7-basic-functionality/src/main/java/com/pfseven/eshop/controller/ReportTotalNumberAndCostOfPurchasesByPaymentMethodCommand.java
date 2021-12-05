package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand implements Command{
    private Logger logger = LoggerFactory.getLogger(ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand.class);

    @Override
    public void execute() {
        String paymentMethod = EshopController.askAdministratorForPaymentMethod();
        logger.info("Hello from ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand... " +
                "payment method: " + paymentMethod);//TODO replace and add functionality
    }
}
