package br.com.aaesocial.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final String TAG = DBConnection.class.getSimpleName();

    private static final String DB_URL = "jdbc:mysql://localhost:3306/AAESocial";
    private static final String DB_USERNAME = "fernandoms";
    private static final String DB_PASSWORD = "123456";

    private static DBConnection ourInstance = new DBConnection();
    private static Connection connection;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        return ourInstance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection
                        (DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}
