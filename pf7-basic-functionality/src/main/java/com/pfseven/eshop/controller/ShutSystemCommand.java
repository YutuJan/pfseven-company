package com.pfseven.eshop.controller;

import static com.pfseven.eshop.controller.EshopController.databaseService;

public class ShutSystemCommand implements Command {

    @Override
    public void execute() {
        databaseService.shutDB();
    }
}
