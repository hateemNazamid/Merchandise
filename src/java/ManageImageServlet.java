import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBConnection;

@WebServlet("/MerchImageServlet")
public class ManageImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get merchID from request
        String merchIDParam = request.getParameter("id");

        if (merchIDParam == null || merchIDParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing merchID");
            return;
        }

        try {
            int merchID = Integer.parseInt(merchIDParam);

            try (Connection conn = DBConnection.createConnection()) {
                String sql = "SELECT image FROM MERCHANDISE WHERE merchID=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, merchID);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    InputStream imageStream = rs.getBinaryStream("image");

                    if (imageStream != null) {
                        response.setContentType("image/jpeg");

                        OutputStream out = response.getOutputStream();
                        byte[] buffer = new byte[4096];
                        int bytesRead;

                        while ((bytesRead = imageStream.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }

                        imageStream.close();
                        out.flush();
                        out.close();
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found.");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Merchandise not found.");
                }
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid merchID");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // POST not needed but supported
    }

    @Override
    public String getServletInfo() {
        return "Displays BLOB image for given merchID";
    }
}
