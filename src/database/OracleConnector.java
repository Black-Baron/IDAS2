package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OracleConnector {

    private static Connection connection = null;
    private static String userName;
    private static String password;
    private static String serverName;
    private static int portNumber;
    private static String dbms;
    private static String sid;
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (OracleConnector.conn == null) {
            throw new SQLException("Session is not established");
        }
        return OracleConnector.conn;
    }
    
    public static boolean isConnected() throws SQLException {
        if (conn.isValid(1)) {
            return true;
        }
        return false;
    }
    public static void setUpConnection(String serverName, int portNumber, String sid, String userName, String password) throws SQLException {
        OracleConnector.serverName = serverName;
        OracleConnector.portNumber = portNumber;
        OracleConnector.dbms = "oracle:thin";
        OracleConnector.sid = sid;
        OracleConnector.userName = userName;
        OracleConnector.password = password;

        Properties connectionProps = new Properties();
        connectionProps.put("user", OracleConnector.userName);
        connectionProps.put("password", OracleConnector.password);
        OracleConnector.conn = DriverManager.getConnection(
                "jdbc:" + OracleConnector.dbms + ":@"
                + OracleConnector.serverName
                + ":" + OracleConnector.portNumber + ":" + OracleConnector.sid,
                connectionProps);
//z vyexportovaného souboru
//{"connections":[{"name":"IDATS1_holy","type":"jdbc","info":{"role":"","SavePassword":"true","OracleConnectionType":"BASIC","RaptorConnectionType":"Oracle","sid":"IDAS","ExportPasswordMode":"Key","customUrl":"jdbc:oracle:thin:@fei-sql1.upceucebny.cz:1521:IDAS","oraDriverType":"thin","NoPasswordConnection":"TRUE","password":"xAFO3JhozVo=","hostname":"fei-sql1.upceucebny.cz","driver":"oracle.jdbc.OracleDriver","port":"1521","subtype":"oraJDBC","OS_AUTHENTICATION":"false","ConnName":"IDATS1_holy","KERBEROS_AUTHENTICATION":"false","user":"st49636","ExportKeyChecksum":"9qaDGJnF9tkm46eTI/whMg=="}}]}
//connection slovně
//OracleConnector.conn = DriverManager.getConnection("jdbc:oracle:thin:@fei-sql1.upceucebny.cz:1521:IDAS");

        System.out.println("jdbc:" + OracleConnector.dbms + ":@" + OracleConnector.serverName + ":" + OracleConnector.portNumber + ":" + connectionProps);

        OracleConnector.conn.setAutoCommit(false);

        System.out.println("Connected to database");
    }

    public static String getConnectionString() {
        return "//jdbc:" + OracleConnector.dbms + ":@" + OracleConnector.serverName + ":" + OracleConnector.portNumber + ":" + OracleConnector.sid;
    }

    public static void closeConnection(boolean commit) throws SQLException {

        if (conn != null) {

            if (commit) {
                conn.commit();
            }

            conn.close();
            conn = null;
            System.out.println("Connection closed");
        }
    }

    public static java.sql.Date parseDate(String date, String format) {
        DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        java.sql.Date result = null;
        try {
            result = new java.sql.Date(df.parse(date).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(OracleConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
