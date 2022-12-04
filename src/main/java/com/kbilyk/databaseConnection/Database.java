package com.kbilyk.databaseConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Database {
    private static final Logger logger = LogManager.getLogger(Database.class.getName()); // logger of that class
    public static java.sql.Connection connection = null; // connection to database

    // Database info
    private static final String url = "jdbc:sqlserver://DESKTOP-VDL5LBV\\MSSQLSERVER:1433;database=flowershopDB";
    private static final String user = "hzz";
    private static final String password = "#daeMON32";

    /**
     * A method that checks whether there is a connection to the database. I
     * If there is no connection, we establish it
     * @return The connection to database
     */
    public static java.sql.Connection getConnection() {
        if(connection == null){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection  = java.sql.DriverManager.getConnection(url, user, password);
                logger.info("The connection to the database was successfully established!" +
                        "\nURL: " + url + "\nFor user: " + user);
            }
            catch (Exception e) {
                logger.error("Error trace in getConnection() : " + e.getMessage());
            }
        }
        return  connection;
    }

    /**
     * A method close the connection to database
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("The connection to the database was successfully closed!" +
                        "\nURL: " + url + "\nFor user: " + user);
            } catch (SQLException e) {
                logger.error("Error trace in closeConnection() : " + e.getMessage());
            }
        }
    }
}
