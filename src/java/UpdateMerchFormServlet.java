
import bean.ClubBean;
import bean.MerchBean;
import util.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateMerchFormServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        String merchID = request.getParameter("merchID");

        MerchBean merch = null;
        List<ClubBean> clubs = new ArrayList<>();

        try {
            Connection conn = DBConnection.createConnection();
            // Fetch merchandise by merchID
            String sql = "SELECT * FROM MERCHANDISE WHERE merchID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(merchID));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    merch = new MerchBean();
                    merch.setData(
                            rs.getInt("merchID"),
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getDouble("price"),
                            rs.getInt("stock"),
                            rs.getString("clubID"), // or rs.getInt("clubID") if clubID is int
                            null // include image if needed
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            Connection conn = DBConnection.createConnection();
            // Fetch all clubs
            String sql = "SELECT clubID, clubName FROM CLUB";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ClubBean clubBean = new ClubBean();
                clubBean.setClubID(rs.getInt("clubID"));
                clubBean.setClubName(rs.getString("clubName"));
                clubs.add(clubBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("merch", merch);
        request.setAttribute("clubs", clubs);
        RequestDispatcher rd = request.getRequestDispatcher("updateMerch.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateMerchFormServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateMerchFormServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Loads merchandise and club info for update form.";
    }
}
