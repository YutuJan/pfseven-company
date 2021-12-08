package com.pfseven.eshop.controller;

import com.pfseven.eshop.model.DatabaseService;

import java.sql.SQLException;
import java.sql.Statement;

import static com.pfseven.eshop.controller.EshopController.reportService;

public class ReportTotalNumberAndCostOfPurchasesByCustomerIDCommand implements Command{

    @Override
    public void execute() {
        Statement statement = DatabaseService.getStatement();
        String customerID = EshopController.askAdministratorForCustomerID();

        try {
            reportService.reportTotalNumberAndCostOfPurchasesByCustomerID(statement, customerID);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
