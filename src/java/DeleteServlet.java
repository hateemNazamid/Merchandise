
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBConnection;

public class DeleteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String table = request.getParameter("table");
        String column = request.getParameter("column");
        String idValue = request.getParameter("id");

        if (table != null && column != null && idValue != null) {
            try (Connection conn = DBConnection.createConnection()) {

                String sql = "DELETE FROM " + table + " WHERE " + column + " = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, idValue);
                stmt.executeUpdate();

                if (table.equalsIgnoreCase("MERCHANDISE")) {
                    response.sendRedirect("ManageMerchServlet");
                } else if (table.equalsIgnoreCase("CLUB")) {
                    response.sendRedirect("ManageClubServlet");
                } else if (table.equalsIgnoreCase("CUSTOMER")) {
                    response.sendRedirect("ManageCustomerServlet");
                } else if (table.equalsIgnoreCase("ORDER")) {
                    response.sendRedirect("ManageOrderServlet");
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errMessage", "Error deleting: " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errMessage", "Missing required fields.");
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
        return "Deletes a merchandise item by merchID";
    }// </editor-fold>

}
