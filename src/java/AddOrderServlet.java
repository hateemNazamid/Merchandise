
import util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Get customer ID from session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerID") == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }
        int customerID = (int) session.getAttribute("customerID");

        try {
            // Get form data
            int merchID = Integer.parseInt(request.getParameter("merchID"));
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity")); // Still passed from form

            Timestamp orderDate = Timestamp.valueOf(LocalDateTime.now());

            try (Connection conn = DBConnection.createConnection()) {
                conn.setAutoCommit(false); // Start transaction

                // Insert order (without quantity)
                String insertSql = "INSERT INTO ORDERS (merchID, customerID, totalPrice, orderDate) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, merchID);
                    insertStmt.setInt(2, customerID);
                    insertStmt.setDouble(3, price);
                    insertStmt.setTimestamp(4, orderDate);
                    insertStmt.executeUpdate();
                }

                // Deduct quantity from merchandise stock
                String updateStockSql = "UPDATE MERCHANDISE SET stock = stock - ? WHERE merchID = ? AND stock >= ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateStockSql)) {
                    updateStmt.setInt(1, quantity);
                    updateStmt.setInt(2, merchID);
                    updateStmt.setInt(3, quantity);
                    int rowsAffected = updateStmt.executeUpdate();

                    if (rowsAffected == 0) {
                        // Stock not sufficient or merch not found
                        conn.rollback();
                        request.setAttribute("error", "Not enough stock available for this item.");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        return;
                    }
                }

                conn.commit(); // Success

                // Redirect on success
                response.sendRedirect("home_customer.jsp");

            } catch (Exception ex) {
                ex.printStackTrace();
                request.setAttribute("error", "Transaction failed: " + ex.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid request: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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

    @Override
    public String getServletInfo() {
        return "Handles placing an order and deducting stock";
    }
}
