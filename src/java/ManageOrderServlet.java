
import bean.OrderBean;
import util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/ManageOrderServlet")
public class ManageOrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<OrderBean> orderList = new ArrayList<>();
        int totalOrders = 0;
        double totalRevenue = 0;
        List<Map<String, Object>> topMerchList = new ArrayList<>();

        try (Connection conn = DBConnection.createConnection()) {

            // 1. Fetch all orders
            String sql = "SELECT * FROM ORDERS";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    OrderBean order = new OrderBean();
                    order.setOrderID(rs.getInt("orderID"));
                    order.setMerchID(rs.getInt("merchID"));
                    order.setCustomerID(rs.getInt("customerID"));
                    order.setTotalPrice(rs.getDouble("totalPrice"));
                    order.setOrderDate(rs.getDate("orderDate"));
                    orderList.add(order);
                }
            }

            // 2. Total orders
            try (Statement stmt1 = conn.createStatement();
                    ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(*) AS total FROM ORDERS")) {
                if (rs1.next()) {
                    totalOrders = rs1.getInt("total");
                }
            }

            // 3. Total revenue
            try (Statement stmt2 = conn.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("SELECT SUM(totalPrice) AS revenue FROM ORDERS")) {
                if (rs2.next()) {
                    totalRevenue = rs2.getDouble("revenue");
                }
            }

            // 4. Top-selling merch
            try (Statement stmt3 = conn.createStatement();
                    ResultSet rs3 = stmt3.executeQuery("SELECT merchID, COUNT(*) AS orderCount FROM ORDERS GROUP BY merchID ORDER BY orderCount DESC LIMIT 5")) {
                while (rs3.next()) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("merchID", rs3.getInt("merchID"));
                    item.put("orderCount", rs3.getInt("orderCount"));
                    topMerchList.add(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("orderList", orderList);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("topMerchList", topMerchList);

        request.getRequestDispatcher("manageOrder.jsp").forward(request, response);
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
