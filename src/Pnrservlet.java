import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
/**
 * Servlet implementation class Pnrservlet
 */
@WebServlet("/Pnrservlet")
public class Pnrservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pnrservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String tid=request.getParameter("p1");
		String uid=request.getParameter("p2");
		RequestDispatcher rd=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","MYDB","admin");
				if(con==null)
					out.println("connect not created");
				else
				{
					System.out.println("connection created");
					PreparedStatement ps=con.prepareStatement("SELECT * FROM RRS_train WHERE TID=?");
					ps.setString(1, tid);
					PreparedStatement qr=con.prepareStatement("SELECT * FROM RRS_Book WHERE usern=?");
					qr.setString(1, uid);
					ResultSet rs=ps.executeQuery();
					ResultSet st=qr.executeQuery();
					if(rs.next()&&st.next())
					{
						PreparedStatement as=con.prepareStatement("select * from RRS_Book");
						ResultSet bs=as.executeQuery();
						PrintWriter out2=response.getWriter();
						out.println("<html><body bgcolor='Azure'><center><h3>Personal Details</h3><table border='1'><tr><td>Name</td><td>Age</td><td>Departure</td><td>Destination</td><td>Class</td><td>Journey Date</td><td>train</td></tr>");
						while(bs.next()) {
							
							out.println("<tr><td>"+bs.getString(1)+"</td><td>"+bs.getString(2)+"</td><td>"+bs.getString(3)+"</td><td>"+bs.getString(4)+"</td><td>"+bs.getString(5)+"</td><td>"+bs.getString(6)+"</td><td>"+bs.getString(7)+"</td></tr>");
							
						}
						out.println("</table></center>");
						
						response.setContentType("text/html");
						//PreparedStatement sp=con.prepareStatement("select * from RRS_train");
						PreparedStatement sp=con.prepareStatement("SELECT * FROM RRS_train WHERE TID=?");
						sp.setString(1, tid);
						ResultSet sr=sp.executeQuery();
						PrintWriter out1=response.getWriter();
						out.println("<center><h3>Train Details</h3><table border='1'><tr><td>Train ID</td><td>Train Name</td><td>Route</td><td>Fare</td><td>Type</td></tr>");
						while(sr.next()) {
							
							out.println("<tr><td>"+sr.getString(1)+"</td><td>"+sr.getString(2)+"</td><td>"+sr.getString(3)+"</td><td>"+sr.getString(4)+"</td><td>"+sr.getString(5)+"</td></tr>");
							
						}
						out.println("</table><br><br><form align=\"center\" action='login.html'>\r\n" + 
								"<input class=\"button1\" type=submit  value=\"LOG OUT\"></center></body></html>");
						//rd=request.getRequestDispatcher("Fetchdata");
						//rd=sc.getRequestDispatcher("/Helloservlet");
						//hs.setAttribute("uid", uid);
					}
					else
					{
						rd=request.getRequestDispatcher("login.html");
					}
					
					rd.forward(request, response);
				}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
