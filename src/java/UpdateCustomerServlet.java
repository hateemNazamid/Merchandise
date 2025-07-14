
import bean.RegisterBean;
import dao.RegisterDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("custUsername") == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String strId = request.getParameter("id");
        int id = Integer.parseInt(strId);

        RegisterBean updatedCustomer = new RegisterBean(name, email, username, password, address, id);
        RegisterDao dao = new RegisterDao();
        String result = dao.updateCustomer(updatedCustomer);

        if ("SUCCESS".equals(result)) {
            session.setAttribute("customer", updatedCustomer);
            request.setAttribute("successMessage", "Profile updated successfully.");
            response.sendRedirect("home_customer.jsp"); // 👈 redirect instead of forward
        } else {
            request.setAttribute("errMessage", result);
            request.getRequestDispatcher("updateCustomer.jsp").forward(request, response);
        }
    }
}
