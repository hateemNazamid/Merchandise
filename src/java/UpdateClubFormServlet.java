
import bean.AdminBean;
import bean.ClubBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBConnection;

public class UpdateClubFormServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        int clubID = Integer.parseInt(request.getParameter("clubID"));
        ClubBean club = null;
        List<AdminBean> adminList = new ArrayList<>();

        try (Connection conn = DBConnection.createConnection()) {

            // Get club info with admin name
            String sql = "SELECT c.clubID, c.clubName, c.adminID, a.name AS adminName "
                    + "FROM CLUB c "
                    + "JOIN ADMIN a ON c.adminID = a.adminID "
                    + "WHERE c.clubID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, clubID);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        club = new ClubBean();
                        club.setClubID(rs.getInt("clubID"));
                        club.setClubName(rs.getString("clubName"));
                        club.setAdminID(rs.getInt("adminID"));
                        club.setAdminName(rs.getString("adminName"));
                    }
                }
            }

            // Get all admins
            String sqlAdmins = "SELECT adminID, name FROM ADMIN";
            try (PreparedStatement stmt = conn.prepareStatement(sqlAdmins);
                    ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    AdminBean admin = new AdminBean();
                    admin.setAdminID(rs.getInt("adminID"));
                    admin.setName(rs.getString("name"));
                    adminList.add(admin);
                }
            }

            request.setAttribute("club", club);
            request.setAttribute("adminList", adminList);
            RequestDispatcher rd = request.getRequestDispatcher("updateClub.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
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
