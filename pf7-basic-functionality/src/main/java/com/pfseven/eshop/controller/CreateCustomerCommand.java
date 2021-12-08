package com.pfseven.eshop.controller;

import static com.pfseven.eshop.controller.EshopController.customerService;

public class CreateCustomerCommand implements Command{

    @Override
    public void execute() {
        String name = EshopController.askAdministratorForCustomerName();
        String customerType = EshopController.askAdministratorForCustomerType();

        customerService.createNewCustomer(name, customerType);
    }
}
