package com.pfseven.eshop.controller;

import java.sql.SQLException;

import static com.pfseven.eshop.controller.EshopController.reportService;

public class ReportCustomersWhoPurchasedMostExpensiveProductCommand implements Command{

    @Override
    public void execute() {
        try {
            reportService.reportCustomersWhoPurchasedMostExpensiveProductAndHowManyTimes();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
