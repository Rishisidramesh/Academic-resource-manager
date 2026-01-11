package com.instpro.irp;

import com.instpro.irp.config.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestOracleConnection {

    public static void main(String[] args) {
        // Using try-with-resources to auto-close connection
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
