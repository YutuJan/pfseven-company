package com.pfseven.eshop.controller;

public class ReportTotalNumberAndCostOfPurchasesByCustomerIDCommand implements Command{

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        System.out.println("Hello from ReportTotalNumberAndCostOfPurchasesByIDCommand");//TODO replace and add functionality
    }
}
