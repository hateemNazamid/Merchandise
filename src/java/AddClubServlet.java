import util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/AddClubServlet")
public class AddClubServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false); // false = do not create new session
        if (session == null || session.getAttribute("adminID") == null) {
            response.sendRedirect("login.jsp"); // Not logged in
            return;
        }

        String clubName = request.getParameter("clubName");
        int adminID = (int) session.getAttribute("adminID");

        if (clubName == null || clubName.trim().isEmpty()) {
            request.setAttribute("errMessage", "Club name cannot be empty.");
            request.getRequestDispatcher("addClub.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DBConnection.createConnection()) {
            String sql = "INSERT INTO CLUB (clubName, adminID) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, clubName);
                stmt.setInt(2, adminID);
                
                // Redirect on success
                if(stmt.executeUpdate()>0){
                    response.sendRedirect("ManageClubServlet?success=added");   
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errMessage", "Error uploading Club: " + e.getMessage());
            request.getRequestDispatcher("addClub.jsp").forward(request, response);
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
        return "Handles adding a new club to the system.";
    }
}
