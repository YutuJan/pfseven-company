package com.pfseven.eshop.controller;

public class ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand implements Command{

    @Override
    public void execute() {
        String customerType = EshopController.askAdministratorForCustomerType();
        System.out.println("Hello from ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand... " +
                "customer's type: " + customerType);//TODO replace and add functionality
    }
}
