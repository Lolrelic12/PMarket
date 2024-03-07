/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.ContentDelivery;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import security.Hash;
import dal.AccountDAO;

/**
 *
 * @author admin
 */
public class changepassword extends HttpServlet {

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
            out.println("<title>Servlet changepassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changepassword at " + request.getContextPath() + "</h1>");
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

        int userid = Integer.parseInt(request.getParameter("userid"));
        Account a = ad.getAccountById(userid);
        String newPasswordHash = Hash.getHash(request.getParameter("newPassword"), "SHA-256");
        String confirmPasswordHash = Hash.getHash(request.getParameter("confirmPassword"), "SHA-256");

        if (!newPasswordHash.equals(confirmPasswordHash)) {
            request.setAttribute("userid", userid);
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            return;
        }

        ad.changePassword(userid, newPasswordHash);
        ContentDelivery.sendSecurityAlert(a.getEmail(), a.getUsername(), a.getDisplayName());
        request.getRequestDispatcher("login.jsp").forward(request, response);
        return;
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
        AccountDAO ad = new AccountDAO();

        String username = request.getParameter("username");
        Account a = ad.getAccountByUsername(username);
        String passwordHash = Hash.getHash(request.getParameter("password"), "SHA-256");
        String newPasswordHash = Hash.getHash(request.getParameter("newPassword"), "SHA-256");
        String confirmPasswordHash = Hash.getHash(request.getParameter("confirmPassword"), "SHA-256");

        if (!newPasswordHash.equals(confirmPasswordHash)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("account.jsp").forward(request, response);
            return;
        }

        if (newPasswordHash.equals(passwordHash)) {
            request.setAttribute("error", "New password and old password cannot be the same.");
            request.getRequestDispatcher("account.jsp").forward(request, response);
            return;
        }

        int credentialsMatch = ad.verifyLogin(username, passwordHash);
        if (credentialsMatch == 0) {
            request.setAttribute("error", "Incorrect password.");
            request.getRequestDispatcher("account.jsp").forward(request, response);
            return;
        } else if (credentialsMatch == 1) {
            ad.changePassword(ad.getAccountIdByUsername(username), newPasswordHash);
            ContentDelivery.sendSecurityAlert(a.getEmail(), a.getUsername(), a.getDisplayName());
            request.getRequestDispatcher("account.jsp").forward(request, response);
            return;
        }
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
