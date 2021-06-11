
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Fetchdata
 */
@WebServlet("/Fetchdata")
public class Fetchdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fetchdata() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","MYDB","admin");
				if(con==null)
					System.out.println("connect not created");
				else
				{
					response.setContentType("text/html");
					System.out.println("connection created");
					PreparedStatement ps=con.prepareStatement("select * from RRS_Book");
					ResultSet rs=ps.executeQuery();
					PrintWriter out=response.getWriter();
					out.println("<html><body bgcolor='Azure'><center><h3>Personal Details</h3><table border='1'><tr><td>Name</td><td>Age</td><td>Departure</td><td>Destination</td><td>Class</td><td>Journey Date</td><td>username</td><td>train</td></tr>");
					while(rs.next()) {
						
						out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td></tr>");
						
					}
					out.println("</table></center>");
					
					
					response.setContentType("text/html");
					PreparedStatement sp=con.prepareStatement("select * from RRS_train");
					ResultSet sr=sp.executeQuery();
					PrintWriter out1=response.getWriter();
					out.println("<center><h3>Train Details</h3><table border='1'><tr><td>Train ID</td><td>Train Name</td><td>Route</td><td>Fare</td><td>Type</td></tr>");
					while(sr.next()) {
						
						out.println("<tr><td>"+sr.getString(1)+"</td><td>"+sr.getString(2)+"</td><td>"+sr.getString(3)+"</td><td>"+sr.getString(4)+"</td><td>"+sr.getString(5)+"</td></tr>");
						
					}
					out.println("</table><br><br><form align=\"center\" action='login.html'>\r\n" + 
							"<input class=\"button1\" type=submit  value=\"LOG OUT\"></center></body></html>");
				}
				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
