/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection createConnection() {
        Connection conn = null;
        try {
            // Load Derby client driver (optional in modern Java)
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            // JDBC URL for Derby network server
            String url = "jdbc:derby://localhost:1527/customers";
            String user = "app";       // default Derby username
            String password = "app";   // default Derby password

            // Establish connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return conn;
    }
}
