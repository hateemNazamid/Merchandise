import bean.MerchBean;
import util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/ManageMerchServlet")
public class ManageMerchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<MerchBean> merchList = new ArrayList<>();

        try (Connection conn = DBConnection.createConnection()) {
            String sql = "SELECT merchID, name, category, price, stock, clubID FROM MERCHANDISE";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                MerchBean merch = new MerchBean();
                merch.setMerchID(rs.getInt("merchID"));
                merch.setMerchName(rs.getString("name"));
                merch.setCategory(rs.getString("category"));
                merch.setPrice(rs.getDouble("price"));
                merch.setStock(rs.getInt("stock"));
                merch.setClubID(rs.getString("clubID"));
                merchList.add(merch);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("merchList", merchList);
        request.getRequestDispatcher("manageMerch.jsp").forward(request, response);
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
        return "Handles displaying list of merchandise";
    }
}
