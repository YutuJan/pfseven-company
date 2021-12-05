package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportCustomersWhoPurchasedMostExpensiveProductCommand implements Command{
    private Logger logger = LoggerFactory.getLogger(ReportCustomersWhoPurchasedMostExpensiveProductCommand.class);

    @Override
    public void execute() {
        logger.info("Hello from ReportCustomersWhoPurchasedMostExpensiveProductCommand...");//TODO replace and add functionality
    }
}
