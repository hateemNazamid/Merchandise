import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import util.DBConnection;

@WebServlet("/UpdateMerchServlet")
@MultipartConfig(maxFileSize = 16177215) // 16MB max for file upload
public class UpdateMerchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int merchID = Integer.parseInt(request.getParameter("merchID"));
        String merchName = request.getParameter("merch");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String clubID = request.getParameter("clubID");

        Part filePart = request.getPart("image");
        InputStream imageStream = (filePart != null && filePart.getSize() > 0) ? filePart.getInputStream() : null;

        try (Connection conn = DBConnection.createConnection()) {
            String sql;
            PreparedStatement stmt;

            if (imageStream != null) {
                sql = "UPDATE MERCHANDISE SET name=?, category=?, price=?, stock=?, clubID=?, image=? WHERE merchID=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, merchName);
                stmt.setString(2, category);
                stmt.setDouble(3, price);
                stmt.setInt(4, stock);
                stmt.setString(5, clubID);
                stmt.setBlob(6, imageStream);
                stmt.setInt(7, merchID);
            } else {
                sql = "UPDATE MERCHANDISE SET name=?, category=?, price=?, stock=?, clubID=? WHERE merchID=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, merchName);
                stmt.setString(2, category);
                stmt.setDouble(3, price);
                stmt.setInt(4, stock);
                stmt.setString(5, clubID);
                stmt.setInt(6, merchID);
            }

            stmt.executeUpdate();

            // Redirect to merchandise list page
            response.sendRedirect("ManageMerchServlet");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errMessage", "Update failed: " + e.getMessage());
            request.getRequestDispatcher("updateMerch.jsp").forward(request, response);
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
        return "Handles update of merchandise";
    }
}
