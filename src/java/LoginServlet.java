
import bean.LoginBean;
import bean.RegisterBean;
import dao.LoginDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String type = request.getParameter("type");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        LoginDao loginDao = new LoginDao();
        loginBean = loginDao.authenticateUser(loginBean);

        if (loginBean == null) {
            request.setAttribute("errMessage", "Invalid username or password.");
            if ("admin".equals(type)) {
                request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("userLogin.jsp").forward(request, response);
            }
            return;
        }

        if ("customer".equals(loginBean.getRole()) && type.equals("user")) {
            HttpSession session = request.getSession();
            session.setAttribute("customerID", loginBean.getID());
            //
            // Build RegisterBean from loginBean
            RegisterBean customer = new RegisterBean();
            customer.setData(
                    loginBean.getName(),
                    loginBean.getEmail(),
                    loginBean.getUsername(),
                    loginBean.getPassword(),
                    loginBean.getAddress(),
                    loginBean.getID()
            );

            session.setAttribute("customer", customer); // ✅ This fixes updateCustomer.jsp
            //
            session.setAttribute("custUsername", loginBean.getUsername());
            response.sendRedirect("home_customer.jsp");
            //request.getRequestDispatcher("home_customer.jsp").forward(request,response);
        } else if ("admin".equals(loginBean.getRole()) && type.equals("admin")) {
            HttpSession session = request.getSession();
            session.setAttribute("adminID", loginBean.getID());

            // Build RegisterBean from loginBean
            RegisterBean admin = new RegisterBean();
            admin.setName(loginBean.getName());
            admin.setUsername(loginBean.getUsername());
            admin.setPassword(loginBean.getPassword());
            admin.setId(loginBean.getID());

            session.setAttribute("admin", admin); // ✅ This fixes updateCustomer.jsp

            session.setAttribute("adminUsername", loginBean.getUsername());
            response.sendRedirect("home_admin.jsp");
            //request.getRequestDispatcher("home_admin.jsp").forward(request,response);
        } else {
            request.setAttribute("errMessage", "Invalid username or password.");
            if ("admin".equals(type)) {
                request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("userLogin.jsp").forward(request, response);
            }
            return;
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
        return "Login Servlet";
    }// </editor-fold>
}
