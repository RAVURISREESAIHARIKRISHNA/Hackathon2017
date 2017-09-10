/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.Date;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Random;
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
@WebServlet(name = "PutPost", urlPatterns = {"/PutPost"})
public class PutPost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
//        @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Cookie cook[] = req.getCookies();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "md", "md");
            System.out.println("Connected");
            PreparedStatement ps = con.prepareStatement("insert into posts values(?,?,?,?,?,?,?,?,?,?,?)");
            Random obj = new Random();
            ps.setInt(1, obj.nextInt(99999) + 1);
            ps.setString(2, req.getParameter("exp"));
            ps.setInt(3, Integer.parseInt(cook[0].getValue()));
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");

            ps.setString(4, formatter.format(new Date()));
            ps.setInt(5, 0);
            ps.setInt(6, 0);
            ps.setString(7, "hi");
            ps.setString(8, "25/06/2017");
            ps.setInt(9, 0);
            ps.setString(10, req.getParameter("district"));
            ps.setString(11, req.getParameter("mandal"));
            Object o = ps.executeUpdate();
            System.out.println("Success");
            RequestDispatcher dispatch = req.getRequestDispatcher("option.html");
            dispatch.forward(req, res);

        } catch (Exception e) {
            System.out.println("Exception Caught");
            e.printStackTrace();
//            RequestDispatcher dispatch = req.getRequestDispatcher("option.html");
//        dispatch.forward(req,res);
        }
    }
}
