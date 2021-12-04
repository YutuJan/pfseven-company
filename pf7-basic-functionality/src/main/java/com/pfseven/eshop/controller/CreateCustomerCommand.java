package com.pfseven.eshop.controller;

public class CreateCustomerCommand implements Command{

    @Override
    public void execute() {
        String name = EshopController.askAdministratorForCustomerName();
        String customerType = EshopController.askAdministratorForCustomerType();
        System.out.println("Hello from CreateCustomerCommand");//TODO replace and add functionality
    }
}
