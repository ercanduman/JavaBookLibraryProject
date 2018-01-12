package ercanduman.library;

/**
 * Created on 10.01.2018.
 */

class GlobalConfigs {
    static final String LOG_SUCCESS_PREFIX = "\nINFO> ";
    static final String LOG_FAILURE_PREFIX = "\nERROR> ";
    static final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
    static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String ORACLE_DB_USERNAME = "EDUMAN";
    static final String ORACLE_DB_PASSWORD = "Erc787dmm";

    static final String SQL_SELECT_ALL = "{? = call javalibraryapp.listallbooks()}";
    static final String SQL_DELETE_BY_ID = " { ? = call javalibraryapp.removebook(?)}"; // for functions "? = call" used
    static final String SQL_ADD_NEW_ONE = "BEGIN javalibraryapp.addnewbook(?, ?, ?); END;"; // for procedures "begin-end block" used
}

