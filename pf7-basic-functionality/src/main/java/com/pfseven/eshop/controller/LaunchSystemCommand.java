package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static com.pfseven.eshop.controller.EshopController.databaseService;

public class LaunchSystemCommand implements Command{
    private final Logger logger = LoggerFactory.getLogger(LaunchSystemCommand.class);

    @Override
    public void execute() {
        logger.info("Hello from LaunchSystemCommand...");//TODO replace and add functionality
        try{
            databaseService.launchDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
