import bean.ClubBean;
import util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/ManageClubServlet")
public class ManageClubServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        List<ClubBean> clubList = new ArrayList<>();

        try (Connection conn = DBConnection.createConnection()) {
            String sql = "SELECT c.clubID, c.clubName, c.adminID, a.name AS adminName " +
                         "FROM CLUB c JOIN ADMIN a ON c.adminID = a.adminID";

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    ClubBean club = new ClubBean();
                    club.setClubID(rs.getInt("clubID"));
                    club.setClubName(rs.getString("clubName"));
                    club.setAdminID(rs.getInt("adminID"));
                    club.setAdminName(rs.getString("adminName"));
                    clubList.add(club);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errMessage", "Database error: " + e.getMessage());
        }

        // Handle success message via ?success=added / updated / deleted
        String success = request.getParameter("success");
        if (success != null) {
            switch (success) {
                case "added":
                    request.setAttribute("successMessage", "Club added successfully!");
                    break;
                case "updated":
                    request.setAttribute("successMessage", "Club updated successfully!");
                    break;
                case "deleted":
                    request.setAttribute("successMessage", "Club deleted successfully!");
                    break;
            }
        }

        request.setAttribute("clubList", clubList);
        request.getRequestDispatcher("manageClub.jsp").forward(request, response);
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
        return "Handles displaying list of clubs and success messages.";
    }
}
