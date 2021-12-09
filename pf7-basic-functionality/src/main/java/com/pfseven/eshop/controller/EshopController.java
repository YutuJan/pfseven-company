package com.pfseven.eshop.controller;

import com.pfseven.eshop.model.CustomerService;
import com.pfseven.eshop.model.DatabaseService;
import com.pfseven.eshop.model.ReportService;
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
        logger.info("Enter customer id: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForProductID(){
        logger.info("Enter product id: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForPaymentMethod(){
        String inputFromAdministrator = "";

        do{
            logger.info("Enter payment method: ");
            inputFromAdministrator =  askAdministratorForAnInput();
            for (PaymentCategories categories: PaymentCategories.values()) {
                if (inputFromAdministrator.equals(categories.value)) {
                    return inputFromAdministrator;
                }
            }
            logger.info("Value {} is not correct", inputFromAdministrator);
            logger.info("Correct values are: {}",
                    PaymentCategories.CASH.value + ", " +
                            PaymentCategories.CREDIT_CARD.value);
            logger.info("Please try again!");
        }while (true);
    }

    public static String askAdministratorForCustomerName(){
        logger.info("Enter customer name: ");
        return askAdministratorForAnInput();
    }

    public static String askAdministratorForCustomerType(){
        String inputFromAdministrator = "";

        do{
            logger.info("Enter customer type: ");
            inputFromAdministrator =  askAdministratorForAnInput();
            for (CustomerCategories categories: CustomerCategories.values()) {
                if (inputFromAdministrator.equals(categories.value)) {
                    return inputFromAdministrator;
                }
            }
            logger.info("Value {} is not correct", inputFromAdministrator);
            logger.info("Correct values are: {}",
                    CustomerCategories.INDIVIDUAL.value + ", " +
                    CustomerCategories.BUSINESS.value + ", " +
                    CustomerCategories.GOVERNMENT.value);
            logger.info("Please try again!");
        }while (true);
    }

    public static String askAdministratorForAnInput(){
        Scanner scanner = new Scanner(System.in);
        String inputFromAdministrator;

        inputFromAdministrator = scanner.nextLine();

        return inputFromAdministrator;
    }
}
