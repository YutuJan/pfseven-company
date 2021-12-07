package com.pfseven.eshop.controller;

import com.pfseven.eshop.model.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;

import static com.pfseven.eshop.controller.EshopController.reportService;

public class ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand implements Command{
    private final Logger logger = LoggerFactory.getLogger(ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand.class);

    @Override
    public void execute() {
        Statement statement = DatabaseService.getStatement();
        String paymentMethod = EshopController.askAdministratorForPaymentMethod();

        try {
            reportService.reportTotalNumberAndCostOfPurchasesByPaymentMethod(statement, paymentMethod);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
