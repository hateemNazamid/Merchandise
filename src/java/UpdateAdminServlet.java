
import bean.RegisterBean;
import dao.RegisterDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UpdateAdminServlet")
public class UpdateAdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminUsername") == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        String name = request.getParameter("name");   
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String strId = request.getParameter("id");
        int id = Integer.parseInt(strId);

        RegisterBean updatedAdmin = new RegisterBean();
        
        updatedAdmin.setName(name);
        updatedAdmin.setUsername(username);
        updatedAdmin.setPassword(password);
        updatedAdmin.setId(id);
        
        RegisterDao dao = new RegisterDao();
        String result = dao.updateAdmin(updatedAdmin);

        if ("SUCCESS".equals(result)) {
            session.setAttribute("admin", updatedAdmin);
            session.setAttribute("adminUsername", updatedAdmin.getUsername());
            request.setAttribute("successMessage", "Profile updated successfully.");
            response.sendRedirect("home_admin.jsp"); // 👈 redirect instead of forward
        } else {
            request.setAttribute("errMessage", result);
            request.getRequestDispatcher("updateAdmin.jsp").forward(request, response);
        }
    }
}
