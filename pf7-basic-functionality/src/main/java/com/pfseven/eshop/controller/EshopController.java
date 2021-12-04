package com.pfseven.eshop.controller;

public class EshopController {
    public void enact(String commandID) throws Exception {
        Command command = CommandFactory.getCommand(commandID);
    }
}
