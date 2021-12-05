package com.pfseven.eshop.controller;

import com.pfseven.eshop.model.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class EshopController {
    private static Logger logger = LoggerFactory.getLogger(EshopController.class);
    protected static DatabaseService databaseService = new DatabaseService();

    public void enact(String commandID) throws Exception {
        Command command = CommandFactory.getCommand(commandID);
        command.execute();
    }

    public static String askAdministratorForCustomerID(){
        logger.info("Enter customer id: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForProductID(){
        logger.info("Enter product id: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForPaymentMethod(){
        logger.info("Enter payment method: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForCustomerName(){
        logger.info("Enter customer name: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForCustomerType(){
        logger.info("Enter customer type: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForAnInput(){
        Scanner scanner = new Scanner(System.in);
        String inputFromAdministrator;

        inputFromAdministrator = scanner.nextLine();

        return inputFromAdministrator;
    }
}
