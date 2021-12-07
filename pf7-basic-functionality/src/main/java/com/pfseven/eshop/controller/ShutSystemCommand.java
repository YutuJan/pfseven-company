package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.pfseven.eshop.controller.EshopController.databaseService;

public class ShutSystemCommand implements Command{
    private final Logger logger = LoggerFactory.getLogger(ShutSystemCommand.class);

    @Override
    public void execute() {
        logger.info("Hello from ShutSystemCommand...");//TODO replace and add functionality
        databaseService.shutDB();
    }
}
