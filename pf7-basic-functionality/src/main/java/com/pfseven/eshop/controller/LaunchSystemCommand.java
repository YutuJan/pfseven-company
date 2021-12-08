package com.pfseven.eshop.controller;

import java.sql.SQLException;

import static com.pfseven.eshop.controller.EshopController.databaseService;

public class LaunchSystemCommand implements Command{

    @Override
    public void execute() {
        try{
            databaseService.launchDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
