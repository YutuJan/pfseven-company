package com.pfseven.eshop.controller;

import java.util.Scanner;

public class EshopController {
    public void enact(String commandID) throws Exception {
        Command command = CommandFactory.getCommand(commandID);
        command.execute();
    }

    public static String askAdministratorForCustomerID(){
        System.out.print("Enter customer id: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForProductID(){
        System.out.print("Enter product id: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForPaymentMethod(){
        System.out.print("Enter payment method: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForCustomerName(){
        System.out.print("Enter customer name: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForCustomerType(){
        System.out.print("Enter customer type: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForAnInput(){
        Scanner scanner = new Scanner(System.in);
        String inputFromAdministrator;

        inputFromAdministrator = scanner.nextLine();

        return inputFromAdministrator;
    }
}
