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
import java.text.Format;
import java.text.SimpleDateFormat;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie cook[] = request.getCookies();
        try{Class.forName("jdbc:oracle:thin:@localhost:8080:orcl");}catch(Exception g){System.out.println("45");}

        try(
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:8080:orcl","md","md");
                PreparedStatement ps = con.prepareStatement("insert into posts values(posts_sequence.nextval,?,?,?,?,?,?,?,?,?,?");
                
        ){
           try{ps.setString(1,request.getParameter("exp"));//Descriprion
           ps.setString(2,cook[0].getValue());//aadhar
           Format formatter = new SimpleDateFormat("dd/MM/yyyy");
           ps.setString(3, formatter.format(new Date()));//Date
           ps.setInt(4,0);//Upvotes
           ps.setInt(5, 0);//Downvotes
           ps.setNull(6,java.sql.Types.INTEGER);//Reply
           ps.setNull(7,java.sql.Types.INTEGER);//Reply Date
           ps.setInt(8, 0);//0 for Open and 1 for Close
           ps.setString(9, request.getParameter("district"));//District
           ps.setString(9, request.getParameter("mandal"));//Mandal
           System.out.println(ps.executeUpdate());
           RequestDispatcher dispatch = request.getRequestDispatcher("option.html");
           dispatch.forward(request,response);

           
        }catch(Exception e){
        //error page
               System.out.println("67");
        }}
        catch (Exception e){
            //ERROR Page
            e.printStackTrace();
        }
        
                
        
    }
}
