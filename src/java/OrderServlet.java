/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bean.MerchBean;
import bean.OrderBean;
import bean.RegisterBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DBConnection;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        RegisterBean customer = (RegisterBean) session.getAttribute("customer");
        if (customer == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }

        int customerID = customer.getId();
        List<OrderBean> orderList = new ArrayList<>();

        try (Connection conn = DBConnection.createConnection()) {
            String sql = "SELECT o.orderID, o.customerID, o.totalPrice, o.orderDate, "
                    + "m.merchID, m.name AS merchName "
                    + "FROM ORDERS o "
                    + "JOIN MERCHANDISE m ON o.merchID = m.merchID "
                    + "WHERE o.customerID = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, customerID);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        OrderBean order = new OrderBean();
                        order.setOrderID(rs.getInt("orderID"));
                        order.setCustomerID(rs.getInt("customerID"));
                        order.setTotalPrice(rs.getDouble("totalPrice"));
                        order.setOrderDate(rs.getDate("orderDate"));

                        MerchBean merch = new MerchBean();
                        merch.setMerchID(rs.getInt("merchID"));
                        merch.setMerchName(rs.getString("merchName"));

                        order.setMerch(merch);
                        orderList.add(order);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("customerOrders", orderList);
        request.getRequestDispatcher("manageOrderCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}