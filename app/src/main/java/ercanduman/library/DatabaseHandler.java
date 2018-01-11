package ercanduman.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleDriver;

/**
 * Created on 10.01.2018.
 */

class DatabaseHandler {
    private static Connection connection;
    private static OracleCallableStatement oracleCallableStatement;
    private static OracleResultSet oracleResultSet;

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

    private static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
            if (oracleResultSet != null) oracleResultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void ListAllBooks() {
        try {
            oracleCallableStatement = (OracleCallableStatement) connection.prepareCall(GlobalConfigs.SQL_SELECT_ALL);
            oracleCallableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            oracleCallableStatement.execute();
            oracleResultSet = (OracleResultSet) oracleCallableStatement.getCursor(1);

            String format = "%-5s %-20s %-20s\n";
            System.out.println(GlobalConfigs.LOG_SUCCESS_PREFIX + "All Books:");
            System.out.printf(format, "Id ", "Book Name", "Author");

            while (oracleResultSet.next()) {
                System.out.printf(format, oracleResultSet.getString(1), oracleResultSet.getString(2), oracleResultSet.getString(3));
            }
            oracleCallableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(GlobalConfigs.LOG_FAILURE_PREFIX + "Error occured while working on SELECT SQL!...");
        } finally {
            closeConnection();
        }
    }
}
