package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShutSystemCommand implements Command{
    private Logger logger = LoggerFactory.getLogger(ShutSystemCommand.class);

    @Override
    public void execute() {
        logger.info("Hello from ShutSystemCommand...");//TODO replace and add functionality
    }
}
