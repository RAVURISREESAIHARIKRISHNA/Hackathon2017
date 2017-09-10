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
import java.sql.ResultSetMetaData;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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
@WebServlet(name = "PostFeed", urlPatterns = {"/PostFeed"})
public class PostFeed extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "md", "md");
            System.out.println("Connected");
            res.setContentType("text/html");
            PreparedStatement ps = con.prepareStatement("select description,reply from posts");
            ResultSet rs = ps.executeQuery();
            PrintWriter pw = res.getWriter();
            pw.print("<html><head></head><body>");
            while (rs.next()) {
                pw.println("<b>");
                pw.println(rs.getString("description"));
                pw.println("<br><input type=\"button\" value=\"Upvote\"");
                pw.println("<br><input type=\"button\" value=\"Downvote\"");
                pw.println("</b>");
                pw.println(rs.getString("reply"));
            }
            pw.println("</body><html>");
            pw.close();
        } catch (Exception e) {
            System.out.println("Exception Caught");
            e.printStackTrace();
//            RequestDispatcher dispatch = req.getRequestDispatcher("option.html");
//        dispatch.forward(req,res);
        }
    }
}
