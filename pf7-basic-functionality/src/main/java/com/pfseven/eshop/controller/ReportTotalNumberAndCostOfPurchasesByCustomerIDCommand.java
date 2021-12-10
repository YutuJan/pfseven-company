package com.pfseven.eshop.controller;

import java.sql.SQLException;

import static com.pfseven.eshop.controller.EshopController.reportService;

public class ReportTotalNumberAndCostOfPurchasesByCustomerIDCommand implements Command {

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();

        try {
            reportService.reportTotalNumberAndCostOfPurchasesByCustomerID(customerID);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
