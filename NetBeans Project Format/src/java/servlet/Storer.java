/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "Storer", urlPatterns = {"/Storer"})
public class Storer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {RequestDispatcher dispatch = request.getRequestDispatcher("option.html");
            dispatch.forward(request, response);
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "md", "md");
            System.out.println("Connected");
            PreparedStatement ps = con.prepareStatement("update posts set reply=?,date=?,satatus=1 where pid=1");
            ps.setString(1, request.getParameter("reply"));
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            ps.setString(2, formatter.format(new Date()));
            ps.execute();
            System.out.println("Success");
            RequestDispatcher dispatch1 = request.getRequestDispatcher("option.html");
            dispatch.forward(request, response);

        } catch (Exception e) {

            //Errror Page
            RequestDispatcher dispatch = request.getRequestDispatcher("error.html");
        dispatch.forward(request,response);
        }
    }

}
