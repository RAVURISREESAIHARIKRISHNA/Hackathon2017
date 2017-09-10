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
            pw.print("<html><head></head><body style=\"background-color:light yellow;\">");
            while (rs.next()) {
                pw.println("<form action=\"/Hackathon1/Storer\" method=\"POST\"");
               pw.println("<input type=\"text\" type=\"hidden\" value=\"1\" name=\"hid\"");
                pw.println("<div style=\"margin:0 auto;width:800px;border:2px solid red;\">");
                pw.println("<h3 style=\"margin:0 auto;width:200px;\"></h3>");
                pw.println("<div style=\"margin:0 auto;width:700px;border:2px solid blue;\">");
                pw.println("<br><span style=\"width:300px;border:2px solid red;display:inline-block;\"");
                
                pw.println(rs.getString("description"));
                pw.println("</span><input value=\"Upvote\" <br=\"\" type=\"button\"><input value=\"Downvote\"<=\"\" b=\"\" type=\"button\">");
                pw.println("<span style=\"width:100px;display:inline-block;border;2px solid green;\">");
                pw.println(rs.getString("reply"));
                pw.println("</span>");
                pw.println("<div style=\"background-color:green;\"");
                pw.println("<input type=\"submit\" value=\"Upvote\"");
                pw.println("<input type=\"submit\" value=\"DownVote\"");
                pw.println("</div>");
                pw.println("<hr>");
                pw.println("Please press Back after Voting");
                pw.println("</form>");
            }
            pw.println("</body><html>");
            pw.close();
        } catch (Exception e) {
            System.out.println("Exception Caught");
            e.printStackTrace();
            RequestDispatcher dispatch = req.getRequestDispatcher("eror.html");
        dispatch.forward(req,res);
        }
    }
}
