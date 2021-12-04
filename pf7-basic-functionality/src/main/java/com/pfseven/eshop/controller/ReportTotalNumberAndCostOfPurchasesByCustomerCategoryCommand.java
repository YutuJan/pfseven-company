package com.pfseven.eshop.controller;

public class ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand implements Command{

    @Override
    public void execute() {
        String customerType = EshopController.askAdministratorForCustomerType();
        System.out.println("Hello from ReportTotalNumberAndCostOfPurchasesByCustomerCategoryCommand");//TODO replace and add functionality
    }
}
