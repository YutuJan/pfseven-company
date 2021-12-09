package com.pfseven.eshop.view;

import com.pfseven.eshop.controller.EshopController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class EshopView {
    private static final int CREATE_CUSTOMER_OPTION = 1;
    private static final int ADD_PRODUCT_TO_AN_ORDER_OPTION = 2;
    private static final int REMOVE_PRODUCT_TO_AN_ORDER_OPTION = 3;
    private static final int BUY_PRODUCTS_OPTION = 4;

    private static final int REPORT_TOTAL_NUMBER_AND_COST_OF_PURCHASES_BY_CUSTOMER_ID_OPTION = 5;
    private static final int REPORT_TOTAL_NUMBER_AND_COST_OF_PURCHASES_BY_CUSTOMER_CATEGORY_OPTION = 6;
    private static final int REPORT_TOTAL_NUMBER_AND_COST_OF_PURCHASES_BY_PAYMENT_METHOD_OPTION = 7;
    private static final int REPORT_CUSTOMERS_WHO_PURCHASED_MOST_EXPENSIVE_PRODUCT_OPTION = 8;

    private static final int SHUT_SYSTEM_OPTION = 9;

    private static final Logger logger = LoggerFactory.getLogger(EshopView.class);
    private static EshopController controller = new EshopController();

    public static void main(String[] args) {
        try {
            askAdministratorForAnAction();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void askAdministratorForAnAction() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int adminOption;

        while (true) {
            showAdministratorPossibleActions();
            adminOption = scanner.nextInt();
            if (adminOption == SHUT_SYSTEM_OPTION) {
                controller.enact("ShutSystem");
                break;
            } else if (adminOption == CREATE_CUSTOMER_OPTION) {
                controller.enact("CreateCustomer");
            } else if (adminOption == ADD_PRODUCT_TO_AN_ORDER_OPTION) {
                controller.enact("AddProductOrder");
            } else if (adminOption == BUY_PRODUCTS_OPTION) {
                controller.enact("BuyProducts");
            } else if (adminOption == REPORT_TOTAL_NUMBER_AND_COST_OF_PURCHASES_BY_CUSTOMER_ID_OPTION) {
                controller.enact("ReportTotalNumberAndCostOfPurchasesByCustomerID");
            } else if (adminOption == REPORT_TOTAL_NUMBER_AND_COST_OF_PURCHASES_BY_CUSTOMER_CATEGORY_OPTION) {
                controller.enact("ReportTotalNumberAndCostOfPurchasesByCustomerCategory");
            } else if (adminOption == REPORT_TOTAL_NUMBER_AND_COST_OF_PURCHASES_BY_PAYMENT_METHOD_OPTION) {
                controller.enact("ReportTotalNumberAndCostOfPurchasesByPaymentMethod");
            } else if (adminOption == REPORT_CUSTOMERS_WHO_PURCHASED_MOST_EXPENSIVE_PRODUCT_OPTION) {
                controller.enact("ReportCustomersWhoPurchasedMostExpensiveProduct");
            } else if (adminOption == REMOVE_PRODUCT_TO_AN_ORDER_OPTION){
                controller.enact("RemoveProductOrder");
            } else {
                logger.info("This option is not available, please try again...");
            }
        }
    }

    private static void showAdministratorPossibleActions() {
        logger.info("------------------------------------");
        logger.info("Choose one of the following options:");
        logger.info(CREATE_CUSTOMER_OPTION + ") " + "CREATE CUSTOMER");
        logger.info(ADD_PRODUCT_TO_AN_ORDER_OPTION + ") " +
                "ADD PRODUCT TO AN ORDER");
        logger.info(REMOVE_PRODUCT_TO_AN_ORDER_OPTION + ") " +
                "REMOVE PRODUCT TO AN ORDER");
        logger.info(BUY_PRODUCTS_OPTION + ") " +
                "BUY PRODUCTS");

        logger.info(REPORT_TOTAL_NUMBER_AND_COST_OF_PURCHASES_BY_CUSTOMER_ID_OPTION + ") " +
                "REPORT TOTAL NUMBER AND COST OF PURCHASES BY CUSTOMER ID");
        logger.info(REPORT_TOTAL_NUMBER_AND_COST_OF_PURCHASES_BY_CUSTOMER_CATEGORY_OPTION + ") " +
                "REPORT TOTAL NUMBER AND COST OF PURCHASES BY CUSTOMER CATEGORY");
        logger.info(REPORT_TOTAL_NUMBER_AND_COST_OF_PURCHASES_BY_PAYMENT_METHOD_OPTION + ") " +
                "REPORT TOTAL NUMBER AND COST OF PURCHASES BY PAYMENT METHOD");
        logger.info(REPORT_CUSTOMERS_WHO_PURCHASED_MOST_EXPENSIVE_PRODUCT_OPTION + ") " +
                "REPORT CUSTOMERS WHO PURCHASED MOST EXPENSIVE PRODUCT");


        logger.info(SHUT_SYSTEM_OPTION + ") " + "SHUT SYSTEM");
    }
}
