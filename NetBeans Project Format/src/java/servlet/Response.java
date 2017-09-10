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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HARI
 */
@WebServlet(name = "Response", urlPatterns = {"/Response"})
public class Response extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {;
        Cookie co = new Cookie("pid","1");
        co.setMaxAge(60*60*2);
        res.addCookie(co);
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "md", "md");
            System.out.println("Connected");
            PreparedStatement ps = con.prepareStatement("select distinct(pid) from post_requests p where (p.up_owners-p.down_owners)>200 ");
            ResultSet rs = ps.executeQuery();
            PrintWriter pw = res.getWriter();
            res.setContentType("text/html");
            pw.println("<html><head></head><body>");
            while (rs.next()) {
                PreparedStatement p = con.prepareStatement("select description from posts where post_id = ?");
                p.setInt(1,Integer.parseInt(rs.getString("pid")));
                ResultSet r = p.executeQuery();
                rs.next();
                pw.println(rs.getString("description"));
                pw.println("<br><form action=\"/Hackathon/Storer\" method=\"POST\"><textarea name=\"reply\" rows=\"4\" cols=\"8\"></textarea><input type\"submit\"></form>");
                

            }
            pw.close();
        } catch (Exception e) {
            //Error Page
//            RequestDispatcher dispatch = req.getRequestDispatcher("option.html");
//        dispatch.forward(req,res);
        }
    }
}
