package com.pfseven.eshop.controller;

import java.sql.SQLException;

import static com.pfseven.eshop.controller.EshopController.reportService;

public class ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand implements Command{

    @Override
    public void execute() {
        String customerType = EshopController.askAdministratorForCustomerType();

        try {
            reportService.reportTotalNumberAndCostOfPurchasesByCustomerCategory(customerType);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
