package com.BiF.BananaSuprema.BananaSuprema.DBOps;

import com.BiF.BananaSuprema.BananaSuprema.Bean.Configuration;

import java.sql.*;
import java.util.Properties;

public class DBBaseOps {

    private static final String ERR_001 = "Can't connect to database. check if the connection has been closed";
    private static final Properties PROPERTIES = Configuration.getInstance().getProperties();

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = PROPERTIES.getProperty("database.url");

    //Database Credentials
    //Admin
    private static final String USER = PROPERTIES.getProperty("database.User");
    private static final String PASS = PROPERTIES.getProperty("database.Password");
    protected Connection conn = null;
    protected PreparedStatement currentStmt = null;

    protected void openDb() throws Exception {
        try {

            if (conn != null) {
                throw new Exception(ERR_001);
            } else {

                //Register JDBC driver
                Class.forName(JDBC_DRIVER);

                //Open a connection
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            throw exc;
        }
    }

    /**
     * closes database connections
     */
    protected void closeDb() {
        try {
            if (currentStmt != null) {
                currentStmt.close();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn = null;
    }

    protected Object executeQuery(String query, Object[] params) throws SQLException {
        Object ris;
        currentStmt = conn.prepareStatement(query);
        if ((params != null) && (params.length > 0)) {
            for (int i = 0; i < params.length; i++) {
                currentStmt.setObject(i + 1, params[i]);
            }
        }
        if (query.toLowerCase().trim().startsWith("select")) {
            ris = currentStmt.executeQuery();
        } else {
            ris = currentStmt.executeUpdate();
        }
        return ris;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
