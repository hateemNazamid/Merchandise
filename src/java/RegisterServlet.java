/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bean.RegisterBean;
import dao.RegisterDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");       
        String email = request.getParameter("email");
        String name = request.getParameter("name");         
        String address = request.getParameter("address");
        String strId = request.getParameter("id");
        int id = Integer.parseInt(strId);


        System.out.println("Received Data:");
        System.out.println("Name: " + request.getParameter("name"));
        System.out.println("Email: " + request.getParameter("email"));
        System.out.println("Username: " + request.getParameter("username"));
        System.out.println("Password: " + request.getParameter("password"));       
        System.out.println("Address: " + request.getParameter("address"));
        
        RegisterBean registerBean = new RegisterBean();
        registerBean.setData(name,email,username,password,address,id);
        
        RegisterDao registerDao = new RegisterDao();
        String userValidate = registerDao.registerUser(registerBean);
        
        if ("SUCCESS".equals(userValidate)) {
            request.getRequestDispatcher("userLogin.jsp").forward(request,response);
            
        }else{
            request.setAttribute("errMessage",userValidate);
            request.getRequestDispatcher("/login.jsp").forward(request,response);

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
        return "Short description";
    }// </editor-fold>

}
