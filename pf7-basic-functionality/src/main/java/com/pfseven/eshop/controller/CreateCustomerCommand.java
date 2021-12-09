package com.pfseven.eshop.controller;

import java.sql.SQLException;

import static com.pfseven.eshop.controller.EshopController.customerService;

public class CreateCustomerCommand implements Command{

    @Override
    public void execute() {
        String name = EshopController.askAdministratorForCustomerName();
        String customerType = EshopController.askAdministratorForCustomerType();

        try {
            customerService.createNewCustomer(name, customerType);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
