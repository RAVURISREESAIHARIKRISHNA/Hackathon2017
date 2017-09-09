/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HARI
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String aadhar = request.getParameter("aadhar");
        Cookie  cook = new Cookie("ac",aadhar);
        cook.setMaxAge(60*60);
        response.addCookie(cook);
        RequestDispatcher dispatch = request.getRequestDispatcher("option.html");
        dispatch.forward(request,response);
        
    }
}
