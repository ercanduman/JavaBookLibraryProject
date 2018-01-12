package ercanduman.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

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
    private static final String outpurFormat = "%-3s %-30s %-30s\n";

    static boolean establishConnection() {
        try {
            Class.forName(GlobalConfigs.ORACLE_DRIVER);
            DriverManager.registerDriver(new OracleDriver());
            connection = DriverManager.getConnection(GlobalConfigs.ORACLE_URL, GlobalConfigs.ORACLE_DB_USERNAME, GlobalConfigs.ORACLE_DB_PASSWORD);
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
            if (oracleCallableStatement != null) oracleCallableStatement.close();
            if (oracleResultSet != null) oracleResultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void ListAllBooks() {
        establishConnection();
        try {
            oracleCallableStatement = (OracleCallableStatement) connection.prepareCall(GlobalConfigs.SQL_SELECT_ALL);
            oracleCallableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            oracleCallableStatement.execute();
            oracleResultSet = (OracleResultSet) oracleCallableStatement.getCursor(1);

            System.out.println(GlobalConfigs.LOG_SUCCESS_PREFIX + "All Books:");
            System.out.printf(outpurFormat, "Id ", "Book Name", "Author");

            while (oracleResultSet.next()) {
                System.out.printf(outpurFormat, oracleResultSet.getString(1), oracleResultSet.getString(2), oracleResultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(GlobalConfigs.LOG_FAILURE_PREFIX + "Error occured while working on SELECT SQL!...");
        } finally {
            closeConnection();
        }
    }

    static void AddNewBook2Database(String bookName, String author) {
        establishConnection();
        try {
            oracleCallableStatement = (OracleCallableStatement) connection.prepareCall(GlobalConfigs.SQL_ADD_NEW_ONE);
            oracleCallableStatement.setString(1, bookName);
            oracleCallableStatement.setString(2, author);
            oracleCallableStatement.registerOutParameter(3, Types.VARCHAR);
            oracleCallableStatement.execute();

            String result = oracleCallableStatement.getString(3); // since 3rd one is output parameter, 3 should be here
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    static void RemoveBookFromDatabse(int id) {
        establishConnection();
        try {
            oracleCallableStatement = (OracleCallableStatement) connection.prepareCall(GlobalConfigs.SQL_DELETE_BY_ID);
            oracleCallableStatement.setInt(2, id);
            oracleCallableStatement.registerOutParameter(1, Types.VARCHAR);
            oracleCallableStatement.execute();

            System.out.println(oracleCallableStatement.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
