package com.pfseven.eshop.controller;

public class ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand implements Command{

    @Override
    public void execute() {
        String paymentMethod = EshopController.askAdministratorForPaymentMethod();
        System.out.println("Hello from ReportTotalNumberAndCostOfPurchasesByPaymentMethodCommand");//TODO replace and add functionality
    }
}
