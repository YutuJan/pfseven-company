package com.pfseven.eshop.controller;

import com.pfseven.eshop.model.DatabaseService;

import java.sql.SQLException;
import java.sql.Statement;

import static com.pfseven.eshop.controller.EshopController.reportService;

public class ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand implements Command{

    @Override
    public void execute() {
        Statement statement = DatabaseService.getStatement();
        String customerType = EshopController.askAdministratorForCustomerType();

        try {
            reportService.reportTotalNumberAndCostOfPurchasesByCustomerCategory(statement, customerType);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
