package code;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
// Servlet Name 
@WebServlet("/Register") 
public class Register  extends HttpServlet { 
    private static final long serialVersionUID = 1L; 
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException   {
        try { 
            // Initialize the database 
            Connection con = Jdbcconnection.initializeDatabase(); 
            PreparedStatement st1 = con .prepareStatement
            		("insert into register values(?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
          
            
          //fetch data in html when we press register
            st1.setString(1,request.getParameter("n1"));
            st1.setString(2,request.getParameter("n2"));
            st1.setString(3,request.getParameter("n3"));
            st1.setString(4,request.getParameter("n4"));
            st1.setString(5,request.getParameter("n5"));
            st1.setString(6,request.getParameter("n6"));
    	    st1.setString(7,request.getParameter("n7"));
    	    st1.setString(8,request.getParameter("n8"));
    	    st1.setString(9,request.getParameter("n9"));
    	    st1.setString(10,request.getParameter("n10"));
    	    st1.setString(11,request.getParameter("n11"));
    	    st1.setString(12,request.getParameter("n12"));
    	    st1.setInt(13, 0);
    	   
          
            st1.executeUpdate(); 
            st1.close();
            con.close(); 
            
            response.sendRedirect("index.html");
          
        } 
        catch (Exception e) {e.printStackTrace();} 
    } 
}