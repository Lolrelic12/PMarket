/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ContentDelivery;
import model.Account;
import utility.KeyGenerator;

/**
 *
 * @author admin
 */
public class resetpassword extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet resetpassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet resetpassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        AccountDAO ad = new AccountDAO();
        String email = request.getParameter("email");

        if (!ad.emailExists(email)) {
            request.setAttribute("error", "This email address is unregistered.");
            request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
        } else {
            int accountId = ad.getAccountIdByEmail(email);
            if (!ad.getVerifiedStatus(accountId)) {
                request.setAttribute("error", "This email address is not yet verified.");
                request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
            } else {
                Account a = ad.getAccountById(accountId);
                String verificationCode = KeyGenerator.generateVerificationCode();
                ContentDelivery.sendPasswordResetRequest(a.getEmail(), a.getUsername(), a.getDisplayName(), verificationCode);
                request.setAttribute("userid", a.getAccountId());
                request.setAttribute("template", verificationCode);
                request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
            }
        }

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
        String userid = request.getParameter("userid");
        String verificationCode = request.getParameter("template");
        String input = request.getParameter("response");
        
        if (!verificationCode.equals(input)) {
            request.setAttribute("error", "Incorrect verification code.");
            request.setAttribute("userid", userid);
            request.setAttribute("template", verificationCode);
            request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
            return;
        }

        request.setAttribute("userid", userid);
        request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
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
