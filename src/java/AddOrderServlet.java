
import util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AddOrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Get customer ID from session
        HttpSession session = request.getSession(false);
        int customerID = (int) session.getAttribute("customerID");

        if (session == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }

        // Get form data
        int merchID = Integer.parseInt(request.getParameter("merchID"));
        int price = Integer.parseInt(request.getParameter("price"));
        Timestamp orderDate = Timestamp.valueOf(LocalDateTime.now());


        try {
            try (Connection conn = DBConnection.createConnection()) {
                String sql = "INSERT INTO ORDERTABLE (merchID, customerID, totalPrice, orderDate) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, merchID);
                    stmt.setInt(2, customerID);
                    stmt.setDouble(3, price);
                    stmt.setTimestamp(4, orderDate);

                    int rows = stmt.executeUpdate();
                    if (rows > 0) {
                        // Order success
                        response.sendRedirect("home_customer");
                    } else {
                        // Failed to insert
                        request.setAttribute("error", "Failed to place order.");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "System error: " + e.getMessage());
            request.getRequestDispatcher("order.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
