import bean.ClubBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBConnection;

@WebServlet(urlPatterns = {"/AddMerchFormServlet"})
public class AddMerchFormServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ClubBean> clubs = new ArrayList<>();
        List<ClubBean> clubs1 = new ArrayList<>();
        
        ClubBean cb = new ClubBean();
        clubs1.add(cb);
        

        try (Connection con = DBConnection.createConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT clubID, clubName FROM CLUB")) {

            while (rs.next()) {
                ClubBean clubBean = new ClubBean();
                clubBean.setClubID(rs.getInt("clubID"));
                clubBean.setClubName(rs.getString("clubName"));
                clubs.add(clubBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("clubs", clubs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addMerch.jsp");
        dispatcher.forward(request, response);
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
        return "Fetches clubID and clubName for merchandise form";
    }
}
