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

@WebServlet("/Balance_enquiry")
public class Balance_enquiry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException   {
	 try {
	
        response.setContentType("text/html");
     	PrintWriter out=response.getWriter();
     	Connection con = Jdbcconnection.initializeDatabase();
 
     	/*Cookie []cookies=request.getCookies();
    	boolean f=false;
    	String name="";
    	String pass="";
    	if(cookies ==  null)
    	{
    		return;
    	}
    	else{
    		for(Cookie c: cookies){
    			String tname=c.getName();
    			if(tname.equals("login"))
    			{
    				for(Cookie c1: cookies)
    				{
    					String t1name=c1.getName();
    					if(t1name.equals("pass"))
    					{
    						f=true;
    						name=c.getValue();
    		    			pass=c1.getValue();
    					}
    				}	
    			}
    		}  		
    	}
     	*/
     	
     	HttpSession session=request.getSession(false);
		if(session!=null){
			
			String log=(String)session.getAttribute("login_id");
    		String pass=(String)session.getAttribute("password");
			
			PreparedStatement ps1=(PreparedStatement) con.prepareStatement
	     	("SELECT Deposit FROM register WHERE Login_id=? AND Password=?");
	     	ps1.setString(1,log);
	    	ps1.setString(2,pass);
	        ResultSet r= ps1.executeQuery();
	        
	        if(r.next()) 
	        {
	         out.println("YOUR TOTAL AMMOUNT IS:"+r.getString(1));
	         out.println("<br><br>	<a type=\"submit\"  href=\"transaction.html\" >Back</a>");
	        }		 
		}
		else{
			out.print("Please login first");
			request.getRequestDispatcher("index.html").include(request, response);
		}
	 } 
	 catch (Exception e) {e.printStackTrace();} 
   } 
}