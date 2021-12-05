package com.pfseven.eshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchSystemCommand implements Command{
    private Logger logger = LoggerFactory.getLogger(LaunchSystemCommand.class);

    @Override
    public void execute() {
        logger.info("Hello from LaunchSystemCommand...");//TODO replace and add functionality
    }
}
