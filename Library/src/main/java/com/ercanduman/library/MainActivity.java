package com.ercanduman.library;

public class MainActivity {
    public static void main(String[] args) {
        if (DatabaseHandler.establishConnection())
            System.out.println(GlobalConfigs.LOG_SUCCESS_PREFIX + "Database connecntion is successful!");

    }
}
