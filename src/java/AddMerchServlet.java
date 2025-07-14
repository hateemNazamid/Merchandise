import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import util.DBConnection;

// Put this above your servlet class
@MultipartConfig(maxFileSize = 16177215) // 16MB limit
public class AddMerchServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String merch = request.getParameter("merch");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String clubID = request.getParameter("clubID");

        InputStream inputStream = null;
        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            inputStream = filePart.getInputStream();
        }

        try (Connection conn = DBConnection.createConnection()) {
            String sql = "INSERT INTO MERCHANDISE (name, category, price, stock, clubID, image) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, merch);
            stmt.setString(2, category);
            stmt.setDouble(3, price);
            stmt.setInt(4, stock);
            stmt.setString(5, clubID);
            if (inputStream != null) {
                stmt.setBlob(6, inputStream);
            } else {
                stmt.setNull(6, Types.BLOB);
            }
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errMessage", "Error uploading Merchandise: " + e.getMessage());
            request.getRequestDispatcher("addMerch.jsp").forward(request, response);
        }
        response.sendRedirect("ManageMerchServlet");
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

