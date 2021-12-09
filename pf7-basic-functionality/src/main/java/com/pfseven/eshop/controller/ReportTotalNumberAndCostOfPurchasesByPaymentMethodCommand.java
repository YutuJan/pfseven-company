package com.pfseven.eshop.controller;

import java.sql.SQLException;

import static com.pfseven.eshop.controller.EshopController.reportService;

public class ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand implements Command{

    @Override
    public void execute() {
        String paymentMethod = EshopController.askAdministratorForPaymentMethod();

        try {
            reportService.reportTotalNumberAndCostOfPurchasesByPaymentMethod(paymentMethod);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
