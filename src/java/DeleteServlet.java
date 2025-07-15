import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBConnection;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String table = request.getParameter("table");
        String column = request.getParameter("column");
        String idValue = request.getParameter("id");

        if (table != null && column != null && idValue != null) {
            try (Connection conn = DBConnection.createConnection()) {

                // 👇 Custom delete logic if deleting from CLUB (to avoid FK violation)
                if (table.equalsIgnoreCase("CLUB")) {
                    // First delete all merchandise linked to this club
                    String deleteMerch = "DELETE FROM MERCHANDISE WHERE clubID = ?";
                    try (PreparedStatement merchStmt = conn.prepareStatement(deleteMerch)) {
                        merchStmt.setString(1, idValue);
                        merchStmt.executeUpdate();
                    }

                    // Then delete the club
                    String deleteClub = "DELETE FROM CLUB WHERE clubID = ?";
                    try (PreparedStatement clubStmt = conn.prepareStatement(deleteClub)) {
                        clubStmt.setString(1, idValue);
                        clubStmt.executeUpdate();
                    }

                    // Redirect to club management page
                    response.sendRedirect("ManageClubServlet?success=deleted");
                    return;
                }

                // 🧼 Default delete logic for other tables
                String sql = "DELETE FROM " + table + " WHERE " + column + " = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, idValue);
                    stmt.executeUpdate();
                }

                // ✅ Redirect based on table
                switch (table.toUpperCase()) {
                    case "MERCHANDISE":
                        response.sendRedirect("ManageMerchServlet");
                        break;
                    case "CUSTOMER":
                        response.sendRedirect("ManageCustomerServlet");
                        break;
                    case "ORDER":
                        response.sendRedirect("ManageOrderServlet");
                        break;
                    default:
                        response.sendRedirect("home_admin.jsp");
                        break;
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

    // HTTP GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // HTTP POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Description
    @Override
    public String getServletInfo() {
        return "DeleteServlet handles deletions with FK checks.";
    }
}
