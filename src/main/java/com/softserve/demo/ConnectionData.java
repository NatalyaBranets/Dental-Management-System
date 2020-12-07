package com.softserve.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionData {
    private static Connection connect;
    private static Logger log = Logger.getLogger(ConnectionData.class.getName());

    public static Connection getConnectionData() {

        if (connect == null) {

            String url = "jdbc:mysql://localhost:3306/dentistry";
            String username = "";
            String password = "";
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                connect = DriverManager.getConnection(url, username, password);
                if (connect == null) {
                    throw new SQLException("Connection error");
                }
            } catch (SQLException ex) {
                log.log(Level.SEVERE, null, ex.getStackTrace());
            }
        }
        return connect;
    }

}
