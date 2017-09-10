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
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HARI
 */
@WebServlet(name = "GovtAuth", urlPatterns = {"/GovtAuth"})
public class GovtAuth extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "md", "md");
            System.out.println("Connected");
            PreparedStatement ps = con.prepareStatement("select password from govt where usr=?");
            String pass = req.getParameter("pass");
            ps.setString(1, req.getParameter("usr"));
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (pass.equals(rs.getString("pass"))) {
                RequestDispatcher dispatch = req.getRequestDispatcher("/Hackathon/Response");
                dispatch.forward(req, res);
            } else {
                RequestDispatcher dispatch = req.getRequestDispatcher("error.html");
                dispatch.forward(req, res);
            }

        } catch (Exception e) {
            //ERROR Page
 RequestDispatcher dispatch = req.getRequestDispatcher("error.html");
        dispatch.forward(req,res);
            System.out.println("Exception GovtAuth");
            //e.printStackTrace();
        }
    }
}
