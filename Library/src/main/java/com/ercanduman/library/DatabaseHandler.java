package com.ercanduman.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

/**
 * Created on 10.01.2018.
 */

class DatabaseHandler {
    private static Connection connection;
    private static ResultSet resultSet;

    static boolean establishConnection() {
        try {
            Class.forName(GlobalConfigs.ORACLE_DRIVER);
            DriverManager.registerDriver(new OracleDriver());

            connection = DriverManager.getConnection(GlobalConfigs.ORACLE_URL, GlobalConfigs.ORACLE_DB_USERNAME, GlobalConfigs.ORACLE_DB_PASSWORD);
            System.out.println(GlobalConfigs.LOG_SUCCESS_PREFIX + "Database connecntion is successful!");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(GlobalConfigs.LOG_FAILURE_PREFIX + "Databse driver error! \n ");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(GlobalConfigs.LOG_FAILURE_PREFIX + "SQL running error! \n ");
        }
        return false;
    }
}
