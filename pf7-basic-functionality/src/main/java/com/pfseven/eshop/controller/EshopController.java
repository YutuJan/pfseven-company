package com.pfseven.eshop.controller;

import com.pfseven.eshop.service.CustomerService;
import com.pfseven.eshop.service.DatabaseService;
import com.pfseven.eshop.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Scanner;

public class EshopController {
    private final static Logger logger = LoggerFactory.getLogger(EshopController.class);
    protected static DatabaseService databaseService = new DatabaseService();
    protected static Statement statement = DatabaseService.getStatement();
    protected static ReportService reportService = new ReportService(statement);
    protected static CustomerService customerService = new CustomerService(statement);

    enum PaymentCategories {
        CASH("cash"), CREDIT_CARD("credit");

        String value;

        PaymentCategories(String value){
            this.value = value;
        }
    }

    enum CustomerCategories {
        INDIVIDUAL("B2C"), BUSINESS("B2B"), GOVERNMENT("B2G");

        String value;

        CustomerCategories(String value){
            this.value = value;
        }
    }

    public void enact(String commandID) throws Exception {
        Command command = CommandFactory.getCommand(commandID);
        command.execute();
    }

    public static String askAdministratorForCustomerID(){
        logger.info("ENTER CUSTOMER'S ID: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForProductID(){
        logger.info("ENTER PRODUCT'S ID: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForPaymentMethod(){
        String inputFromAdministrator = "";

        do{
            logger.info("ENTER A PAYMENT METHOD: ");
            inputFromAdministrator =  askAdministratorForAnInput();
            for (PaymentCategories categories: PaymentCategories.values()) {
                if (inputFromAdministrator.equals(categories.value)) {
                    return inputFromAdministrator;
                }
            }
            logger.info("VALUE {} IS NOT CORRECT", inputFromAdministrator);
            logger.info("CORRECT VALUES ARE: {}",
                    PaymentCategories.CASH.value + ", " +
                            PaymentCategories.CREDIT_CARD.value);
            logger.info("PLEASE TRY AGAIN!");
        }while (true);
    }

    public static String askAdministratorForCustomerName(){
        logger.info("ENTER CUSTOMER'S NAME: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForCustomerType(){
        String inputFromAdministrator = "";

        do{
            logger.info("ENTER CUSTOMER'S TYPE: ");
            inputFromAdministrator =  askAdministratorForAnInput();
            for (CustomerCategories categories: CustomerCategories.values()) {
                if (inputFromAdministrator.equals(categories.value)) {
                    return inputFromAdministrator;
                }
            }
            logger.info("VALUE {} IS NOT CORRECT", inputFromAdministrator);
            logger.info("CORRECT VALUES ARE: {}",
                    CustomerCategories.INDIVIDUAL.value + ", " +
                    CustomerCategories.BUSINESS.value + ", " +
                    CustomerCategories.GOVERNMENT.value);
            logger.info("PLEASE TRY AGAIN!");
        }while (true);
    }

    public static String askAdministratorForAnInput(){
        Scanner scanner = new Scanner(System.in);
        String inputFromAdministrator;

        inputFromAdministrator = scanner.nextLine();

        return inputFromAdministrator;
    }
}
