package com.pfseven.eshop.controller;

import static com.pfseven.eshop.controller.EshopController.customerService;

public class RemoveProductOrderCommand implements Command {

    @Override
    public void execute() {
        String customerID = EshopController.askAdministratorForCustomerID();
        String productID = EshopController.askAdministratorForProductID();

        customerService.removeOrderItem(customerID, productID);
    }
}
