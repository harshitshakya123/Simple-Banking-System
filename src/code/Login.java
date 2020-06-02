package code;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
// Servlet Name 
@WebServlet("/Login") 
public class Login  extends HttpServlet { 
    private static final long serialVersionUID = 1L; 
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException   {
        try { 
        	   response.setContentType("text/html");
        	PrintWriter out=response.getWriter();
        	Connection con = Jdbcconnection.initializeDatabase();
        	String x= request.getParameter("loginid");
    	    String y= request.getParameter("password"); 
    	    PreparedStatement ps=(PreparedStatement) con.prepareStatement
    	    		("select * from register where Login_id=? and Password=?");
   
            
           ps.setString(1,x);
           ps.setString(2,y);
             ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
            	/*Cookie c=new Cookie("login",x);
            	Cookie c1=new Cookie("pass",y);
            	response.addCookie(c);
            	response.addCookie(c1);
            	Thread.sleep(1200);
            	*/
            	
            	HttpSession session=request.getSession();
        		session.setAttribute("login_id",x);
        		session.setAttribute("password",y);
        		response.sendRedirect("transaction.html");
        	
            	
            }
            else 
           {
            	/*out.println("YOU ARE NOT PROVIDE A VALID LOGIN_ID AND PASSWORD");
            	 out.println("<br><br>	<a type=\"submit\"  href=\"index.html\" >Back</a>");*/
            	out.print("Sorry, username or password error!");
    			request.getRequestDispatcher("index.html").include(request, response);
                 
           }
 	
        } 
        catch (Exception e) {e.printStackTrace();} 
     } 
}