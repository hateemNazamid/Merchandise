import util.DBConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/UpdateClubServlet")
public class UpdateClubServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        int clubID = Integer.parseInt(request.getParameter("clubID"));
        String clubName = request.getParameter("clubName");
        int adminID = Integer.parseInt(request.getParameter("adminID"));

        try (Connection conn = DBConnection.createConnection()) {

            String sql = "UPDATE CLUB SET clubName = ?, adminID = ? WHERE clubID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, clubName);
                pstmt.setInt(2, adminID);
                pstmt.setInt(3, clubID);

                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    response.sendRedirect("ManageClubServlet?success=updated");
                } else {
                    request.setAttribute("errMessage", "Update failed. Club not found.");
                    request.getRequestDispatcher("updateClub.jsp").forward(request, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("updateClub.jsp").forward(request, response);
        }
    }


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
