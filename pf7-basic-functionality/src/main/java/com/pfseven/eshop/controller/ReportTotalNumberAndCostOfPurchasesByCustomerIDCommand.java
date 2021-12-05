package com.pfseven.eshop.controller;

public class ReportTotalNumberAndCostOfPurchasesByCustomerIDCommand implements Command{

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        System.out.println("Hello from ReportTotalNumberAndCostOfPurchasesByCustomerID... " +
                "customer's ID: " + customerID);//TODO replace and add functionality
    }
}
