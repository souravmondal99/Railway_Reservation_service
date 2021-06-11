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
 * Servlet implementation class Loginservlet
 */
@WebServlet("/Loginservlet")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginservlet() {
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
		String uid=request.getParameter("t1");
		String pass=request.getParameter("t2");
		RequestDispatcher rd=null;
		//HttpSession hs=request.getSession(true);
		//ServletContext sc=getServletContext();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","MYDB","admin");
				if(con==null)
					out.println("connect not created");
				else
				{
					System.out.println("connection created");
					PreparedStatement ps=con.prepareStatement("SELECT * FROM RRS_USERS WHERE USERID=? AND PASS=?");
					ps.setString(1, uid);
					ps.setString(2, pass);
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						response.sendRedirect("select.html");
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
