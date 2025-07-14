/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.RegisterBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;
// or use: import java.util.UUID; (for UUID approach)

public class RegisterDao {

    public String registerUser(RegisterBean registerBean) {

        String username = registerBean.getUsername();
        String password = registerBean.getPassword();
        String name = registerBean.getName();
        String email = registerBean.getEmail();
        String address = registerBean.getAddress();
        String type = registerBean.getType();

        String sql = "";
        if (type.equals("user")) {
            sql = "INSERT INTO CUSTOMER (EMAIL, FULLNAME, USERNAME, PASSWORD, ADDRESS) VALUES (?, ?, ?, ?, ?)";
        } else if (type.equals("admin")) {
            sql = "INSERT INTO ADMIN (NAME, USERNAME, PASSWORD) VALUES (?, ?, ?)";
        }

        try (Connection con = DBConnection.createConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            if (type.equals("user")) {
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, username);
                ps.setString(4, password);
                ps.setString(5, address);
            } else if (type.equals("admin")) {
                ps.setString(1, name);
                ps.setString(2, username);
                ps.setString(3, password);

            }

            int rowsInserted = ps.executeUpdate();
            return (rowsInserted > 0) ? "SUCCESS" : "Registration Failed";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Registration Failed";
        }
    }

    public String updateCustomer(RegisterBean customer) {
        String sql = "UPDATE CUSTOMER SET EMAIL=?, FULLNAME=?, PASSWORD=?, ADDRESS=? WHERE CUSTOMERID=?";

        try (Connection con = DBConnection.createConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getPassword());
            ps.setString(4, customer.getAddress());
            ps.setInt(5, customer.getId());  // <-- This is important

            int rowsUpdated = ps.executeUpdate();
            return (rowsUpdated > 0) ? "SUCCESS" : "Update Failed";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Update Failed: " + e.getMessage();
        }
    }

}
