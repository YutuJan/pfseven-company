package com.pfseven.eshop.controller;

import java.sql.SQLException;

import static com.pfseven.eshop.controller.EshopController.customerService;

public class BuyProductsCommand implements Command{

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        String paymentMethod = EshopController.askAdministratorForPaymentMethod();

        try {
            customerService.payOrder(customerID, paymentMethod);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
