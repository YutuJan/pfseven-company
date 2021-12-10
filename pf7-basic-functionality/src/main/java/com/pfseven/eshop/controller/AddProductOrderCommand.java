package com.pfseven.eshop.controller;

import static com.pfseven.eshop.controller.EshopController.customerService;

public class AddProductOrderCommand implements Command {

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        String productID = EshopController.askAdministratorForProductID();

        customerService.addOrderItem(customerID, productID);
    }
}
